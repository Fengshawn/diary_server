package com.example.user.repository;

import com.example.user.model.TaskCardSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCardSnapshotRepository extends JpaRepository<TaskCardSnapshot, Long> {

    /** 批量查询多个任务的卡片快照（任务栏列表一次性加载） */
    List<TaskCardSnapshot> findByTaskIdIn(List<Long> taskIds);
}
