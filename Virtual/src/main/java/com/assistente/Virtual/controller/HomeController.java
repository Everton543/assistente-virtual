package com.assistente.Virtual.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @GetMapping("/{chave}")
    public String index(@PathVariable("chave") String chave, Model model) {
        model.addAttribute("chave", chave);
        return "index";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
