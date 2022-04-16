
-- 【1、创建数据库】 utf8mb4 可以支持类似于emoji等表情格式
CREATE DATABASE IF NOT EXISTS cc_api_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci; 
-- 切换数据库
USE cc_api_platform;

-- 【2、创建数据表】
-- 1、用户表
CREATE TABLE IF NOT EXISTS cc_user
(
    `id`                    INT  NOT NULL AUTO_INCREMENT,  
    `username`              varchar(128) NOT NULL COMMENT '用户名',
    `password`              varchar(128) NOT NULL COMMENT '密码',
    `name`                  varchar(128) NOT NULL COMMENT '姓名',
    `email`                 varchar(128) COMMENT '邮箱',
    `mobile`                varchar(128) COMMENT '手机',
    `roles`                 varchar(500) COMMENT '角色',
    `status`                INT  NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `description`           varchar(2000) COMMENT '描述',
    `current_project_id`    INT COMMENT '当前项目ID',
    `last_login_time`       datetime COMMENT '最后一次登录时间',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
    FOREIGN KEY (current_project_id) REFERENCES cc_project(id),
    CONSTRAINT user_u4 UNIQUE(id,username,email,mobile), -- 是否唯一
    INDEX idx_user_name (name),		-- 定义name为索引
    INDEX idx_user_id (id)  -- 定义id为索引

);
-- 2、项目表
CREATE TABLE IF NOT EXISTS cc_project
(
    `id`                    INT  NOT NULL AUTO_INCREMENT,  
    `name`	                varchar(128)  NOT NULL COMMENT '项目名称',
    `description`	        varchar(2000) COMMENT '描述',
    `status`                INT  NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `owner_id`	            int  NOT NULL COMMENT '责任人ID',
    `owner_name`	        varchar(128)  NOT NULL COMMENT '责任人姓名',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
    FOREIGN KEY (owner_id) REFERENCES cc_user(id),
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT project_u2 UNIQUE(id,name), -- 是否唯一
    INDEX idx_project_name (name),		-- 定义name为索引
    INDEX idx_project_id (id)  -- 定义id为索引
);

