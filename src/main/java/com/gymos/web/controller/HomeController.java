package com.gymos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index"; // Vrátí název šablony HTML pro domovskou stránku
    }
}
