package com.example.user.repository;

import com.example.user.model.User;
import com.example.user.model.UserGlow;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserGlowRepository extends JpaRepository<UserGlow, Long> {

    Optional<UserGlow> findByUserId(Long userId);

}
