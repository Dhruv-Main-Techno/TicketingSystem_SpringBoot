package com.main.ticketing.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping({"/", "/ui"})
    public String home() {
        return "index"; // Renders src/main/resources/templates/index.html
    }
}
