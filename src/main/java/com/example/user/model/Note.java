package com.example.user.model;

import com.example.user.enums.NoteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "notes")
public class Note {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String title;

    @Lob
    private String content;

    private String imageUrl;

    private Boolean isSaved = false;

    @Enumerated(EnumType.STRING)
    private NoteStatus status; // DRAFT, CONFIRMED

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // getters/setters
}

