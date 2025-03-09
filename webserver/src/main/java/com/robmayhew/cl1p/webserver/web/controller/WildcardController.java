package com.robmayhew.cl1p.webserver.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WildcardController {


    @GetMapping("/**")
    public String wildcardGet(HttpServletRequest request, Model model)
    {

        return "wildcard_get";
    }

    @PostMapping("/**")
    public String wildcardPost(HttpServletRequest request, Model model)
    {
        return "wildcard_post";
    }
}
