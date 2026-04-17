package com.example.user.model;

import com.example.user.enums.ArtifactPhase;
import com.example.user.enums.ArtifactType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
    name = "task_artifact",
    indexes = {
        @Index(name = "idx_artifact_task_id", columnList = "task_id"),
        @Index(name = "idx_artifact_todo_id", columnList = "todo_id"),
        @Index(name = "idx_artifact_step_id", columnList = "step_id")
    }
)
public class TaskArtifact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    /** 过程产物关联到具体 Todo（可为空，最终交付时为空） */
    @Column(name = "todo_id")
    private Long todoId;

    /** 过程产物关联到具体 Step（可为空） */
    @Column(name = "step_id")
    private Long stepId;

    @Enumerated(EnumType.STRING)
    @Column(name = "artifact_type", nullable = false, length = 20)
    private ArtifactType artifactType;

    /** PROCESS=过程产物；FINAL=最终交付 */
    @Enumerated(EnumType.STRING)
    @Column(name = "phase", nullable = false, length = 20)
    private ArtifactPhase phase;

    /** 产出物标题 */
    @Column(name = "title", length = 500)
    private String title;

    /** 语义化描述文案（100字以内） */
    @Column(name = "semantic_summary", length = 1000)
    private String semanticSummary;

    /** 资源地址 */
    @Column(name = "uri", length = 2048)
    private String uri;

    /** 缩略图地址，用于卡片展示 */
    @Column(name = "thumbnail_uri", length = 2048)
    private String thumbnailUri;

    /** 文件大小（字节） */
    @Column(name = "size_bytes")
    private Long sizeBytes;

    /** 版本号，支持迭代产出 */
    @Column(name = "version_no", nullable = false, columnDefinition = "int default 1")
    private Integer versionNo = 1;

    /** 是否在会话流中可见 */
    @Column(name = "is_visible_in_chat", nullable = false,
            columnDefinition = "tinyint(1) default 1")
    private Boolean isVisibleInChat = true;

    /** 是否为最终置顶交付物 */
    @Column(name = "is_pinned_final", nullable = false,
            columnDefinition = "tinyint(1) default 0")
    private Boolean isPinnedFinal = false;

    @Column(name = "created_at", nullable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
}
