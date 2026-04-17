package com.example.user.model;

import com.example.user.enums.AgentRunStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_agent_run",
    indexes = {
        @Index(name = "idx_agent_run_task_id", columnList = "task_id")
    }
)
public class TaskAgentRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    /** Agent 标识（业务 ID / slug） */
    @Column(name = "agent_id", nullable = false, length = 100)
    private String agentId;

    /**
     * 与卡片左上角标签一一对应：
     * ANALYZING=分析中 / PLANNING=规划中 / WORKING=工作中 /
     * PAUSED=暂停 / COMPLETED=已完成 / FAILED=失败
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AgentRunStatus status = AgentRunStatus.ANALYZING;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    /** 当前正在执行的 Todo ID */
    @Column(name = "current_todo_id")
    private Long currentTodoId;

    /** 卡片当前浮出所在的消息 ID（最新位置） */
    @Column(name = "latest_card_message_id")
    private Long latestCardMessageId;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
