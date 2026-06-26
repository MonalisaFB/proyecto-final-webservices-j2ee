package com.unaempresa.examen_final.controller;

import com.unaempresa.examen_final.model.Pregunta;
import com.unaempresa.examen_final.model.TipoPregunta;
import com.unaempresa.examen_final.model.Tematica;
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
                         Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Pregunta> pagina;

        if (tematicaId != null) {
            pagina = preguntaService.listarPorTematica(tematicaId, pageable);
        } else {
            pagina = preguntaService.listarTodas(pageable);
        }

        // Se añade siempre, aunque sea null, para que la vista pueda referenciarla sin error
        model.addAttribute("tematicaId", tematicaId);
        model.addAttribute("pagina", pagina);
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "pregunta/listar";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        Pregunta pregunta = new Pregunta();
        pregunta.setTematica(new Tematica());
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("tematicas", tematicaService.listarTodas());
        model.addAttribute("tipos", TipoPregunta.values());
        return "pregunta/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("pregunta") Pregunta pregunta,
                          BindingResult result,
                          RedirectAttributes redirect,
                          Model model) {
        if (result.hasErrors()) {
            if (pregunta.getTematica() == null) {
                pregunta.setTematica(new Tematica());
            }
            model.addAttribute("tematicas", tematicaService.listarTodas());
            model.addAttribute("tipos", TipoPregunta.values());
            return "pregunta/form";
        }
        pregunta.setTematica(tematicaService.buscarPorId(pregunta.getTematica().getId()));
        preguntaService.guardar(pregunta);
        redirect.addFlashAttribute("success", "Pregunta guardada correctamente");
        return "redirect:/preguntas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("pregunta", preguntaService.buscarPorId(id));
        model.addAttribute("tematicas", tematicaService.listarTodas());
        model.addAttribute("tipos", TipoPregunta.values());
        return "pregunta/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        preguntaService.eliminar(id);
        redirect.addFlashAttribute("success", "Pregunta eliminada correctamente");
        return "redirect:/preguntas";
    }
}