drop table if exists grade;
CREATE TABLE `grade`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        VARCHAR(64)  NOT NULL COMMENT '名称',
    `description` varchar(128) NOT NULL COMMENT '等级描述',
    `create_by`   int(11) NOT NULL COMMENT '创建人',
    `update_by`   int(11) NOT NULL COMMENT '修改人',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='等级表';

drop table if exists bug;
CREATE TABLE `bug`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`          VARCHAR(64)  NOT NULL COMMENT 'bug标题',
    `report_user_id` int(11) NOT NULL COMMENT '缺陷报告创建人id',
    `grade_id`       int(11) NOT NULL COMMENT '缺陷报告创建人id',
    `description`    varchar(128) NOT NULL COMMENT '缺陷描述',
    `create_by`      int(11) NOT NULL COMMENT '创建人',
    `update_by`      int(11) NOT NULL COMMENT '修改人',
    `create_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='缺陷表';

drop table if exists `version`;
CREATE TABLE `version`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        VARCHAR(64)  NOT NULL COMMENT '版本名称',
    `description` varchar(128) NOT NULL COMMENT '版本描述',
    `create_by`   int(11) NOT NULL COMMENT '创建人',
    `update_by`   int(11) NOT NULL COMMENT '修改人',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='版本表';

drop table if exists bug_version;
CREATE TABLE `bug_version`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `bug_id`     int(11) NOT NULL COMMENT '缺陷id',
    `version_id` int(11) NOT NULL COMMENT '版本id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='缺陷版本关系表';

drop table if exists user;
CREATE TABLE `user`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`    VARCHAR(64)  NOT NULL COMMENT '用户名',
    `name`        VARCHAR(64)  NOT NULL COMMENT '姓名',
    `password`    VARCHAR(64)  NOT NULL COMMENT '密码',
    `description` varchar(128) NOT NULL COMMENT '描述',
    `create_by`   int(11) NOT NULL COMMENT '创建人',
    `update_by`   int(11) NOT NULL COMMENT '修改人',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='用户表';

