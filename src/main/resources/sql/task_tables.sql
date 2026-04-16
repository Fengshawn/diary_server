-- =============================================
-- 任务管理模块 SQL 表定义
-- =============================================

-- 任务表：存储用户发起的任务
CREATE TABLE IF NOT EXISTS `task` (
    `id`              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '任务主键ID',
    `user_id`         BIGINT       NOT NULL                COMMENT '任务发起者用户ID（任务归属者）',
    `channel_id`      BIGINT       NOT NULL                COMMENT '任务发起时所在的频道ID',
    `title`           VARCHAR(255) DEFAULT NULL             COMMENT '任务标题（AI 生成或用户自定义）',
    `status`          VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE' COMMENT '任务状态：ACTIVE-进行中, PAUSED-已暂停, COMPLETED-已完成',
    `context`         TEXT                                  COMMENT '任务累积上下文（AI 用于判断消息归属的上下文信息）',
    `last_active_at`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近活跃时间（用于进行任务/历史任务的分区排序）',
    `created_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_task_user_id` (`user_id`),
    INDEX `idx_task_user_status` (`user_id`, `status`),
    INDEX `idx_task_last_active` (`user_id`, `last_active_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务表';

-- 任务消息关联表：记录每条消息与任务的关联关系
CREATE TABLE IF NOT EXISTS `task_message` (
    `id`                BIGINT  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `task_id`           BIGINT  NOT NULL                COMMENT '关联的任务ID',
    `message_id`        BIGINT  NOT NULL                COMMENT '消息ID（频道中的原始消息）',
    `channel_id`        BIGINT  NOT NULL                COMMENT '消息所在的频道ID',
    `user_id`           BIGINT  NOT NULL                COMMENT '消息发送者用户ID',
    `content`           TEXT                            COMMENT '消息内容',
    `quoted_message_id` BIGINT  DEFAULT NULL            COMMENT '被引用的消息ID（如果是引用消息）',
    `quoted_content`    TEXT                            COMMENT '被引用消息的文字内容',
    `sequence_order`    INT     NOT NULL                COMMENT '消息在任务中的顺序编号',
    `created_at`        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_task_message_task_id` (`task_id`),
    INDEX `idx_task_message_message_id` (`message_id`),
    INDEX `idx_task_message_channel_id` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务消息关联表';
