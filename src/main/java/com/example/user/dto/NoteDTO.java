package com.example.user.dto;

import lombok.Data;

@Data
public class NoteDTO {
    private Long id;
    private Long userId;
    private String content;
    private String imageUrl;
    private Boolean isSaved;
}
