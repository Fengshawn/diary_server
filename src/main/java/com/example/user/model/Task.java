package com.example.user.model;

import com.example.user.enums.TaskIntentType;
import com.example.user.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task",
    indexes = {
        @Index(name = "idx_task_user_status_lastactive",
               columnList = "initiator_user_id, status, last_active_at"),
        @Index(name = "idx_task_channel_lastactive",
               columnList = "channel_id, last_active_at")
    }
)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 任务发起人，任务栏仅本人可见 */
    @Column(name = "initiator_user_id", nullable = false)
    private Long initiatorUserId;

    /** 所属频道 */
    @Column(name = "channel_id")
    private Long channelId;

    /** 触发该任务的用户消息，卡片锚点 */
    @Column(name = "source_message_id")
    private Long sourceMessageId;

    /** 意图分类 */
    @Enumerated(EnumType.STRING)
    @Column(name = "intent_type", nullable = false, length = 20)
    private TaskIntentType intentType;

    /** 任务状态状态机：PENDING → RUNNING ↔ PAUSED → COMPLETED/FAILED/CANCELED */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TaskStatus status = TaskStatus.PENDING;

    /** 任务名称，10字以内 */
    @Column(name = "title", length = 40)
    private String title;

    /** 目标一句话描述 */
    @Column(name = "goal_summary", length = 500)
    private String goalSummary;

    /** AI 用于任务归属判断的上下文摘要 */
    @Lob
    @Column(name = "context_summary")
    private String contextSummary;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "last_active_at")
    private LocalDateTime lastActiveAt;

    /** 累计耗时（秒），暂停/恢复后累加 */
    @Column(name = "total_elapsed_seconds", nullable = false, columnDefinition = "bigint default 0")
    private Long totalElapsedSeconds = 0L;

    /** 失败原因 */
    @Column(name = "failure_reason", length = 1000)
    private String failureReason;

    /** 下沉到历史任务的时间 */
    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
