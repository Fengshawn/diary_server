package com.example.user.controller;


import com.example.user.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/achievement")
public class AchievementController {
    @Autowired
    private AchievementService achievementService;

    @PostMapping("/receive")
    public ResponseEntity<?> receiveCard(@RequestBody Map<String, Long> request) {
        Long cardId = request.get("cardId");
        achievementService.receive(cardId);
        return ResponseEntity.ok("已收下");
    }

    @DeleteMapping("/discard")
    public ResponseEntity<?> discardCard(@RequestParam Long cardId) {
        achievementService.discard(cardId); // maybe soft delete or flag
        return ResponseEntity.ok("已放弃");
    }

}