package com.example.user.service;

import com.example.user.enums.GlowChangeType;
import com.example.user.model.Achievement;
import com.example.user.model.Note;
import com.example.user.repository.AchievementRepository;
import com.example.user.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository cardRepository;
    private final NoteRepository noteRepository;
    private final UserGlowService userGlowService;
    //Todo private final LLMClient llmClient;

    public Achievement generate(Long noteId) {
        Note note = noteRepository.findById(noteId).orElseThrow();
        Long userId = note.getUserId();

        if (!Boolean.TRUE.equals(note.getIsSaved())) {
            throw new IllegalStateException("笔记尚未保存，无法生成成就卡");
        }

        // 扣 1 积分
        userGlowService.checkAndDeductPoint(userId, 1);

        // Todo 调用 LLM（封装在 llmClient 中）
        //LLMResponse resp = llmClient.generateCard(note.getContent(), note.getImageUrl());

        Achievement achievement = new Achievement();
        achievement.setUserId(userId);
        achievement.setNoteId(noteId);
//        achievement.setCardTitle(resp.getTitle());
//        achievement.setCardDescription(resp.getDescription());
//        achievement.setCardTags(String.join(",", resp.getTags()));
//        achievement.setLlmRawOutput(resp.getRawJson());
        achievement.setIsReceived(false);
        achievement.setCreatedAt(LocalDateTime.now());

        return cardRepository.save(achievement);
    }

    public void receive(Long cardId) {
        Achievement achievement = cardRepository.findById(cardId).orElseThrow();
        if (Boolean.TRUE.equals(achievement.getIsReceived())) {
            throw new IllegalStateException("卡片已被收下");
        }
        achievement.setIsReceived(true);
        cardRepository.save(achievement);
    }

    public void discard(Long cardId) {
        Achievement achievement = cardRepository.findById(cardId).orElseThrow();
        cardRepository.delete(achievement); // 或设置软删除标志
    }
}

