/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : wh_dormitory

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 19/12/2019 16:24:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for school_class
-- ----------------------------
DROP TABLE IF EXISTS `school_class`;
CREATE TABLE `school_class`  (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级表的自增id',
  `class_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级名称',
  `class_grade` int(255) DEFAULT NULL COMMENT '班级几年制',
  `class_year_system` int(255) DEFAULT NULL COMMENT '毕业年份',
  `class_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `class_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除(0表示未删除,1表示删除)',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_class
-- ----------------------------
INSERT INTO `school_class` VALUES (1, '软件171', 3, 2020, NULL, NULL, 0);

-- ----------------------------
-- Table structure for school_depart
-- ----------------------------
DROP TABLE IF EXISTS `school_depart`;
CREATE TABLE `school_depart`  (
  `depart_id` int(11) NOT NULL COMMENT '部门id',
  `depart_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名字',
  `depart_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `depart_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除(0表示未删除,1表示删除)',
  PRIMARY KEY (`depart_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_dorm
-- ----------------------------
DROP TABLE IF EXISTS `school_dorm`;
CREATE TABLE `school_dorm`  (
  `dorm__id` int(11) NOT NULL COMMENT '寝室表自增id',
  `dorm_number` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '寝室门牌号',
  `dorm_floor` int(255) DEFAULT NULL COMMENT '楼层',
  `pk_dormitory_id` int(11) DEFAULT NULL COMMENT '关联宿舍id',
  PRIMARY KEY (`dorm__id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_dorm
-- ----------------------------
INSERT INTO `school_dorm` VALUES (1, '4-517', 5, 4);

-- ----------------------------
-- Table structure for school_dormitory
-- ----------------------------
DROP TABLE IF EXISTS `school_dormitory`;
CREATE TABLE `school_dormitory`  (
  `dormitory_id` int(11) NOT NULL COMMENT '宿舍编号',
  `dormitory_longitude` double(255, 0) DEFAULT NULL COMMENT '宿舍经度',
  `dormitory_latitude` double(255, 0) DEFAULT NULL COMMENT '宿舍纬度',
  `type` int(255) DEFAULT NULL COMMENT '宿舍类型,0表示女寝,1表示男寝,2表示混寝',
  PRIMARY KEY (`dormitory_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_dormitory
-- ----------------------------
INSERT INTO `school_dormitory` VALUES (4, NULL, NULL, 1);

-- ----------------------------
-- Table structure for school_leave_history
-- ----------------------------
DROP TABLE IF EXISTS `school_leave_history`;
CREATE TABLE `school_leave_history`  (
  `leave_history_id` int(11) NOT NULL COMMENT '学生请假流水表自增id',
  `pk_teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联教师id',
  `pk_stu_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联学生id',
  `is_success` int(11) DEFAULT NULL COMMENT '0表未审批,1表成功,2表失败',
  `leave_history_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `leave_history_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`leave_history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_sign_history
-- ----------------------------
DROP TABLE IF EXISTS `school_sign_history`;
CREATE TABLE `school_sign_history`  (
  `sign_history_id` int(11) NOT NULL COMMENT '学生签到表自增ID',
  `pk_stu_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联学生表学号',
  `pk_teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联教师表工号',
  `stu_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `continue_sign_month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '连续签到天数',
  `sign_history_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `sign_history_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除(0表示未删除,1表示删除)',
  PRIMARY KEY (`sign_history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_student
-- ----------------------------
DROP TABLE IF EXISTS `school_student`;
CREATE TABLE `school_student`  (
  `stu_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `pk_class_id` int(11) DEFAULT NULL COMMENT '关联班级表',
  `pk_role_id` int(11) DEFAULT NULL COMMENT '关联角色表',
  `stu_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `stu_sex` tinyint(255) DEFAULT NULL COMMENT '学生性别',
  `stu_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生账号',
  `stu_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生密码',
  `stu_birthday` datetime(0) DEFAULT NULL COMMENT '学生出生年月',
  `pk_teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联教师表',
  `pk_dorm_id` int(11) DEFAULT NULL COMMENT '关联寝室表',
  `stu_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `stu_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '1表删除,0表位删除',
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_student
-- ----------------------------
INSERT INTO `school_student` VALUES ('173067113', 1, 4, '余文豪', 1, 'admin', '123456', '1999-02-22 00:00:00', '2', NULL, '2019-12-18 10:27:39', NULL, 0);
INSERT INTO `school_student` VALUES ('173067114', 1, 4, '余文涛', 1, 'qwert', '12345', '1998-10-22 16:00:00', '2', 1234, NULL, NULL, 0);
INSERT INTO `school_student` VALUES ('173067117', 1, 4, '赵汉明', 1, 'aaaaa', '12345', NULL, '2', 1234, NULL, NULL, 0);

-- ----------------------------
-- Table structure for school_teacher
-- ----------------------------
DROP TABLE IF EXISTS `school_teacher`;
CREATE TABLE `school_teacher`  (
  `teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师编号',
  `pk_class_id` int(11) DEFAULT NULL COMMENT '关联班级表',
  `teacher_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师名字',
  `teacher_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `teacher_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `teacher_sex` tinyint(255) DEFAULT NULL COMMENT '教师性别(0-女,1-男)',
  `teacher_birthday` datetime(0) DEFAULT NULL COMMENT '出生日期',
  `teacher_prof` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教工职称',
  `teacher_depart_id` int(11) DEFAULT NULL COMMENT '关联部门表',
  `teacher_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `teacher_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除(1表示删除,0表示未删除)',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_teacher
-- ----------------------------
INSERT INTO `school_teacher` VALUES ('113067114', 1, '张一心', NULL, NULL, 1, '2019-07-04 09:31:15', '讲师', NULL, NULL, NULL, 0);
INSERT INTO `school_teacher` VALUES ('123456781', 2, '孟丽丽', NULL, NULL, 0, '1990-01-11 00:00:00', '讲师', NULL, NULL, NULL, 0);
INSERT INTO `school_teacher` VALUES ('173067113', 3, '余文豪', 'admin', '123456', 1, '2019-12-19 14:55:07', '讲师', NULL, '2019-12-19 14:55:18', NULL, 0);

-- ----------------------------
-- Table structure for school_unsign_history
-- ----------------------------
DROP TABLE IF EXISTS `school_unsign_history`;
CREATE TABLE `school_unsign_history`  (
  `unsign_history_id` int(11) NOT NULL COMMENT '学生未签到表自增id',
  `pk_stu_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联学生表学号',
  `pk_teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联教师表工号',
  `stu_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `is_checked` tinyint(255) DEFAULT NULL COMMENT '是否已经检查-0表未检查,1表检查',
  `unsign_history_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `unsign_history_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除(0表示未删除,1表示删除)',
  PRIMARY KEY (`unsign_history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单自增id',
  `menu_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `menu_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modifytime` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除0表未删,1表删',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '学生签到', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (2, '查询自己签到状态', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (3, '查询班级的签到状态', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (4, '学生请假', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (5, '教师审批请假', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_menu` VALUES (6, '新增人员', NULL, NULL, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '功能模块自增id',
  `operation_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operation_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ljurlqz` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '拦截url前缀url???',
  `parent_id` int(11) DEFAULT NULL COMMENT '操作父id???',
  `createtime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除-0表未删除,1表删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作入职自增id',
  `power_id` int(11) DEFAULT NULL COMMENT '关联权限id',
  `operation_content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作日志内容',
  `operation_user_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `operation_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除,0表未删1表删',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表自增id',
  `power_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限类型',
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除,0表未删,1表删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO `sys_power` VALUES (1, '查询所有班级的学生宿舍签到状态', '2019-12-19 15:38:52', NULL, 0);
INSERT INTO `sys_power` VALUES (2, '查看自己班级的学生签到状态', '2019-12-19 15:39:12', NULL, 0);
INSERT INTO `sys_power` VALUES (3, '查看自己的签到状态', NULL, NULL, 1);
INSERT INTO `sys_power` VALUES (4, '允许请假', NULL, NULL, 0);
INSERT INTO `sys_power` VALUES (5, '给予权限', NULL, NULL, 0);
INSERT INTO `sys_power` VALUES (6, '新增教师', NULL, NULL, 0);
INSERT INTO `sys_power` VALUES (7, '新增学生', NULL, NULL, 0);
INSERT INTO `sys_power` VALUES (8, '学生签到', NULL, NULL, 0);
INSERT INTO `sys_power` VALUES (9, '学生请假', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_power_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_power_menu`;
CREATE TABLE `sys_power_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限与菜单管理表自增id',
  `menu_id` int(11) DEFAULT NULL,
  `power_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除0表未删1表删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_power_menu
-- ----------------------------
INSERT INTO `sys_power_menu` VALUES (1, 1, 8, NULL, NULL, 0);
INSERT INTO `sys_power_menu` VALUES (2, 2, 3, NULL, NULL, 0);
INSERT INTO `sys_power_menu` VALUES (3, 3, 1, NULL, NULL, 0);
INSERT INTO `sys_power_menu` VALUES (4, 3, 2, NULL, NULL, 0);
INSERT INTO `sys_power_menu` VALUES (5, 4, 9, NULL, NULL, 0);
INSERT INTO `sys_power_menu` VALUES (6, 5, 4, NULL, NULL, 0);
INSERT INTO `sys_power_menu` VALUES (7, 6, 6, NULL, NULL, 0);
INSERT INTO `sys_power_menu` VALUES (8, 6, 7, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_power_operation
-- ----------------------------
DROP TABLE IF EXISTS `sys_power_operation`;
CREATE TABLE `sys_power_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限与功能操作表',
  `power_id` int(11) DEFAULT NULL,
  `operation_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除,0表示未删,1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表自增id',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除0表示未删,1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '班主任', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (2, '校长', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (3, '宿管大叔', NULL, NULL, 0);
INSERT INTO `sys_role` VALUES (4, '学生', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_role_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_power`;
CREATE TABLE `sys_role_power`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色与权限关联表',
  `role_id` int(11) DEFAULT NULL,
  `power_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除0表未删1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_power
-- ----------------------------
INSERT INTO `sys_role_power` VALUES (1, 2, 1, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (2, 1, 2, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (3, 4, 8, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (4, 2, 4, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (5, 2, 5, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (6, 2, 6, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (7, 2, 7, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (8, 1, 4, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (9, 1, 7, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (10, 4, 3, NULL, NULL, 0);
INSERT INTO `sys_role_power` VALUES (11, 4, 9, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_teacher_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_teacher_group`;
CREATE TABLE `sys_teacher_group`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师组表自增id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师组名',
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除0表未删,1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_teacher_group
-- ----------------------------
INSERT INTO `sys_teacher_group` VALUES (1, '教师一组', NULL, NULL, 0);
INSERT INTO `sys_teacher_group` VALUES (2, '教师二组', NULL, NULL, 0);
INSERT INTO `sys_teacher_group` VALUES (3, '教师三组', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_teacher_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_teacher_role`;
CREATE TABLE `sys_teacher_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师与角色关联表自增id',
  `teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除0表示未删1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_teacher_role
-- ----------------------------
INSERT INTO `sys_teacher_role` VALUES (1, '173067113', 2, NULL, NULL, 0);
INSERT INTO `sys_teacher_role` VALUES (2, '123456781', 1, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_teacher_teachergroup
-- ----------------------------
DROP TABLE IF EXISTS `sys_teacher_teachergroup`;
CREATE TABLE `sys_teacher_teachergroup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组与用户信息关联表自增id',
  `teacher_group_id` int(11) DEFAULT NULL,
  `teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除0表示未删,1表示删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_teacher_teachergroup
-- ----------------------------
INSERT INTO `sys_teacher_teachergroup` VALUES (1, 1, '123456781', NULL, NULL, 0);
INSERT INTO `sys_teacher_teachergroup` VALUES (2, 2, '173067113', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_teachergroup_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_teachergroup_role`;
CREATE TABLE `sys_teachergroup_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师组关联角色表自增id',
  `teacher_group_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `modify_time` datetime(0) DEFAULT NULL,
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除1表示删除,0表示未删',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_teachergroup_role
-- ----------------------------
INSERT INTO `sys_teachergroup_role` VALUES (1, 1, 1, NULL, NULL, 0);
INSERT INTO `sys_teachergroup_role` VALUES (2, 2, 2, NULL, NULL, 0);
INSERT INTO `sys_teachergroup_role` VALUES (3, 1, 3, NULL, NULL, 0);
INSERT INTO `sys_teachergroup_role` VALUES (4, NULL, NULL, NULL, NULL, 1);

SET FOREIGN_KEY_CHECKS = 1;
