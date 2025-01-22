package com.example.PlayMate.Controller;

import com.example.PlayMate.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private GameService gameService;

    @GetMapping("/admin")
    public String showAdminPage() {
        // Return the name of the HTML page (e.g., "admin.html")
        return "Admin";
    }
}
