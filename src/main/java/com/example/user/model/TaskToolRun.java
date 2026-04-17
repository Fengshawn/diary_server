package com.example.user.model;

import com.example.user.enums.ToolRunStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_tool_run",
    indexes = {
        @Index(name = "idx_tool_run_step_id", columnList = "step_id")
    }
)
public class TaskToolRun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "step_id", nullable = false)
    private Long stepId;

    /**
     * 第三层：工具调用输入参数（脱敏处理后的 JSON）
     * 默认折叠，需用户手动打开
     */
    @Lob
    @Column(name = "input_payload_json")
    private String inputPayloadJson;

    /** 工具调用输出结果（JSON） */
    @Lob
    @Column(name = "output_payload_json")
    private String outputPayloadJson;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ToolRunStatus status = ToolRunStatus.RUNNING;

    /** 业务错误码 */
    @Column(name = "error_code", length = 100)
    private String errorCode;

    /** 错误描述 */
    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;
}
