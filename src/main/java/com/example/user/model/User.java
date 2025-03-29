package com.example.user.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "union_id", unique = true, nullable = false, length = 10)
    private String unionId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image", nullable = false, columnDefinition = "varchar(255) default '-'")
    private String image = "-";

    @Column(name = "name")
    private String name;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "status", nullable = false, columnDefinition = "int default 0")
    private int status = 0;

    @Column(name = "type", nullable = false, columnDefinition = "int default 0")
    private int type = 0;

    @Column(name = "login_type", nullable = false, columnDefinition = "int default 0")
    private int loginType = 0;

    @Column(name = "introduce", nullable = false, columnDefinition = "varchar(255) default ''")
    private String introduce = "";

    @Column(name = "create_date", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createDate = new Date();

    @Column(name = "update_date", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date updateDate = new Date();

    @Column(name = "delete_date")
    private Date deleteDate;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "phone")
    private String phone;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "invitation_code", unique = true, nullable = false)
    private String invitationCode;

    @Column(name = "apple_id")
    private String appleId;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "app_origin", columnDefinition = "varchar(255) default '20000'")
    private String appOrigin = "20000";
}