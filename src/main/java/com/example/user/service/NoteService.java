package com.example.user.service;

import com.example.user.dto.NoteDTO;
import com.example.user.enums.NoteStatus;
import com.example.user.model.Note;
import com.example.user.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Note saveNote(NoteDTO dto) {
        Note note;
        if (dto.getId() != null) {
            note = noteRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("笔记不存在"));
        } else {
            note = new Note();
            note.setUserId(dto.getUserId());
            note.setCreatedAt(LocalDateTime.now());
        }

        note.setContent(dto.getContent());
        note.setImageUrl(dto.getImageUrl());
        note.setIsSaved(dto.getIsSaved());

        if (dto.getIsSaved() != null && dto.getIsSaved()) {
            note.setStatus(NoteStatus.CONFIRMED);
        } else {
            note.setStatus(NoteStatus.DRAFT);
        }

        note.setUpdatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public String saveNoteImage(Long noteId, MultipartFile file) {
        // Todo 上传图片到 OSS 或本地磁盘（此处仅伪代码）
        String imageUrl = uploadToStorage(file);

        Note note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("笔记不存在"));
        note.setImageUrl(imageUrl);
        note.setUpdatedAt(LocalDateTime.now());
        noteRepository.save(note);

        return imageUrl;
    }

    private String uploadToStorage(MultipartFile file) {
        // Todo 上传图片到 OSS 或本地磁盘（此处仅伪代码）简化模拟上传 建议抽象成 StorageService
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        // 实际可上传至阿里云、S3 或本地 static 目录
        return "https://your.cdn/" + filename;
    }
}

