package com.example.PlayMate.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class AuthController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This will map to the login.html in the templates directory
    }

    }