-- 3、模块表
CREATE TABLE IF NOT EXISTS cc_module
(
	  `id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `name`	                varchar(128) NOT NULL COMMENT '模块名称',
    `description`	        varchar(2000) COMMENT '描述',
    `status`                INT  NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `project_id`	        int NOT NULL COMMENT '项目ID',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

	PRIMARY KEY (id),		-- 指定主键列
	FOREIGN KEY (project_id) REFERENCES cc_project(id),  -- 指定外键id
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT module_u2 UNIQUE(id,name), -- 是否唯一
    INDEX idx_module_name (name),		-- 定义索引
    INDEX idx_module_id (id)  -- 定义id为索引
);

-- 4、环境表
CREATE TABLE IF NOT EXISTS cc_environment
(
	`id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `name`	                varchar(128) NOT NULL COMMENT '环境名称',
    `host`	                varchar(500) NOT NULL COMMENT '主机',
    `db_config`	            varchar(500) NOT NULL COMMENT '数据库配置',
    `description`	        varchar(2000)  COMMENT '描述',
    `status`                INT  NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `project_id`	        int  NOT NULL COMMENT '项目ID',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
	FOREIGN KEY (project_id) REFERENCES cc_project(id),  -- 指定外键id
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT environment_u2 UNIQUE(id,name), -- 是否唯一
    INDEX idx_environment_name (name),		-- 定义索引
    INDEX idx_environment_id (id)  -- 定义id为索引
);
-- 5、接口表
CREATE TABLE IF NOT EXISTS cc_interface
(
	`id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `name`	                varchar(500) NOT NULL COMMENT '接口名称',
    `path`	                varchar(500) NOT NULL COMMENT '路径',
    `request_method`	    varchar(128) NOT NULL COMMENT '请求方法',
    `response_type`	        varchar(128) NOT NULL COMMENT '响应类型',
    `description`	        varchar(2000) COMMENT '描述',
    `status`                INT NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `developer_id`      	int COMMENT '开发人员ID',
    `developer_name`    	varchar(128) COMMENT '开发姓名',
    `project_id`	        int NOT NULL COMMENT '项目ID',
    `module_id`         	int NOT NULL COMMENT '模块ID',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
	FOREIGN KEY (project_id) REFERENCES cc_project(id),  -- 指定外键id
    FOREIGN KEY (module_id) REFERENCES cc_module(id),
    FOREIGN KEY (developer_id) REFERENCES cc_user(id), 
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT interface_u5 UNIQUE(id),   -- 是否唯一
    INDEX idx_interface_id (id)  -- 定义id为索引
);
-- 6、测试任务表
CREATE TABLE IF NOT EXISTS cc_task
(
    `id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `name`              	varchar(128) NOT NULL COMMENT '任务名称',
    `description`	        varchar(2000) COMMENT '描述',
    `status`                INT NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `project_id`	        int NOT NULL COMMENT '项目ID',
    `is_archive`            int NOT NULL COMMENT '是否归档：false 否,true 是',
    `is_job`                int NOT NULL DEFAULT 0 COMMENT '是否定时执行';
    `archive_id`	        int COMMENT '归档ID',
    `archive_name`	        varchar(128) COMMENT '归档人员姓名',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
	FOREIGN KEY (project_id) REFERENCES cc_project(id),  -- 指定外键id
    FOREIGN KEY (archive_id) REFERENCES cc_user(id),
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT task_u2 UNIQUE(id,name), -- 是否唯一
    INDEX idx_task_name (name),		-- 定义索引
    INDEX idx_task_id (id)  -- 定义id为索引
	
);
-- 7、测试任务-模块表
CREATE TABLE IF NOT EXISTS cc_task_module
(
    `id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `task_id`	              int NOT NULL COMMENT '任务ID',
    `module_id`         	  int NOT NULL COMMENT '模块ID',
    PRIMARY KEY (id),
    FOREIGN KEY (task_id) REFERENCES cc_task(id),
    FOREIGN KEY (module_id) REFERENCES cc_module(id),
    INDEX idx_task_id (id)  -- 定义id为索引
);

-- 8、测试套件表
CREATE TABLE IF NOT EXISTS cc_test_suite
(
	`id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `name`              	varchar(128) NOT NULL COMMENT '套件名称',
    `description`	        varchar(2000) COMMENT '描述',
    `status`                INT NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `project_id`	        int NOT NULL COMMENT '项目ID',
    `task_id`	            int NOT NULL COMMENT '任务ID',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
	FOREIGN KEY (project_id) REFERENCES cc_project(id),  -- 指定外键id
    FOREIGN KEY (task_id) REFERENCES cc_task(id),
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT test_suite_u2 UNIQUE(id,name), -- 是否唯一
    INDEX idx_test_suite_name (name),		-- 定义索引
    INDEX idx_test_suite_id (id)  -- 定义id为索引
);

