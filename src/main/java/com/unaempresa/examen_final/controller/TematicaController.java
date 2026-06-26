package com.unaempresa.examen_final.controller;

import com.unaempresa.examen_final.model.Tematica;
import com.unaempresa.examen_final.service.TematicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tematicas")
@RequiredArgsConstructor
public class TematicaController {

    private final TematicaService tematicaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "tematica/listar";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("tematica", new Tematica());
        return "tematica/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("tematica") Tematica tematica,
                          BindingResult result,
                          RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "tematica/form";
        }
        tematicaService.guardar(tematica);
        redirect.addFlashAttribute("success", "Temática guardada correctamente");
        return "redirect:/tematicas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("tematica", tematicaService.buscarPorId(id));
        return "tematica/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirect) {
        tematicaService.eliminar(id);
        redirect.addFlashAttribute("success", "Temática eliminada correctamente");
        return "redirect:/tematicas";
    }
}