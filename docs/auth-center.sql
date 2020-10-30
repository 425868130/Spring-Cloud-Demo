SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_info
-- ----------------------------
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`
(
    `uid`         bigint unsigned                                               NOT NULL COMMENT '用户唯一标识',
    `account`     varchar(255)                                                  NOT NULL DEFAULT '' COMMENT '账户名,用于登录识别',
    `nick_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
    `real_name`   varchar(32)                                                   NOT NULL DEFAULT '' COMMENT '用户真实姓名',
    `sex`         tinyint                                                       NOT NULL DEFAULT '-1' COMMENT '性别:1男0女-1未知',
    `email`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       NOT NULL DEFAULT '' COMMENT '邮箱',
    `telephone`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci        NOT NULL DEFAULT '' COMMENT '电话',
    `head_img`    varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL DEFAULT '' COMMENT '用户头像',
    `location`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       NOT NULL DEFAULT '' COMMENT '所属地域',
    `birthday`    date                                                                   DEFAULT NULL COMMENT '生日',
    `status_id`   int                                                           NOT NULL DEFAULT '0' COMMENT '状态id',
    `deleted`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除 1是 0 否',
    `create_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` timestamp                                                     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间，只在deleted字段为1时有意义',
    PRIMARY KEY (`uid`) USING BTREE,
    KEY `account_name` (`account`),
    KEY `location` (`location`),
    KEY `birthday` (`birthday`),
    KEY `status_id` (`status_id`),
    KEY `deleted` (`deleted`),
    KEY `account_info_sex_index` (`sex`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for account_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `account_role_relation`;
CREATE TABLE `account_role_relation`
(
    `uid`         bigint unsigned NOT NULL COMMENT '用户id',
    `role_id`     bigint          NOT NULL COMMENT '角色id',
    `create_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY `uid_role_id` (`uid`, `role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for account_secret_profile
-- ----------------------------
DROP TABLE IF EXISTS `account_secret_profile`;
CREATE TABLE `account_secret_profile`
(
    `uid`         bigint unsigned                                               NOT NULL COMMENT '用户唯一标识',
    `password`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci       NOT NULL COMMENT '密码',
    `id_card`     varchar(256)                                                  NOT NULL DEFAULT '' COMMENT '身份证信息,加密存储',
    `salt`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码加密盐',
    `create_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`uid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for account_status
-- ----------------------------
DROP TABLE IF EXISTS `account_status`;
CREATE TABLE `account_status`
(
    `uid`         bigint unsigned NOT NULL COMMENT '用户唯一标识',
    `status_id`   int unsigned    NOT NULL DEFAULT '0' COMMENT 'uid自定义状态,关联user_status表id',
    `create_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY `uid` (`uid`, `status_id`) USING BTREE COMMENT '一个用户账号的同一状态只有一个'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for account_status_define
-- ----------------------------
DROP TABLE IF EXISTS `account_status_define`;
CREATE TABLE `account_status_define`
(
    `id`          int unsigned                                                   NOT NULL AUTO_INCREMENT COMMENT '状态id',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '状态名称',
    `description` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注说明',
    `create_time` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`
(
    `id`              int unsigned                                                   NOT NULL AUTO_INCREMENT,
    `organization_id` int unsigned                                                   NOT NULL COMMENT '所属组织id',
    `parent_id`       int unsigned                                                   NOT NULL COMMENT '父公司/租户id',
    `name`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '公司/租户名称',
    `description`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '备注说明',
    `logo`            varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'logo图片地址',
    `status`          int                                                            NOT NULL DEFAULT '0' COMMENT '状态定义',
    `create_time`     timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `organization_id` (`organization_id`, `status`),
    KEY `parent_id` (`parent_id`, `status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`
(
    `id`              int unsigned                                                   NOT NULL AUTO_INCREMENT,
    `organization_id` int unsigned                                                   NOT NULL COMMENT '所属组织id',
    `company_id`      int unsigned                                                   NOT NULL COMMENT '所属公司id',
    `parent_id`       int unsigned                                                   NOT NULL COMMENT '父部门id',
    `name`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '部门名称',
    `description`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '备注说明',
    `logo`            varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'logo图片地址',
    `status`          int                                                            NOT NULL DEFAULT '0' COMMENT '状态定义',
    `create_time`     timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `parent_id` (`parent_id`, `status`),
    KEY `organization_id` (`organization_id`, `company_id`, `status`),
    KEY `company_id` (`company_id`, `status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for department_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `department_user_relation`;
CREATE TABLE `department_user_relation`
(
    `uid`           bigint unsigned NOT NULL COMMENT '用户id主键',
    `department_id` bigint unsigned NOT NULL COMMENT '部门id',
    `status`        int             NOT NULL DEFAULT '0' COMMENT '状态定义',
    `create_time`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY `uid` (`uid`, `department_id`),
    KEY `uid_2` (`uid`, `department_id`, `status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`
(
    `id`            bigint unsigned                                                NOT NULL AUTO_INCREMENT,
    `department_id` int unsigned                                                   NOT NULL COMMENT '所属部门id',
    `name`          varchar(255)                                                   NOT NULL COMMENT '小组名称',
    `description`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '备注说明',
    `logo`          varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'logo图片地址',
    `status`        int                                                            NOT NULL DEFAULT '0' COMMENT '状态定义',
    `create_time`   timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `department_id` (`department_id`, `status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for group_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `group_role_relation`;
CREATE TABLE `group_role_relation`
(
    `group_id`    bigint unsigned NOT NULL COMMENT '小组id',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色id',
    `status`      int             NOT NULL DEFAULT '0' COMMENT '状态定义',
    `create_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY `group_id_role_id` (`group_id`, `role_id`),
    KEY `group_id_role_id_status` (`group_id`, `role_id`, `status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for group_user_relation
-- ----------------------------
DROP TABLE IF EXISTS `group_user_relation`;
CREATE TABLE `group_user_relation`
(
    `uid`         bigint          NOT NULL COMMENT '用户id主键',
    `group_id`    bigint unsigned NOT NULL COMMENT '小组id',
    `status`      int             NOT NULL DEFAULT '0' COMMENT '状态定义',
    `create_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    UNIQUE KEY `uid` (`uid`, `group_id`) USING BTREE,
    KEY `uid_2` (`uid`, `group_id`, `status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户小组关联关系';

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`
(
    `id`          int unsigned                                                   NOT NULL AUTO_INCREMENT,
    `parent_id`   int unsigned                                                   NOT NULL COMMENT '父组织id',
    `name`        varchar(255)                                                   NOT NULL COMMENT '组织名称',
    `description` varchar(255)                                                   NOT NULL COMMENT '备注说明',
    `logo`        varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组织logo图片地址',
    `status`      int                                                            NOT NULL DEFAULT '0' COMMENT '状态定义',
    `create_time` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `status` (`status`),
    KEY `parent_id` (`parent_id`, `status`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `role`       bigint unsigned NOT NULL,
    `permission` bigint unsigned NOT NULL,
    UNIQUE KEY `role` (`role`, `permission`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`          bigint unsigned                                                NOT NULL,
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '权限名称',
    `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '描述说明',
    `status`      int                                                            NOT NULL DEFAULT '0' COMMENT '权限状态定义',
    `expire_time` timestamp                                                      NOT NULL COMMENT '过期时间',
    `create_time` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `status` (`status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