-- 9、测试用例表
CREATE TABLE IF NOT EXISTS cc_test_case
(
	`id`                    INT  NOT NULL AUTO_INCREMENT,  	
    `name`              	varchar(128) NOT NULL COMMENT '测试用例名称',
    `request_data`      	longtext  COMMENT '请求数据',	
    `extract`	            longtext COMMENT '响应提取',		
    `assertion`         	longtext COMMENT '断言',		
    `db_assertion`      	longtext COMMENT '数据库断言',		
    `marks`	                varchar(200) COMMENT '标记',		
    `description`	        varchar(2000) COMMENT '描述',
    `order_index`	        int NOT NULL COMMENT '执行顺序',
    `status`                INT NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `interface_id`      	int NOT NULL COMMENT '接口ID',
    `module_id`         	int NOT NULL COMMENT '模块ID',
    `test_suite_id`      	int NOT NULL COMMENT '测试套件ID',
    `task_id`	            int NOT NULL COMMENT '任务ID',
    `project_id`	        int NOT NULL COMMENT '项目ID',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
    FOREIGN KEY (interface_id) REFERENCES cc_interface(id), 
    FOREIGN KEY (module_id) REFERENCES cc_module(id),
	FOREIGN KEY (test_suite_id) REFERENCES cc_test_suite(id),
    FOREIGN KEY (task_id) REFERENCES cc_task(id),
    FOREIGN KEY (project_id) REFERENCES cc_project(id),
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT test_case_u2 UNIQUE(id,name), -- 是否唯一
    INDEX idx_test_case_name (name),		-- 定义索引
    INDEX idx_test_case_id (id)  -- 定义id为索引
);
-- 10、测试记录表
CREATE TABLE IF NOT EXISTS cc_test_record
(
	`id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `name`              	varchar(50) COMMENT '测试记录名称',		
    `thread_count`      	int  COMMENT '执行线程数',	
    `description`	        varchar(2000) COMMENT '描述',
    `record_status`         INT NOT NULL COMMENT '执行状态:0表示执行完成,1表示生执行中',
    `is_delete`	            int	NOT NULL COMMENT '是否已删除',	
    `environment_id`       	int  NOT NULL COMMENT '项目环境ID',	
    `task_id`	            int NOT NULL COMMENT '任务ID',
    `project_id`	        int NOT NULL COMMENT '项目ID',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
    FOREIGN KEY (environment_id) REFERENCES cc_environment(id),
    FOREIGN KEY (task_id) REFERENCES cc_task(id),
	FOREIGN KEY (project_id) REFERENCES cc_project(id),  -- 指定外键id
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT test_record_u2 UNIQUE(id,name), -- 是否唯一
    INDEX idx_test_record_name (name),		-- 定义索引
    INDEX idx_test_recordt_id (id)  -- 定义id为索引
);

-- 11、测试报告表
CREATE TABLE IF NOT EXISTS cc_test_report
(
	`id`                    INT  NOT NULL AUTO_INCREMENT,  	-- 设置id列为非空、自增
    `result`	            longtext COMMENT '测试报告结果',	
    `description`	        varchar(2000) COMMENT '描述',
    `status`                INT NOT NULL COMMENT '状态:0启用,1禁用,2删除',
    `test_record_id`	    int NOT NULL COMMENT '测试记录ID',	
    `project_id`	        int NOT NULL COMMENT '项目ID',
    `create_by_id`          INT NOT NULL COMMENT '创建人ID',
    `create_by_name`        varchar(128) NOT NULL COMMENT '创建人姓名',
    `create_time`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by_id`          INT NOT NULL COMMENT '修改人ID',
    `update_by_name`        varchar(128) NOT NULL COMMENT '修改人姓名',
    `update_time`           datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),		-- 指定主键列
    FOREIGN KEY (test_record_id) REFERENCES cc_test_record(id),
	FOREIGN KEY (project_id) REFERENCES cc_project(id),  -- 指定外键id
    FOREIGN KEY (create_by_id) REFERENCES cc_user(id),
    FOREIGN KEY (update_by_id) REFERENCES cc_user(id),
    CONSTRAINT test_report_u1 UNIQUE(id), -- 是否唯一
    INDEX idx_test_report_id (id)  -- 定义id为索引
);




CREATE TABLE `cc_job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cron` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `status` int NOT NULL COMMENT '定时任务状态:1开始,2停止,3已创建',,
  `task_id` int NOT NULL,
  `project_id` int NOT NULL,
  `environment_id` int NOT NULL,
  `xxl_job_id` int DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_by_id` int DEFAULT NULL,
  `create_by_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by_id` int DEFAULT NULL,
  `update_by_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;



