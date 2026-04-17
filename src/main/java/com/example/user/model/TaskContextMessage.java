package com.example.user.model;

import com.example.user.enums.ContextRelationType;
import com.example.user.enums.MessageRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_context_message",
    uniqueConstraints = {
        @UniqueConstraint(name = "uniq_task_message",
                          columnNames = {"task_id", "message_id"})
    }
)
public class TaskContextMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    /** 关联的会话消息 ID */
    @Column(name = "message_id", nullable = false)
    private Long messageId;

    /** user 或 assistant */
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private MessageRole role;

    /** 被引用的原始消息 ID（引用消息场景） */
    @Column(name = "quoted_message_id")
    private Long quotedMessageId;

    /** 本条消息与任务的关联类型 */
    @Enumerated(EnumType.STRING)
    @Column(name = "relation_type", nullable = false, length = 20)
    private ContextRelationType relationType;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
}
