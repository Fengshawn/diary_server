package com.example.user.repository;

import com.example.user.enums.StepStatus;
import com.example.user.model.TaskTodoStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskTodoStepRepository extends JpaRepository<TaskTodoStep, Long> {

    /** 查询某 Todo 下所有 Step，按序号正序（用于第二层展示） */
    List<TaskTodoStep> findByTodoIdOrderBySeqNoAsc(Long todoId);

    /** 查询某 Todo 下当前正在执行的 Step */
    Optional<TaskTodoStep> findByTodoIdAndStatus(Long todoId, StepStatus status);

    /** 查询某 Todo 下已完成的 Step（展开但不显示第三层） */
    List<TaskTodoStep> findByTodoIdAndStatusOrderBySeqNoAsc(Long todoId, StepStatus status);
}
