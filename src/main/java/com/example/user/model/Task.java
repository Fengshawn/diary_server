package com.example.user.model;

import com.example.user.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "task", indexes = {
    @Index(name = "idx_task_user_id", columnList = "userId"),
    @Index(name = "idx_task_user_status", columnList = "userId, status"),
    @Index(name = "idx_task_last_active", columnList = "userId, lastActiveAt")
})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long channelId;

    @Column(length = 255)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status = TaskStatus.ACTIVE;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String context;

    @Column(nullable = false)
    private LocalDateTime lastActiveAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
