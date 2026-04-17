package com.example.user.model;

import com.example.user.enums.StepStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_todo_step",
    indexes = {
        @Index(name = "idx_step_todo_id", columnList = "todo_id")
    }
)
public class TaskTodoStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    /** 在所属 Todo 内的顺序（1-based） */
    @Column(name = "seq_no", nullable = false)
    private Integer seqNo;

    /** 工具名称，如 browser_use / code_interpreter */
    @Column(name = "tool_name", nullable = false, length = 100)
    private String toolName;

    /** 工具类型分类 */
    @Column(name = "tool_type", length = 100)
    private String toolType;

    /** 第二层展示：用户可读的工具摘要（一句话，含关键信息） */
    @Column(name = "summary_text", length = 500)
    private String summaryText;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StepStatus status = StepStatus.PENDING;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    /** 量化指标：数量 */
    @Column(name = "metric_count")
    private Long metricCount;

    /** 量化指标：数据大小（字节） */
    @Column(name = "metric_size")
    private Long metricSize;

    /** 量化指标：耗时（毫秒） */
    @Column(name = "metric_duration_ms")
    private Long metricDurationMs;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
