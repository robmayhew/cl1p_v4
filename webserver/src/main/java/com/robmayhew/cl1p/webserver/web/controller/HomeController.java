package com.robmayhew.cl1p.webserver.web.controller;

import com.robmayhew.cl1p.webserver.config.Labels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final Labels labels;

    @Autowired
    public HomeController(Labels labels) {
        this.labels = labels;
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("labels", labels);
        return "index";
    }
}
