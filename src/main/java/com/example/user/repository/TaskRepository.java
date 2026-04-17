package com.example.user.repository;

import com.example.user.enums.TaskStatus;
import com.example.user.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /** 任务栏：查询某用户的所有任务，按最近活跃倒序 */
    List<Task> findByInitiatorUserIdOrderByLastActiveAtDesc(Long initiatorUserId);

    /** 任务栏进行中任务（排除已下沉历史） */
    List<Task> findByInitiatorUserIdAndArchivedAtIsNullOrderByLastActiveAtDesc(Long initiatorUserId);

    /** 任务栏历史任务（已下沉） */
    List<Task> findByInitiatorUserIdAndArchivedAtIsNotNullOrderByLastActiveAtDesc(Long initiatorUserId);

    /** 查询某用户特定状态的任务（用于归属判断） */
    List<Task> findByInitiatorUserIdAndStatusIn(Long initiatorUserId, List<TaskStatus> statuses);

    /** 按频道和活跃时间查询 */
    List<Task> findByChannelIdOrderByLastActiveAtDesc(Long channelId);

    /** 查询超过指定时间无更新的进行中任务（用于自动下沉） */
    List<Task> findByStatusAndLastActiveAtBefore(TaskStatus status, LocalDateTime threshold);
}
