package com.unaempresa.examen_final.controller;

import com.unaempresa.examen_final.dto.PreguntaFormDTO;
import com.unaempresa.examen_final.dto.RespuestaDTO;
import com.unaempresa.examen_final.model.*;
import com.unaempresa.examen_final.service.PreguntaService;
import com.unaempresa.examen_final.service.TematicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/preguntas")
@RequiredArgsConstructor
public class PreguntaController {

    private final PreguntaService preguntaService;
    private final TematicaService tematicaService;

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(required = false) Long tematicaId,
                         @RequestParam(required = false) TipoPregunta tipo,
                         Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Pregunta> pagina = preguntaService.filtrar(tematicaId, tipo, pageable);

        model.addAttribute("tematicaId", tematicaId);
        model.addAttribute("tipo", tipo);
        model.addAttribute("pagina", pagina);
        model.addAttribute("tematicas", tematicaService.listarTodas());
        model.addAttribute("tipos", TipoPregunta.values());
        return "pregunta/listar";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("preguntaForm", new PreguntaFormDTO());
        model.addAttribute("tematicas", tematicaService.listarTodas());
        model.addAttribute("tipos", TipoPregunta.values());
        return "pregunta/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("preguntaForm") PreguntaFormDTO form,
                          BindingResult result,
                          RedirectAttributes redirect,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tematicas", tematicaService.listarTodas());
            model.addAttribute("tipos", TipoPregunta.values());
            return "pregunta/form";
        }

        Tematica tematica = tematicaService.buscarPorId(form.getTematicaId());
        Pregunta pregunta;

        switch (form.getTipo()) {
            case VF:
                PreguntaVerdaderoFalso vf = new PreguntaVerdaderoFalso();
                vf.setRespuestaCorrecta("true".equalsIgnoreCase(form.getRespuestaCorrecta() != null ? form.getRespuestaCorrecta().trim() : ""));
                pregunta = vf;
                break;
            case UNICA:
                PreguntaSeleccionUnica unica = new PreguntaSeleccionUnica();
                unica.setOpcionCorrecta(form.getRespuestaCorrecta() != null ? form.getRespuestaCorrecta().trim() : "");
                if (form.getOpciones() != null && !form.getOpciones().isBlank()) {
                    unica.setOpciones(Arrays.asList(form.getOpciones().split("\\n")));
                }
                pregunta = unica;
                break;
            case MULTIPLE:
                PreguntaSeleccionMultiple multiple = new PreguntaSeleccionMultiple();
                if (form.getOpciones() != null && !form.getOpciones().isBlank()) {
                    multiple.setOpciones(Arrays.asList(form.getOpciones().split("\\n")));
                }
                pregunta = multiple;
                break;
            default:
                throw new IllegalArgumentException("Tipo no válido: " + form.getTipo());
        }

        if (form.getId() != null) {
            pregunta.setId(form.getId());
        }
        pregunta.setEnunciado(form.getEnunciado().trim());
        pregunta.setTematica(tematica);

        preguntaService.guardar(pregunta);
        redirect.addFlashAttribute("success", "Pregunta guardada correctamente");
        return "redirect:/preguntas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Pregunta pregunta = preguntaService.buscarPorId(id);
        PreguntaFormDTO form = new PreguntaFormDTO();
        form.setId(pregunta.getId());
        form.setEnunciado(pregunta.getEnunciado());
        form.setTematicaId(pregunta.getTematica().getId());

        if (pregunta instanceof PreguntaVerdaderoFalso vf) {
            form.setTipo(TipoPregunta.VF);
            form.setRespuestaCorrecta(vf.getRespuestaCorrecta().toString());
        } else if (pregunta instanceof PreguntaSeleccionUnica unica) {
            form.setTipo(TipoPregunta.UNICA);
            form.setRespuestaCorrecta(unica.getOpcionCorrecta());
            form.setOpciones(String.join("\n", unica.getOpciones()));
        } else if (pregunta instanceof PreguntaSeleccionMultiple multiple) {
            form.setTipo(TipoPregunta.MULTIPLE);
            form.setOpciones(String.join("\n", multiple.getOpciones()));
        }

        model.addAttribute("preguntaForm", form);
        model.addAttribute("tematicas", tematicaService.listarTodas());
        model.addAttribute("tipos", TipoPregunta.values());
        return "pregunta/form";
    }

    @GetMapping("/responder/{id}")
    public String responderForm(@PathVariable Long id, Model model) {
        Pregunta pregunta = preguntaService.buscarPorId(id);
        RespuestaDTO respuesta = new RespuestaDTO();
        respuesta.setPreguntaId(pregunta.getId());
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("respuesta", respuesta);
        return "pregunta/responder";
    }

    @PostMapping("/responder")
    public String responder(@ModelAttribute RespuestaDTO respuesta, Model model) {
        Pregunta pregunta = preguntaService.buscarPorId(respuesta.getPreguntaId());
        boolean acierto = false;

        if (pregunta instanceof PreguntaVerdaderoFalso vf) {
            acierto = Boolean.parseBoolean(respuesta.getRespuesta()) == vf.getRespuestaCorrecta();
        } else if (pregunta instanceof PreguntaSeleccionUnica unica) {
            acierto = unica.getOpcionCorrecta().equalsIgnoreCase(respuesta.getRespuesta().trim());
        } else if (pregunta instanceof PreguntaSeleccionMultiple multiple) {
            List<String> correctas = multiple.getOpcionesCorrectas().stream().map(String::toLowerCase).sorted().toList();
            List<String> dadas = respuesta.getRespuestas().stream().map(String::toLowerCase).sorted().toList();
            acierto = correctas.equals(dadas);
        }

        model.addAttribute("pregunta", pregunta);
        model.addAttribute("acierto", acierto);
        model.addAttribute("respuesta", respuesta);
        return "pregunta/resultado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        preguntaService.eliminar(id);
        redirect.addFlashAttribute("success", "Pregunta eliminada correctamente");
        return "redirect:/preguntas";
    }
}