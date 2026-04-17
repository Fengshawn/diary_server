package com.example.user.repository;

import com.example.user.enums.MessageRole;
import com.example.user.model.TaskContextMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskContextMessageRepository extends JpaRepository<TaskContextMessage, Long> {

    /** 查询某任务的全部上下文消息（按创建时间正序） */
    List<TaskContextMessage> findByTaskIdOrderByCreatedAtAsc(Long taskId);

    /** 查询某任务中特定角色的消息 */
    List<TaskContextMessage> findByTaskIdAndRole(Long taskId, MessageRole role);

    /** 根据 message_id 查询（用于判断消息是否已绑定某任务） */
    Optional<TaskContextMessage> findByTaskIdAndMessageId(Long taskId, Long messageId);

    /** 查询某 message_id 绑定的任务 ID（用于归属判断） */
    List<TaskContextMessage> findByMessageId(Long messageId);
}
