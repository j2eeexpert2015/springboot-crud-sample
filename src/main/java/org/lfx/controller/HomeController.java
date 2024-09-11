package org.lfx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showWelcomePage() {
        return "welcome"; // Refers to welcome.html in the templates folder
    }
}
