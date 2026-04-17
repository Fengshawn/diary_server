package com.example.user.model;

import com.example.user.enums.CardEventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_event",
    indexes = {
        @Index(name = "idx_task_event_task_time",
               columnList = "task_id, occurred_at")
    }
)
public class TaskEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    /** 关联的 Agent 执行记录（可为空，如任务级事件） */
    @Column(name = "agent_run_id")
    private Long agentRunId;

    /** 关联的 Todo（可为空） */
    @Column(name = "todo_id")
    private Long todoId;

    /** 关联的 Step（可为空） */
    @Column(name = "step_id")
    private Long stepId;

    /** 事件类型：完整的卡片生命周期 */
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false, length = 50)
    private CardEventType eventType;

    /** 事件附加数据（JSON），用于重放和审计 */
    @Lob
    @Column(name = "event_payload_json")
    private String eventPayloadJson;

    @Column(name = "occurred_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime occurredAt = LocalDateTime.now();
}
