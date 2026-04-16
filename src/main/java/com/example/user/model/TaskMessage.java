package com.example.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "task_message", indexes = {
    @Index(name = "idx_task_message_task_id", columnList = "taskId"),
    @Index(name = "idx_task_message_message_id", columnList = "messageId"),
    @Index(name = "idx_task_message_channel_id", columnList = "channelId")
})
public class TaskMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long taskId;

    @Column(nullable = false)
    private Long messageId;

    @Column(nullable = false)
    private Long channelId;

    @Column(nullable = false)
    private Long userId;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    private Long quotedMessageId;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String quotedContent;

    @Column(nullable = false)
    private Integer sequenceOrder;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
