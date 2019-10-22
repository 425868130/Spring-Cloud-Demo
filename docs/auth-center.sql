create schema `auth-center`;

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255)        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`   bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `role` varchar(255)        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(255)        NOT NULL,
    `password` varchar(255)        NOT NULL,
    `salt`     varchar(255)        NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `role`       bigint(20) unsigned NOT NULL,
    `permission` bigint(20) unsigned NOT NULL,
    UNIQUE KEY `role` (`role`, `permission`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

###

INSERT INTO `sys_permission`
VALUES (1, 'Retrieve');
INSERT INTO `sys_permission`
VALUES (2, 'Create');
INSERT INTO `sys_permission`
VALUES (3, 'Update');
INSERT INTO `sys_permission`
VALUES (4, 'Delete');
INSERT INTO `sys_role`
VALUES (1, 'guest');
INSERT INTO `sys_role`
VALUES (2, 'user');
INSERT INTO `sys_role`
VALUES (3, 'admin');
INSERT INTO `role_permission`
VALUES (1, 1);
INSERT INTO `role_permission`
VALUES (1, 2);
INSERT INTO `role_permission`
VALUES (2, 2);
INSERT INTO `role_permission`
VALUES (3, 2);
INSERT INTO `role_permission`
VALUES (1, 3);
INSERT INTO `role_permission`
VALUES (2, 3);
INSERT INTO `role_permission`
VALUES (3, 3);
INSERT INTO `role_permission`
VALUES (4, 3);