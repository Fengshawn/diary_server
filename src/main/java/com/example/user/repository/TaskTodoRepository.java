package com.example.user.repository;

import com.example.user.enums.TodoStatus;
import com.example.user.model.TaskTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskTodoRepository extends JpaRepository<TaskTodo, Long> {

    /** 查询某任务下所有 Todo，按序号正序（用于展示 Todo 列表） */
    List<TaskTodo> findByTaskIdOrderBySeqNoAsc(Long taskId);

    /** 查询某 AgentRun 下所有 Todo，按序号正序 */
    List<TaskTodo> findByAgentRunIdOrderBySeqNoAsc(Long agentRunId);

    /** 查询某任务当前正在执行的 Todo */
    Optional<TaskTodo> findByTaskIdAndStatus(Long taskId, TodoStatus status);

    /** 查询某任务下已完成的 Todo 数量 */
    long countByTaskIdAndStatus(Long taskId, TodoStatus status);
}
