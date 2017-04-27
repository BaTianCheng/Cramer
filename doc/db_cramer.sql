/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_cramer

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-04-27 11:07:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_authority`
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(128) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `href` varchar(256) DEFAULT NULL,
  `target` varchar(128) DEFAULT NULL,
  `icon` varchar(128) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('1', '权限菜单', null, '1', '1', '0', '/auth', null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('2', '用户管理', null, '1', '1', '1', '/auth/users', null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('3', '添加用户', null, '2', '1', '2', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('4', '修改用户', null, '2', '1', '2', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('5', '删除用户', null, '2', '1', '2', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('6', '角色管理', null, '1', '1', '1', '/auth/roles', null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('7', '添加角色', null, '2', '1', '6', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('8', '修改角色', null, '2', '1', '6', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('9', '删除角色', null, '2', '1', '6', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('10', '部门管理', null, '1', '1', '1', '/auth/departments', null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('11', '添加部门', null, '2', '1', '10', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('12', '修改部门', null, '2', '1', '10', null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('13', '删除部门', null, '2', '1', '10', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(128) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `area_id` varchar(16) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '系统', 'SYS', null, '1', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_department_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department_role`;
CREATE TABLE `sys_department_role` (
  `department_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department_role
-- ----------------------------
INSERT INTO `sys_department_role` VALUES ('1', '1');
INSERT INTO `sys_department_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(128) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `dapartment_id` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', null, null, '1', null, null, null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('2', '用户', null, null, '1', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `sys_role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority` (
  `role_id` int(11) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence` (
  `seq_name` varchar(50) NOT NULL,
  `current_val` int(11) NOT NULL,
  `increment_val` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_sequence
-- ----------------------------
INSERT INTO `sys_sequence` VALUES ('seq_sys_authority_id', '30', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_department_id', '2', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_role_id', '3', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_user_id', '30', '1');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `login_ip` varchar(32) DEFAULT NULL,
  `login_time` varchar(32) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `Remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '1', null, null, null, null, null, '1', null, '5656');
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', '1', null, '1', null, null, null, null, null, '1', null, '4545');

-- ----------------------------
-- Table structure for `sys_user_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_department`;
CREATE TABLE `sys_user_department` (
  `user_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `sys_user_department_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_department
-- ----------------------------
INSERT INTO `sys_user_department` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');

-- ----------------------------
-- Function structure for `currval`
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(v_seq_name VARCHAR(50)) RETURNS int(11)
begin        
    declare value integer;         
    set value = 0;         
    select current_val into value  from sys_sequence where seq_name = v_seq_name;   
   return value;   
end
;;
DELIMITER ;

-- ----------------------------
-- Function structure for `nextval`
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(v_seq_name VARCHAR(50)) RETURNS int(11)
begin  
    update sys_sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;  
    return currval(v_seq_name);  
end
;;
DELIMITER ;
