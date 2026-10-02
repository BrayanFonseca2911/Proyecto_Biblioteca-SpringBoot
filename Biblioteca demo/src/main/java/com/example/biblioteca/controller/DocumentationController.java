package com.example.biblioteca.controller;

import org.springframework.stereotype.Controller;

@Controller
public class DocumentationController {
    public String documentation(){
        return "redirect:/docs/index.html";
    }
}
