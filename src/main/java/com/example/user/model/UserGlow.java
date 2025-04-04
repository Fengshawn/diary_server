package com.example.user.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "user_glow")
@Data
public class UserGlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int glowPoint;

    private int totalRecords;

    private int totalDialogues;

    private int glowLevel;

    private Date lastUpdated;
}
