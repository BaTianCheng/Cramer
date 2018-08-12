/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_cramer

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-06-06 09:48:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `msg_notice`
-- ----------------------------
DROP TABLE IF EXISTS `msg_notice`;
CREATE TABLE `msg_notice` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(2046) DEFAULT NULL,
  `content` text,
  `push_flag` int(11) DEFAULT NULL,
  `top_flag` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_notice
-- ----------------------------
INSERT INTO `msg_notice` VALUES ('1', '1', '1', 'Cramer系统推出1.0版本', 'Cramer经过一段时间的开发，目前推出了第一个版本，包含一些基本功能。', null, null, '1', '1', '2017-06-06 09:48:41', null, null, null);

-- ----------------------------
-- Table structure for `msg_web_mail`
-- ----------------------------
DROP TABLE IF EXISTS `msg_web_mail`;
CREATE TABLE `msg_web_mail` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `receiver_type` int(11) DEFAULT NULL,
  `receiver` varchar(2046) DEFAULT NULL,
  `method` int(11) DEFAULT NULL,
  `title` varchar(2046) DEFAULT NULL,
  `content` text,
  `icon` varchar(128) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `read_time` datetime DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_web_mail
-- ----------------------------

-- ----------------------------
-- Table structure for `msg_web_mail_storage`
-- ----------------------------
DROP TABLE IF EXISTS `msg_web_mail_storage`;
CREATE TABLE `msg_web_mail_storage` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `tag` int(11) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  `mail_id` int(11) DEFAULT NULL,
  `method` int(11) DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_web_mail_storage
-- ----------------------------

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
INSERT INTO `sys_authority` VALUES ('102001', '消息管理', 'msg', '1', '1', '0', '/msg', null, null, '14', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('102101', '通知公告', 'notify', '1', '1', '102001', '/msg/notify', null, null, '15', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('102102', '添加公告', 'notify:add', '2', '1', '102101', null, null, null, '16', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('102103', '修改公告', 'notify:edit', '2', '1', '102101', null, null, null, '17', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('102104', '删除公告', 'notify:delete', '2', '1', '102101', null, null, null, '18', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('102201', '站内信管理', 'webmail', '1', '1', '102001', '/msg/webmail', null, null, '19', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('102202', '发送站内信', 'webmail:add', '2', '1', '102201', null, null, null, '20', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('103001', '工作流管理', 'workflow', '1', '1', '0', '/workflow', null, null, '21', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('103101', '发起新流程', 'workflow-templates', '1', '1', '103001', 'workflow/templates', null, null, '22', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('103102', '待办列表', 'workflow-undos', '1', '1', '103001', 'workflow/dones', null, null, '23', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('103103', '已办列表', 'workflow-dones', '1', '1', '103001', 'workflow/dones', null, null, '24', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('103104', '我的流程', 'workflow-createds', '1', '1', '103001', 'workflow/createds', null, null, '25', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('104001', '服务总线', 'esb', '1', '1', '0', '/esb', null, null, '26', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('104101', '服务调用', 'esb-invoke', '1', '1', '104001', 'esb/invoke', null, null, '27', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('104102', '计划任务', 'esb-planningjob', '1', '1', '104001', 'esb/planningjob', null, null, '28', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('104103', '实时任务', 'esb-regularjob', '1', '1', '104001', 'esb/regularjob', null, null, '29', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('104104', '服务监控', 'esb-monitor', '1', '1', '104001', 'esb/monitor', null, null, '30', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('104105', '历史记录', 'esb-history', '1', '1', '104001', 'esb/history', null, null, '31', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('109001', '系统管理', 'sys', '1', '1', '0', '/sys', null, null, '90', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('109101', '操作日志', 'log', '1', '1', '109001', '/sys/log', null, null, '91', null, null, null, null, null);
INSERT INTO `sys_authority` VALUES ('109201', '使用统计', 'statistics', '1', '1', '109001', '/sys/statistics', null, null, '92', null, null, null, null, null);

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
INSERT INTO `sys_department_role` VALUES ('3', '2');

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
  `department_id` int(11) DEFAULT NULL,
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
INSERT INTO `sys_role_authority` VALUES ('1', '101001');
INSERT INTO `sys_role_authority` VALUES ('1', '101101');
INSERT INTO `sys_role_authority` VALUES ('1', '101102');
INSERT INTO `sys_role_authority` VALUES ('1', '101103');
INSERT INTO `sys_role_authority` VALUES ('1', '101104');
INSERT INTO `sys_role_authority` VALUES ('1', '101201');
INSERT INTO `sys_role_authority` VALUES ('1', '101202');
INSERT INTO `sys_role_authority` VALUES ('1', '101203');
INSERT INTO `sys_role_authority` VALUES ('1', '101204');
INSERT INTO `sys_role_authority` VALUES ('1', '101301');
INSERT INTO `sys_role_authority` VALUES ('1', '101302');
INSERT INTO `sys_role_authority` VALUES ('1', '101303');
INSERT INTO `sys_role_authority` VALUES ('1', '101304');
INSERT INTO `sys_role_authority` VALUES ('1', '102001');
INSERT INTO `sys_role_authority` VALUES ('1', '102101');
INSERT INTO `sys_role_authority` VALUES ('1', '102102');
INSERT INTO `sys_role_authority` VALUES ('1', '102103');
INSERT INTO `sys_role_authority` VALUES ('1', '102104');
INSERT INTO `sys_role_authority` VALUES ('1', '102201');
INSERT INTO `sys_role_authority` VALUES ('1', '102202');
INSERT INTO `sys_role_authority` VALUES ('1', '103001');
INSERT INTO `sys_role_authority` VALUES ('1', '103101');
INSERT INTO `sys_role_authority` VALUES ('1', '103102');
INSERT INTO `sys_role_authority` VALUES ('1', '103103');
INSERT INTO `sys_role_authority` VALUES ('1', '103104');
INSERT INTO `sys_role_authority` VALUES ('1', '104001');
INSERT INTO `sys_role_authority` VALUES ('1', '104101');
INSERT INTO `sys_role_authority` VALUES ('1', '104102');
INSERT INTO `sys_role_authority` VALUES ('1', '104103');
INSERT INTO `sys_role_authority` VALUES ('1', '104104');
INSERT INTO `sys_role_authority` VALUES ('1', '104105');
INSERT INTO `sys_role_authority` VALUES ('1', '109001');
INSERT INTO `sys_role_authority` VALUES ('1', '109101');
INSERT INTO `sys_role_authority` VALUES ('1', '109201');
INSERT INTO `sys_role_authority` VALUES ('2', '101201');
INSERT INTO `sys_role_authority` VALUES ('2', '101202');
INSERT INTO `sys_role_authority` VALUES ('2', '101203');
INSERT INTO `sys_role_authority` VALUES ('2', '101204');

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
INSERT INTO `sys_sequence` VALUES ('seq_msg_notice_id', '2', '1');
INSERT INTO `sys_sequence` VALUES ('seq_msg_web_mail_id', '1', '1');
INSERT INTO `sys_sequence` VALUES ('seq_msg_web_mail_storage_id', '1', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_authority_id', '30', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_department_id', '3', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_operatelog_id', '91', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_role_id', '4', '1');
INSERT INTO `sys_sequence` VALUES ('seq_sys_user_id', '34', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, '1', null, '1', '1', null, null, null, null, '1', '2017-05-22 09:24:00', '5656');

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
