package com.example.user.controller;

import com.example.user.dto.NoteDTO;
import com.example.user.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping("/save")
    public ResponseEntity<?> saveNote(@RequestBody NoteDTO dto) {
        noteService.saveNote(dto); // 若 isSaved=true 标记 CONFIRMED
        return ResponseEntity.ok("保存成功");
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam("noteId") Long noteId,
                                         @RequestPart("file") MultipartFile file) {
        String imageUrl = noteService.saveNoteImage(noteId, file);
        return ResponseEntity.ok(Map.of("imageUrl", imageUrl));
    }
}
////Todo 生成成就卡是不是可以直接用Python api去和llm交互
//@PostMapping("/note/generate-achievement")
//public ResponseEntity<?> generateAchievement(@RequestBody Map<String, Long> request) {
//    Long noteId = request.get("noteId");
//    achievementCardService.generate(noteId); // 调用 LLM，扣积分，生成卡片
//    return ResponseEntity.ok("生成成功");
//}

////Todo 调llm api最好还是用python做, rag或者agent的生态还是python好, 万一以后做大做强了呢
//public void generate(Long noteId) {
//    Note note = noteRepo.findById(noteId).orElseThrow();
//    Long userId = note.getUserId();
//
//    if (!note.getIsSaved()) throw new IllegalStateException("未确认保存不能生成成就卡");
//
//    // 校验积分是否充足
//    userGlowService.checkAndDeductPoint(userId, 1, GlowChangeType.GENERATE_ACHIEVEMENT);
//
//    // 调用 LLM API
//    LLMResponse resp = llmClient.call(note.getContent(), note.getImageUrl());
//
//    // 保存成就卡
//    AchievementCard card = new AchievementCard();
//    card.setNoteId(noteId);
//    card.setUserId(userId);
//    card.setCardTitle(resp.title);
//    card.setCardDescription(resp.description);
//    card.setCardTags(String.join(",", resp.tags));
//    card.setLlmRawOutput(resp.raw);
//    cardRepo.save(card);
//}


