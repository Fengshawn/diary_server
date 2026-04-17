package com.example.user.repository;

import com.example.user.enums.RecommendationStatus;
import com.example.user.model.TaskRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRecommendationRepository extends JpaRepository<TaskRecommendation, Long> {

    /** 查询某任务当前激活的推荐（业务约束：同一任务只有一条 ACTIVE） */
    Optional<TaskRecommendation> findByTaskIdAndStatus(Long taskId, RecommendationStatus status);

    /** 查询某任务的全部推荐历史 */
    List<TaskRecommendation> findByTaskIdOrderByCreatedAtDesc(Long taskId);

    /** 判断某任务是否存在激活的推荐 */
    boolean existsByTaskIdAndStatus(Long taskId, RecommendationStatus status);
}
