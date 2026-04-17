package com.example.user.repository;

import com.example.user.enums.CardEventType;
import com.example.user.model.TaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskEventRepository extends JpaRepository<TaskEvent, Long> {

    /** 查询某任务的完整事件流（按时间正序，用于审计/重放） */
    List<TaskEvent> findByTaskIdOrderByOccurredAtAsc(Long taskId);

    /** 查询某任务特定类型的事件 */
    List<TaskEvent> findByTaskIdAndEventType(Long taskId, CardEventType eventType);

    /** 查询某 AgentRun 下的事件 */
    List<TaskEvent> findByAgentRunIdOrderByOccurredAtAsc(Long agentRunId);

    /** 查询某时间段内的任务事件（用于多端同步） */
    List<TaskEvent> findByTaskIdAndOccurredAtAfterOrderByOccurredAtAsc(
            Long taskId, LocalDateTime after);
}
