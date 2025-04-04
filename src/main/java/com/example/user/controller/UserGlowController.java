package com.example.user.controller;

import com.example.user.model.UserGlow;
import com.example.user.service.UserGlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/glow")
public class UserGlowController {

    @Autowired
    private UserGlowService glowService;

    @GetMapping("/current")
    public ResponseEntity<UserGlow> getCurrentGlow(@RequestParam Long userId) {
        return ResponseEntity.ok(glowService.getUserGlow(userId));
    }
}

