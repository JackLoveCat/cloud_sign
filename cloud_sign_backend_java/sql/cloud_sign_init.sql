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
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单管理表';

-- 初始化菜单管理表数据
-- ----------------------------
insert into sys_menu values('1','系统管理','0','#','#',1,1,0,'admin',sysdate(),'admin',sysdate(),null);

insert into sys_menu values('11','菜单管理','1','#','#',1,0,1,'admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('12','角色管理','1','#','#',2,0,1,'admin',sysdate(),'admin',sysdate(),null);
insert into sys_menu values('13','用户管理','1','#','#',3,0,1,'admin',sysdate(),'admin',sysdate(),null);

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

-- 班课信息表
-- ----------------------------
drop table if exists cla_course;
CREATE TABLE cla_course (
    course_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '班课ID',
    course_name VARCHAR(50) NOT NULL COMMENT '课程名称',
    class_name VARCHAR(50) NOT NULL COMMENT '班级名称',
    course_page VARCHAR(100) COMMENT '班课封面',
    semester VARCHAR(50) COMMENT '学期',
    curriculum VARCHAR(50) COMMENT '学校课表班课',
    textbook VARCHAR(50) COMMENT '云教材',
    university VARCHAR(50) COMMENT '学校',
    academy VARCHAR(50) COMMENT '学院',
    study_requirement VARCHAR(100) COMMENT '学习要求',
    lecture_progress VARCHAR(100) COMMENT '教学进度',
    exam_arrangement VARCHAR(100) COMMENT '考试安排',
    create_user_id BIGINT(20) NOT NULL COMMENT '创建者ID',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    remark VARCHAR(500) DEFAULT '' COMMENT '备注',
    PRIMARY KEY (course_id)
)  ENGINE=INNODB AUTO_INCREMENT=2000 COMMENT='班课信息表';

-- 初始化班课信息表
-- ----------------------------
insert into cla_course values('1','工程实践','2019级工程硕士',null,'2019-2020第2学期',null,null,"福州大学","数学与计算机科学学院",null,null,null,1,'admin',sysdate(),'admin',sysdate(),null);