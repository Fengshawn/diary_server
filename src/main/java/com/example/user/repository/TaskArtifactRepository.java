package com.example.user.repository;

import com.example.user.enums.ArtifactPhase;
import com.example.user.enums.ArtifactType;
import com.example.user.model.TaskArtifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskArtifactRepository extends JpaRepository<TaskArtifact, Long> {

    /** 查询某任务下所有产出物（过程+最终），按创建时间正序（卡片堆叠顺序） */
    List<TaskArtifact> findByTaskIdOrderByCreatedAtAsc(Long taskId);

    /** 查询某任务下最终交付产出物 */
    List<TaskArtifact> findByTaskIdAndPhase(Long taskId, ArtifactPhase phase);

    /** 查询某任务下特定类型的产出物 */
    List<TaskArtifact> findByTaskIdAndArtifactType(Long taskId, ArtifactType artifactType);

    /** 查询某 Todo 下的过程产出物（逐步交付展示） */
    List<TaskArtifact> findByTodoIdOrderByCreatedAtAsc(Long todoId);

    /** 查询会话流中可见的产出物 */
    List<TaskArtifact> findByTaskIdAndIsVisibleInChatTrueOrderByCreatedAtAsc(Long taskId);
}
