package com.example.user.repository;

import com.example.user.enums.TaskStatus;
import com.example.user.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdAndStatusOrderByLastActiveAtDesc(Long userId, TaskStatus status);

    List<Task> findByUserIdOrderByLastActiveAtDesc(Long userId);

    List<Task> findByUserIdAndLastActiveAtAfterAndStatusOrderByLastActiveAtDesc(
            Long userId, LocalDateTime since, TaskStatus status);

    List<Task> findByUserIdAndLastActiveAtBeforeAndStatusOrderByLastActiveAtDesc(
            Long userId, LocalDateTime before, TaskStatus status);
}
