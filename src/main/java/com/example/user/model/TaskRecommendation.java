package com.example.user.model;

import com.example.user.enums.RecommendationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_recommendation",
    indexes = {
        @Index(name = "idx_recommendation_task_status",
               columnList = "task_id, status")
    }
)
public class TaskRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    /** 本组推荐基于哪条 AI 消息产生 */
    @Column(name = "based_on_message_id")
    private Long basedOnMessageId;

    /** 选项 A：对当前产出物的优化/迭代 */
    @Column(name = "option_a_text", nullable = false, length = 500)
    private String optionAText;

    /** 选项 B：基于当前产出物的延伸/推进 */
    @Column(name = "option_b_text", nullable = false, length = 500)
    private String optionBText;

    /**
     * ACTIVE=当前展示；EXPIRED=已过期；CLICKED=用户点击了选项
     * 同一任务同一时刻只有一条 ACTIVE 记录（业务层保证）
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private RecommendationStatus status = RecommendationStatus.ACTIVE;

    /** 推荐过期时间（新消息产生时设置为当前时间使其失效） */
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
}
