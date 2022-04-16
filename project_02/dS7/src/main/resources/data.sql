INSERT INTO `springboot`.`grade`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('紧急', 'bug严重程度', 1, 1, now(), now());
INSERT INTO `springboot`.`grade`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('重要', 'bug严重程度', 1, 1, now(), now());
INSERT INTO `springboot`.`grade`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('一般', 'bug严重程度', 1, 1, now(), now());
INSERT INTO `springboot`.`grade`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('低', 'bug严重程度', 1, 1, now(), now());

INSERT INTO `springboot`.`bug`(`title`, `report_user_id`, `grade_id`, `description`, `create_by`, `update_by`,
                               `create_time`, `update_time`)
VALUES ('胖蛋跨栏禁赛', 1, 1, '缺陷记录', 1, 1, now(), now());
INSERT INTO `springboot`.`bug`(`title`, `report_user_id`, `grade_id`, `description`, `create_by`, `update_by`,
                               `create_time`, `update_time`)
VALUES ('三胖做了手术', 1, 2, '缺陷记录', 1, 1, now(), now());
INSERT INTO `springboot`.`bug`(`title`, `report_user_id`, `grade_id`, `description`, `create_by`, `update_by`,
                               `create_time`, `update_time`)
VALUES ('二没睡醒', 1, 3, '缺陷记录', 1, 1, now(), now());
INSERT INTO `springboot`.`bug`(`title`, `report_user_id`, `grade_id`, `description`, `create_by`, `update_by`,
                               `create_time`, `update_time`)
VALUES ('等二睡醒', 1, 4, '缺陷记录', 1, 1, now(), now());

INSERT INTO `springboot`.`version`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('v1.0.0', '版本v1.0.0', 1, 1, now(), now());
INSERT INTO `springboot`.`version`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('v2.0.0', '版本v2.0.0', 1, 1, now(), now());
INSERT INTO `springboot`.`version`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('v3.0.0', '版本v3.0.0', 1, 1, now(), now());
INSERT INTO `springboot`.`version`(`name`, `description`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES ('v4.0.0', '版本v4.0.0', 1, 1, now(), now());

INSERT INTO `springboot`.`bug_version`(`bug_id`, `version_id`)
VALUES (1, 4);
INSERT INTO `springboot`.`bug_version`(`bug_id`, `version_id`)
VALUES (2, 3);
INSERT INTO `springboot`.`bug_version`(`bug_id`, `version_id`)
VALUES (3, 2);
INSERT INTO `springboot`.`bug_version`(`bug_id`, `version_id`)
VALUES (4, 1);

INSERT INTO `springboot`.`user`(`username`, `name`, `password`, `description`, `create_by`, `update_by`, `create_time`,
                                `update_time`)
VALUES ('胖蛋', 'Pandan', '123456', '用户记录', 1, 1, now(), now());
INSERT INTO `springboot`.`user`(`username`, `name`, `password`, `description`, `create_by`, `update_by`, `create_time`,
                                `update_time`)
VALUES ('凯蒂猫', 'HelloKitty', '123456', '用户记录', 1, 1, now(), now());
INSERT INTO `springboot`.`user`(`username`, `name`, `password`, `description`, `create_by`, `update_by`, `create_time`,
                                `update_time`)
VALUES ('三胖', 'SanPang', '123456', '用户记录', 1, 1, now(), now());
INSERT INTO `springboot`.`user`(`username`, `name`, `password`, `description`, `create_by`, `update_by`, `create_time`,
                                `update_time`)
VALUES ('小二', 'XiaoEr', '123456', '用户记录', 1, 1, now(), now());

