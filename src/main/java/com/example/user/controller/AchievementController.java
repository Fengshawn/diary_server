package com.example.user.controller;


import com.example.user.model.Achievement;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(@PathVariable Long id) {
        return ResponseEntity.ok(achievementService.getCardById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(achievementService.getCardsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@PathVariable Long id, @RequestBody Achievement updatedData) {
        achievementService.updateCard(id, updatedData);
        return ResponseEntity.ok("更新成功");
    }
}