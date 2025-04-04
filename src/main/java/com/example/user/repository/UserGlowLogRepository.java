package com.example.user.repository;

import com.example.user.model.UserGlowLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserGlowLogRepository extends JpaRepository<UserGlowLog, Long> {

    Optional<UserGlowLog> findByUserId(Long userId);

}