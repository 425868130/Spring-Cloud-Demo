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