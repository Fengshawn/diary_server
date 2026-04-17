package com.example.user.repository;

import com.example.user.enums.ToolRunStatus;
import com.example.user.model.TaskToolRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskToolRunRepository extends JpaRepository<TaskToolRun, Long> {

    /** 查询某 Step 下的所有工具运行记录（第三层，默认折叠） */
    List<TaskToolRun> findByStepId(Long stepId);

    /** 查询某 Step 下特定状态的工具运行 */
    List<TaskToolRun> findByStepIdAndStatus(Long stepId, ToolRunStatus status);

    /** 查询某 Step 下最新一次工具运行 */
    Optional<TaskToolRun> findTopByStepIdOrderByStartedAtDesc(Long stepId);
}
