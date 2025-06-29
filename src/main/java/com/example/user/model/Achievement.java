package com.example.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "achievement")
public class Achievement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long noteId;
    private Long userId;

    private String achievementTitle;

    @Lob
    private String achievementDescription;

    private String achievementTags; // 可用JSON字符串存 ["成长", "坚持"]

    private Boolean isReceived = false;

    @Lob
    private String llmRawOutput;

    private LocalDateTime createdAt = LocalDateTime.now();

}

