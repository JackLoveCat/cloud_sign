-- 代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table (
  table_id          bigint(20)      not null auto_increment    comment '编号',
  table_name        varchar(200)    default ''                 comment '表名称',
  table_comment     varchar(500)    default ''                 comment '表描述',
  class_name        varchar(100)    default ''                 comment '实体类名称',
  tpl_category      varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作）',
  package_name      varchar(100)                               comment '生成包路径',
  module_name       varchar(30)                                comment '生成模块名',
  business_name     varchar(30)                                comment '生成业务名',
  function_name     varchar(50)                                comment '生成功能名',
  function_author   varchar(50)                                comment '生成功能作者',
  options           varchar(1000)                              comment '其它生成选项',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (table_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表';


-- 代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment '编号',
  table_id          varchar(64)                                comment '归属表编号',
  column_name       varchar(200)                               comment '列名称',
  column_comment    varchar(500)                               comment '列描述',
  column_type       varchar(100)                               comment '列类型',
  java_type         varchar(500)                               comment 'JAVA类型',
  java_field        varchar(200)                               comment 'JAVA字段名',
  is_pk             char(1)                                    comment '是否主键（1是）',
  is_increment      char(1)                                    comment '是否自增（1是）',
  is_required       char(1)                                    comment '是否必填（1是）',
  is_insert         char(1)                                    comment '是否为插入字段（1是）',
  is_edit           char(1)                                    comment '是否编辑字段（1是）',
  is_list           char(1)                                    comment '是否列表字段（1是）',
  is_query          char(1)                                    comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  sort              int                                        comment '排序',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (column_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表字段';


-- 学校院系表
-- ----------------------------
DROP TABLE
IF
	EXISTS sys_uniacada;
CREATE TABLE sys_uniacada (
uniacada_id BIGINT ( 20 ) NOT NULL auto_increment COMMENT '学校院系ID',
university_name VARCHAR ( 50 ) NOT NULL COMMENT '学校名称',
academy_name VARCHAR ( 50 ) NOT NULL COMMENT '院系名称',
order_num INT ( 4 ) DEFAULT 0 COMMENT '显示顺序',
create_by VARCHAR ( 64 ) DEFAULT '' COMMENT '创建者',
create_time datetime COMMENT '创建时间',
update_by VARCHAR ( 64 ) DEFAULT '' COMMENT '更新者',
update_time datetime COMMENT '更新时间',
remark VARCHAR ( 500 ) DEFAULT '' COMMENT '备注',
PRIMARY KEY ( uniacada_id ),
unique key (university_name, academy_name)
) ENGINE = INNODB auto_increment = 2000 COMMENT = '学校院系表';

-- 初始化学校院系表数据
-- ----------------------------
insert into sys_uniacada values('1','福州大学','数学与计算机科学学院','0','admin',sysdate(),'admin',sysdate(),null);
insert into sys_uniacada values('2','福州大学','电气工程与自动化学院','1','admin',sysdate(),'admin',sysdate(),null);
insert into sys_uniacada values('3','福州大学','机械工程及自动化学院','2','admin',sysdate(),'admin',sysdate(),null);
insert into sys_uniacada values('4','厦门大学','新闻传播学院','3','admin',sysdate(),'admin',sysdate(),null);
insert into sys_uniacada values('5','厦门大学','经济学院','4','admin',sysdate(),'admin',sysdate(),null);

-- 菜单管理表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  icon              varchar(100)    default '#'                comment '菜单图标',
  link              varchar(100)    default '#'                comment '菜单链接',
  order_num         int(4)          default 0                  comment '显示顺序',
  is_menu           char(1)         default 0                  comment '是否菜单（0否 1是）',
  is_page           char(1)         default 0                  comment '是否页面（0否 1是）',
  perms             varchar(100)    default null               comment '权限标识',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id),
  unique key (menu_name)
) engine=innodb auto_increment=2000 comment = '菜单管理表';

-- 初始化菜单管理表数据
-- ----------------------------
insert into sys_menu values('1','系统管理','0','#','#',1,1,0,null,'admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('2','班课列表','0','#','#',2,1,0,null,'admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('3','数据字典管理','0','#','#',3,1,0,null,'admin',sysdate(),'admin',sysdate(),null);

insert into sys_menu values('5','签到管理','0','#','#',5,1,0,null,'admin',sysdate(),'admin',sysdate(),null);

insert into sys_menu values('11','菜单管理','1','#','#',1,0,1,'system:menu','admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('12','角色管理','1','#','#',2,0,1,'system:role','admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('13','用户管理','1','#','#',3,0,1,'system:user','admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('14','学校院系管理','1','#','#',4,0,1,'system:uniacada','admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('15','类型管理','3','#','#',1,0,1,'sysdicttype:type:list','admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('16','信息管理','3','#','#',2,0,1,'sysdictdata:data:list','admin',sysdate(),'admin',sysdate(),null);

insert into sys_menu values('21','我创建的','2','#','#',1,0,1,'cla:course:create','admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('22','我加入的','2','#','#',2,0,1,'cla:course:join','admin',sysdate(),'admin',sysdate(),null);

insert into sys_menu values('23','教师签到','5','#','#',1,0,1,'signin:teacher:signin','admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('24','学生签到','5','#','#',2,0,1,'signin:student:signin','admin',sysdate(),'admin',sysdate(),null);

-- 用户信息表
-- ----------------------------
DROP TABLE
IF
	EXISTS sys_user;
CREATE TABLE sys_user (
user_id BIGINT ( 20 ) NOT NULL auto_increment COMMENT '用户ID',
uniacada_id BIGINT ( 20 ) DEFAULT NULL COMMENT '学校系ID',
user_name VARCHAR ( 30 ) NOT NULL COMMENT '用户账号',
nick_name VARCHAR ( 30 ) NOT NULL COMMENT '用户昵称',
student_num VARCHAR ( 20 ) DEFAULT NULL COMMENT '学号',
user_type VARCHAR ( 2 ) DEFAULT '00' COMMENT '用户类型（00系统用户）',
email VARCHAR ( 50 ) DEFAULT NULL COMMENT '用户邮箱',
phonenumber VARCHAR ( 11 ) DEFAULT NULL COMMENT '手机号码',
avatar VARCHAR ( 100 ) DEFAULT '' COMMENT '头像地址',
PASSWORD VARCHAR ( 100 ) DEFAULT '' COMMENT '密码',
STATUS CHAR ( 1 ) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
del_flag CHAR ( 1 ) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
login_ip VARCHAR ( 50 ) DEFAULT '' COMMENT '最后登陆IP',
login_date datetime COMMENT '最后登陆时间',
create_by VARCHAR ( 64 ) DEFAULT '' COMMENT '创建者',
create_time datetime COMMENT '创建时间',
update_by VARCHAR ( 64 ) DEFAULT '' COMMENT '更新者',
update_time datetime COMMENT '更新时间',
remark VARCHAR ( 500 ) DEFAULT NULL COMMENT '备注',
PRIMARY KEY ( user_id ),
UNIQUE KEY (email),
UNIQUE KEY (phonenumber),
UNIQUE KEY (student_num)
) ENGINE = INNODB auto_increment = 100 COMMENT = '用户信息表';

-- 初始化-用户信息表数据
-- ----------------------------
INSERT INTO sys_user VALUES (1,NULL,'admin','管理员',NULL,'00','cloudsign@163.com','15888888888','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1',SYSDATE(),'admin',SYSDATE(),'admin',SYSDATE(),NULL);
INSERT INTO sys_user VALUES (2,1,'teachertest','教师测试用户',null,'01','teachertest@qq.com','15999999999','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1',SYSDATE(),'admin',SYSDATE(),'admin',SYSDATE(),NULL);
INSERT INTO sys_user VALUES (3,1,'studenttest','学生测试用户','190325000','01','studenttest@qq.com','15666666666','','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1',SYSDATE(),'admin',SYSDATE(),'admin',SYSDATE(),NULL);


-- 角色信息表
-- ----------------------------
DROP TABLE
IF
	EXISTS sys_role;
CREATE TABLE sys_role (
role_id BIGINT ( 20 ) NOT NULL auto_increment COMMENT '角色ID',
role_name VARCHAR ( 30 ) NOT NULL COMMENT '角色名称',
editable CHAR ( 1 ) NOT NULL COMMENT '是否允许修改（1是 0否）',
role_sort INT ( 4 ) NOT NULL COMMENT '显示顺序',
STATUS CHAR ( 1 ) NOT NULL COMMENT '角色状态（0正常 1停用）',
del_flag CHAR ( 1 ) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
create_by VARCHAR ( 64 ) DEFAULT '' COMMENT '创建者',
create_time datetime COMMENT '创建时间',
update_by VARCHAR ( 64 ) DEFAULT '' COMMENT '更新者',
update_time datetime COMMENT '更新时间',
remark VARCHAR ( 500 ) DEFAULT NULL COMMENT '备注',
PRIMARY KEY ( role_id ) ,
unique key (role_name)
) ENGINE = INNODB auto_increment = 100 COMMENT = '角色信息表';

-- 初始化-角色信息表数据
-- ----------------------------
INSERT INTO sys_role VALUES ('1','管理员',0,1,'0','0','admin',SYSDATE(),'admin',SYSDATE(),NULL);
INSERT INTO sys_role VALUES ('2','教师',1,2,'0','0','admin',SYSDATE(),'admin',SYSDATE(),NULL);
INSERT INTO sys_role VALUES ('3','学生',1,3,'0','0','admin',SYSDATE(),'admin',SYSDATE(),NULL);


-- 用户和角色关联表
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';

-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');
insert into sys_user_role values ('3', '3');


-- 角色和菜单关联表
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('2', '2');
insert into sys_role_menu values ('2', '23');
insert into sys_role_menu values ('3', '2');
insert into sys_role_menu values ('2', '21');
insert into sys_role_menu values ('3', '22');
insert into sys_role_menu values ('3', '24');

-- 班课信息表
-- ----------------------------
drop table if exists cla_course;
CREATE TABLE cla_course (
    course_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '班课ID',
    course_name VARCHAR(50) NOT NULL COMMENT '课程名称',
	course_num VARCHAR(10) NOT NULL COMMENT '班课号',
    class_name VARCHAR(50) NOT NULL COMMENT '班级名称',
    course_page VARCHAR(100) COMMENT '班课封面',
    semester VARCHAR(50) COMMENT '学期',
    curriculum VARCHAR(50) COMMENT '学校课表班课',
    textbook VARCHAR(50) COMMENT '云教材',
    uniacada_id BIGINT(20) NOT NULL COMMENT '学校院系ID',
    study_requirement VARCHAR(100) COMMENT '学习要求',
    lecture_progress VARCHAR(100) COMMENT '教学进度',
    exam_arrangement VARCHAR(100) COMMENT '考试安排',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (course_id)
)  ENGINE=INNODB AUTO_INCREMENT=100 COMMENT='班课信息表';

-- 初始化班课信息表
-- ----------------------------
INSERT INTO cla_course VALUES ('1','工程实践','000000','2019级工程硕士',NULL,'2019-2020第2学期',NULL,NULL,1,NULL,NULL,NULL,'admin',sysdate(),'admin',sysdate(),NULL);

-- 班课和学生信息表
-- ----------------------------
drop table if exists cla_course_student;
CREATE TABLE cla_course_student (
    course_id BIGINT(20) NOT NULL COMMENT '班课ID',
	student_id BIGINT(20) NOT NULL COMMENT '学生ID（用户ID）',
	actual_sign INT DEFAULT 0 COMMENT '实际签到次数',
    actual_exp INT DEFAULT 0 COMMENT '实际经验值',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (course_id,student_id)
)  ENGINE=INNODB COMMENT='班课和学生信息表';

-- 初始化班课和学生信息表
-- ----------------------------
INSERT INTO cla_course_student VALUES (1,3,0,0,'admin',sysdate(),'admin',sysdate(),NULL);

-- 班课和教师信息表
-- ----------------------------
drop table if exists cla_course_teacher;
CREATE TABLE cla_course_teacher (
    course_id BIGINT(20) NOT NULL COMMENT '班课ID',
	teacher_id BIGINT(20) NOT NULL COMMENT '任课教师ID',
    sign INT DEFAULT 0 COMMENT '发起签到次数',
    full_exp INT DEFAULT 0 COMMENT '满经验值',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (course_id)
)  ENGINE=INNODB COMMENT='班课和教师信息表';

-- 初始化班课和教师信息表
-- ----------------------------
INSERT INTO cla_course_teacher VALUES (1,2,0,0,'admin',sysdate(),'admin',sysdate(),NULL);

-- 系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id        bigint(20)     not null auto_increment   comment '访问ID',
  user_name      varchar(50)    default ''                comment '用户账号',
  ipaddr         varchar(50)    default ''                comment '登录IP地址',
  login_location varchar(255)   default ''                comment '登录地点',
  browser        varchar(50)    default ''                comment '浏览器类型',
  os             varchar(50)    default ''                comment '操作系统',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  msg            varchar(255)   default ''                comment '提示消息',
  login_time     datetime                                 comment '访问时间',
  primary key (info_id)
) engine=innodb auto_increment=100 comment = '系统访问记录';



-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_type_id     bigint(20)      not null auto_increment    comment '字典类型主键',
  dict_type_name   varchar(100)    default ''                 comment '字典类型名称',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_type_id),
  unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';

insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '任务状态列表');
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '任务分组列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '登录状态列表');


-- ----------------------------
-- 12、字典信息表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_data_id     bigint(20)      not null auto_increment    comment '字典信息主键',
  dict_data_sort   int(4)          default 0                  comment '字典信息排序',
  dict_label       varchar(100)    default ''                 comment '字典信息标签',
  dict_value       varchar(100)    default ''                 comment '字典信息键值',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  list_class       varchar(100)    default null               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_data_id)
) engine=innodb auto_increment=100 comment = '字典信息表';

insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',         '',        'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',         '',        'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',         '',        'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',        'primary', 'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',        'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',   'primary', 'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',   'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '停用状态');
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',       'primary', 'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '正常状态');
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',       'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '停用状态');
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',        '',        'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '默认分组');
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',        '',        'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '系统分组');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',           'primary', 'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',           'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',      'warning', 'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',      'success', 'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',    'primary', 'Y', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',    'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '关闭状态');
insert into sys_dict_data values(18, 1,  '新增',     '1',       'sys_oper_type',        'info',    'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '新增操作');
insert into sys_dict_data values(19, 2,  '修改',     '2',       'sys_oper_type',        'info',    'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '修改操作');
insert into sys_dict_data values(20, 3,  '删除',     '3',       'sys_oper_type',        'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '删除操作');
insert into sys_dict_data values(21, 4,  '授权',     '4',       'sys_oper_type',        'primary', 'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '授权操作');
insert into sys_dict_data values(22, 5,  '导出',     '5',       'sys_oper_type',        'warning', 'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '导出操作');
insert into sys_dict_data values(23, 6,  '导入',     '6',       'sys_oper_type',        'warning', 'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '导入操作');
insert into sys_dict_data values(24, 7,  '强退',     '7',       'sys_oper_type',        'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '强退操作');
insert into sys_dict_data values(25, 8,  '生成代码', '8',       'sys_oper_type',        'warning', 'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '生成操作');
insert into sys_dict_data values(26, 9,  '清空数据', '9',       'sys_oper_type',        'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '清空操作');
insert into sys_dict_data values(27, 1,  '成功',     '0',       'sys_common_status',    'primary', 'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '正常状态');
insert into sys_dict_data values(28, 2,  '失败',     '1',       'sys_common_status',    'danger',  'N', '0', 'admin', '2020-03-18 00-00-00', 'admin', '2020-03-18 00-00-00', '停用状态');


-- ----------------------------
-- 11、教师发起签到表
-- ----------------------------
drop table if exists cla_teacher_sign;
create table cla_teacher_sign
(
  teacher_sign_id       bigint(20)       not null auto_increment    comment '教师发起签到ID',
  teacher_id            bigint(20)       not null                   comment '教师ID',
  course_id             bigint(20)       not null                   comment '班课ID',
  start_time            datetime                                     comment '开始时间',
  stop_time              datetime                                     comment '结束时间',
  ipaddr                varchar(50)     default ''                   comment '发起签到IP地址',
  remark                varchar(100)    default null                comment '备注',
  primary key (teacher_sign_id)
) engine=innodb auto_increment=100 comment = '教师发起签到表';


-- ----------------------------
-- 12、学生签到记录表
-- ----------------------------
drop table if exists cla_student_sign;
create table cla_student_sign
(
  student_sign_id       bigint(20)       not null auto_increment    comment '学生签到记录ID',
  student_id            bigint(20)       not null                   comment '学生ID',
  course_id             bigint(20)       not null                   comment '班课ID',
  sign_time             datetime                                     comment '签到时间',
  ipaddr                varchar(50)     default ''                   comment '签到IP地址',
  remark                varchar(100)    default null                comment '备注',
  primary key (student_sign_id)
) engine=innodb auto_increment=100 comment = '学生签到记录表';

-- ----------------------------
-- 系统参数表
-- ----------------------------
drop table if exists sys_settings;
CREATE TABLE sys_settings (
    sys_settings_id BIGINT(20) NOT NULL auto_increment COMMENT '系统设置ID',
    each_sign_exp INT DEFAULT 2 COMMENT '每次签到经验',
    each_sign_time INT DEFAULT 2 COMMENT '每次签到时间',
    status     char(1) default '0' comment '状态（0正常 1停用）',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (sys_settings_id)
)  ENGINE=INNODB COMMENT='系统参数表';

insert into sys_settings values(1, 2, 2, '0', 'admin', '2020-05-06 00-00-00', 'admin', '2020-05-06 00-00-00', 'lktest');
