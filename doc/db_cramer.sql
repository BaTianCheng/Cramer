/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_cramer

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-05-05 17:35:02
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
INSERT INTO `sys_authority` VALUES ('101001', '权限菜单', 'auth', '1', '1', '0', '/auth', null, null, '1', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101101', '用户管理', 'user', '1', '1', '101001', '/auth/users', null, null, '2', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101102', '添加用户', 'user:add', '2', '1', '101101', null, null, null, '3', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101103', '修改用户', 'user:eidt', '2', '1', '101101', null, null, null, '4', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101104', '删除用户', 'user:delete', '2', '1', '101101', null, null, null, '5', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101201', '角色管理', 'role', '1', '1', '101001', '/auth/roles', null, null, '6', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101202', '添加角色', 'role:add', '2', '1', '101201', null, null, null, '7', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101203', '修改角色', 'role:edit', '2', '1', '101201', null, null, null, '8', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101204', '删除角色', 'role:delete', '2', '1', '101201', null, null, null, '9', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101301', '部门管理', 'department', '1', '1', '101001', '/auth/departments', null, null, '10', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101302', '添加部门', 'department:add', '2', '1', '101301', null, null, null, '11', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101303', '修改部门', 'department:edit', '2', '1', '101301', null, null, null, '12', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('101304', '删除部门', 'department:delete', '2', '1', '101301', null, null, null, '13', null, null, null, null, null);

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
INSERT INTO `sys_department` VALUES ('1', '系统', 'SYS', '1', '1', '0', null, null, null, null, null, null, null);

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
-- Table structure for `sys_operatelog`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operatelog`;
CREATE TABLE `sys_operatelog` (
  `id` int(11) NOT NULL,
  `module_id` int(11) DEFAULT NULL,
  `operate_type` int(11) DEFAULT NULL,
  `operate_by` int(11) DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL,
  `operate_ip` varchar(32) DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `remarks` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operatelog
-- ----------------------------
INSERT INTO `sys_operatelog` VALUES ('2', '100001', '291001', '1', '2017-05-05 15:11:21', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('3', '100001', '291001', '1', '2017-05-05 15:16:16', '192.168.73.52', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('4', '100001', '291001', '1', '2017-05-05 15:19:49', '192.168.73.71', '/cramer/login', '登录成功', null);

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
INSERT INTO `sys_role` VALUES ('1', '管理员', 'administrator', '1', '1', '0', '1', null, null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('2', '用户', 'user', '2', '1', '0', '1', null, null, null, null, null, null);

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
INSERT INTO `sys_role_authority` VALUES ('1', '100001');
INSERT INTO `sys_role_authority` VALUES ('1', '2');
INSERT INTO `sys_role_authority` VALUES ('1', '3');
INSERT INTO `sys_role_authority` VALUES ('1', '4');
INSERT INTO `sys_role_authority` VALUES ('1', '5');
INSERT INTO `sys_role_authority` VALUES ('1', '6');
INSERT INTO `sys_role_authority` VALUES ('1', '7');
INSERT INTO `sys_role_authority` VALUES ('1', '8');
INSERT INTO `sys_role_authority` VALUES ('1', '9');
INSERT INTO `sys_role_authority` VALUES ('1', '10');
INSERT INTO `sys_role_authority` VALUES ('1', '11');
INSERT INTO `sys_role_authority` VALUES ('1', '12');
INSERT INTO `sys_role_authority` VALUES ('1', '13');

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
INSERT INTO `sys_sequence` VALUES ('seq_sys_operatelog_id', '4', '1');
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
  `code` varchar(32) DEFAULT NULL,
  `real_name` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
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
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, '1', null, '1', null, null, null, null, null, '1', null, '5656');
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, '1', null, '1', null, null, null, null, null, '1', '2017-05-04 17:25:51', 'dfsdfsghg');

-- ----------------------------
-- Table structure for `sys_user_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_department`;
CREATE TABLE `sys_user_department` (
  `user_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_department
-- ----------------------------
INSERT INTO `sys_user_department` VALUES ('1', '1');
INSERT INTO `sys_user_department` VALUES ('2', '1');

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
INSERT INTO `sys_user_role` VALUES ('2', '2');

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
