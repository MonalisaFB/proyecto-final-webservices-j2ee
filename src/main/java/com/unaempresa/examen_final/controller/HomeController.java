package com.unaempresa.examen_final.controller;

import com.unaempresa.examen_final.service.TematicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final TematicaService tematicaService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tematicas", tematicaService.listarTodas());
        return "index";
    }
}