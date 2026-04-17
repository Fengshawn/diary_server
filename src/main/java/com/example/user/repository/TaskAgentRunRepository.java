package com.example.user.repository;

import com.example.user.enums.AgentRunStatus;
import com.example.user.model.TaskAgentRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskAgentRunRepository extends JpaRepository<TaskAgentRun, Long> {

    /** 查询某任务下的所有 Agent 执行记录 */
    List<TaskAgentRun> findByTaskId(Long taskId);

    /** 查询某任务下特定状态的 Agent 执行 */
    List<TaskAgentRun> findByTaskIdAndStatus(Long taskId, AgentRunStatus status);

    /** 查询某任务下最新的 Agent 执行记录 */
    Optional<TaskAgentRun> findTopByTaskIdOrderByCreatedAtDesc(Long taskId);
}
