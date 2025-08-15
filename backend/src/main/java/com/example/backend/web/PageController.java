package com.example.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class PageController {
    @GetMapping("/") public String home() { return "index"; }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/room")
    public String room(@RequestParam String id, @RequestParam(required = false) String name, Model model) {
        model.addAttribute("roomId", id);
        model.addAttribute("name", name == null ? "guest" : name);
        return "room";
    }
    
}
