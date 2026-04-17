package com.example.user.model;

import com.example.user.enums.AgentRunStatus;
import com.example.user.enums.CardFocusLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "task_card_snapshot")
public class TaskCardSnapshot {

    /** task_id 即为主键，一个任务只有一份快照 */
    @Id
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 前端卡片左上角标签，直接映射 AgentRunStatus：
     * ANALYZING=分析中 / PLANNING=规划中 / WORKING=工作中 /
     * COMPLETED=已完成 / PAUSED=暂停 / FAILED=失败
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "current_status_label", nullable = false, length = 20)
    private AgentRunStatus currentStatusLabel;

    /** 卡片当前聚焦层级，决定面板默认展开位置 */
    @Enumerated(EnumType.STRING)
    @Column(name = "current_focus_level", length = 20)
    private CardFocusLevel currentFocusLevel;

    /** 卡片在会话流中的原始锚点消息 ID */
    @Column(name = "anchor_message_id")
    private Long anchorMessageId;

    /** 卡片最新浮出位置的消息 ID（任务完成/等待反馈时更新） */
    @Column(name = "floating_message_id")
    private Long floatingMessageId;

    /** 卡片是否已折叠收起 */
    @Column(name = "is_collapsed", nullable = false,
            columnDefinition = "tinyint(1) default 0")
    private Boolean isCollapsed = false;

    /** 卡片中间区域的最新预览文本（思考链/Todo名/工具摘要/结果） */
    @Column(name = "latest_preview_text", length = 1000)
    private String latestPreviewText;

    @Column(name = "updated_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
