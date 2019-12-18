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

 Date: 18/12/2019 15:30:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for school_class
-- ----------------------------
DROP TABLE IF EXISTS `school_class`;
CREATE TABLE `school_class`  (
  `class_id` int(11) NOT NULL COMMENT '班级表的自增id',
  `class_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级名称',
  `class_grade` int(255) DEFAULT NULL COMMENT '班级几年制',
  `class_year_system` int(255) DEFAULT NULL COMMENT '毕业年份',
  `class_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `class_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除(0表示未删除,1表示删除)',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for school_student
-- ----------------------------
DROP TABLE IF EXISTS `school_student`;
CREATE TABLE `school_student`  (
  `stu_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `pk_class_id` int(11) DEFAULT NULL COMMENT '关联班级表',
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
  `pk_power_id` int(11) DEFAULT NULL COMMENT '关联全力数据字典表',
  `is_deleted` tinyint(255) DEFAULT NULL COMMENT '是否删除(1表示删除,0表示未删除)',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_teacher_power
-- ----------------------------
DROP TABLE IF EXISTS `school_teacher_power`;
CREATE TABLE `school_teacher_power`  (
  `power_id` int(11) NOT NULL COMMENT '教师权限自增id',
  `power_value` int(255) DEFAULT NULL COMMENT '权限值',
  PRIMARY KEY (`power_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stu_leave_history
-- ----------------------------
DROP TABLE IF EXISTS `stu_leave_history`;
CREATE TABLE `stu_leave_history`  (
  `leave_history_id` int(11) NOT NULL COMMENT '学生请假流水表自增id',
  `pk_teacher_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联教师id',
  `pk_stu_id` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联学生id',
  `is_success` int(11) DEFAULT NULL COMMENT '0表未审批,1表成功,2表失败',
  `leave_history_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `leave_history_modify` datetime(0) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`leave_history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for stu_sign_history
-- ----------------------------
DROP TABLE IF EXISTS `stu_sign_history`;
CREATE TABLE `stu_sign_history`  (
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
-- Table structure for stu_unsign_history
-- ----------------------------
DROP TABLE IF EXISTS `stu_unsign_history`;
CREATE TABLE `stu_unsign_history`  (
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

SET FOREIGN_KEY_CHECKS = 1;
