package com.example.user.model;

import com.example.user.enums.TodoStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_todo",
    uniqueConstraints = {
        @UniqueConstraint(name = "uniq_task_seq",
                          columnNames = {"task_id", "seq_no"})
    },
    indexes = {
        @Index(name = "idx_todo_task_id", columnList = "task_id"),
        @Index(name = "idx_todo_agent_run_id", columnList = "agent_run_id")
    }
)
public class TaskTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "agent_run_id", nullable = false)
    private Long agentRunId;

    /** 执行顺序（1-based），严格有序 */
    @Column(name = "seq_no", nullable = false)
    private Integer seqNo;

    /** 第一层展示文案：动名词描述，无技术术语 */
    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TodoStatus status = TodoStatus.PENDING;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    /** Todo 完成后的结果摘要，给前端卡片展示 */
    @Column(name = "result_summary", length = 1000)
    private String resultSummary;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
