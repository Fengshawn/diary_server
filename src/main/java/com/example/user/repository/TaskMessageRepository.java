package com.example.user.repository;

import com.example.user.model.TaskMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskMessageRepository extends JpaRepository<TaskMessage, Long> {

    List<TaskMessage> findByTaskIdOrderBySequenceOrderAsc(Long taskId);

    Optional<TaskMessage> findTopByTaskIdOrderBySequenceOrderDesc(Long taskId);

    Optional<TaskMessage> findTopByTaskIdOrderByCreatedAtDesc(Long taskId);
}
