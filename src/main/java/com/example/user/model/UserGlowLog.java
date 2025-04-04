package com.example.user.model;

import com.example.user.enums.GlowChangeType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "user_glow_log")
@Data
public class UserGlowLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private GlowChangeType changeType;

    private int changeValue;

    private int currentTotal;

    private String description;

    private Date createdAt;
}
