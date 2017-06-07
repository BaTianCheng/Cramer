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
INSERT INTO `sys_department` VALUES ('3', '测试部门', 'TEST', '1', '1', '1', null, null, null, null, null, null, '');

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
-- Records of sys_operatelog
-- ----------------------------
INSERT INTO `sys_operatelog` VALUES ('2', '100001', '291001', '1', '2017-05-05 15:11:21', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('3', '100001', '291001', '1', '2017-05-05 15:16:16', '192.168.73.52', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('4', '100001', '291001', '1', '2017-05-05 15:19:49', '192.168.73.71', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('5', '100001', '291001', '1', '2017-05-08 17:04:44', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('6', '100001', '291001', '1', '2017-05-08 17:07:49', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('7', '100001', '291001', '1', '2017-05-08 17:26:43', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('8', '100001', '230002', '1', '2017-05-08 17:26:57', '0:0:0:0:0:0:0:1', '/cramer/auth/roles/authorities/update', '更新角色权限', null);
INSERT INTO `sys_operatelog` VALUES ('9', '100001', '291001', '1', '2017-05-08 17:30:01', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('10', '100001', '230002', '1', '2017-05-08 17:30:16', '0:0:0:0:0:0:0:1', '/cramer/auth/roles/authorities/update', '更新角色权限', null);
INSERT INTO `sys_operatelog` VALUES ('11', '100001', '291001', '1', '2017-05-08 17:41:15', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('12', '100001', '291001', '1', '2017-05-08 17:43:23', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('13', '100001', '291001', '1', '2017-05-09 10:03:20', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('14', '100001', '291001', '1', '2017-05-09 10:25:26', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('15', '100001', '291001', '1', '2017-05-09 10:47:54', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('16', '100001', '230001', '1', '2017-05-09 10:48:05', '0:0:0:0:0:0:0:1', '/cramer/auth/departments/add', '新增部门', null);
INSERT INTO `sys_operatelog` VALUES ('17', '100001', '291001', '1', '2017-05-09 13:58:01', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('18', '100001', '291001', '1', '2017-05-09 14:11:08', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('19', '100001', '230002', '1', '2017-05-09 14:15:20', '0:0:0:0:0:0:0:1', '/cramer/auth/departments/roles/update', '更新部门角色', null);
INSERT INTO `sys_operatelog` VALUES ('20', '100001', '291001', '1', '2017-05-09 14:27:20', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('21', '100001', '291001', '1', '2017-05-10 10:03:29', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('22', '100001', '291001', '1', '2017-05-10 11:14:00', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('23', '100001', '291001', '1', '2017-05-10 11:14:13', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('24', '100001', '291001', '1', '2017-05-10 11:15:45', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('25', '100001', '291001', '1', '2017-05-10 11:17:55', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('26', '100001', '291001', '1', '2017-05-12 09:44:40', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('27', '100001', '291001', '1', '2017-05-12 10:15:47', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('28', '100001', '291001', '1', '2017-05-12 10:16:42', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('29', '100001', '291001', '1', '2017-05-12 10:46:22', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('30', '100001', '291001', '1', '2017-05-12 10:58:31', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('31', '100001', '230001', '1', '2017-05-12 10:59:33', '0:0:0:0:0:0:0:1', '/cramer/auth/users/add/info', '新增用户', null);
INSERT INTO `sys_operatelog` VALUES ('32', '100001', '230001', '1', '2017-05-12 11:02:10', '0:0:0:0:0:0:0:1', '/cramer/auth/users/add/info', '新增用户', null);
INSERT INTO `sys_operatelog` VALUES ('33', '100001', '230001', '1', '2017-05-12 11:07:16', '0:0:0:0:0:0:0:1', '/cramer/auth/users/add/info', '新增用户', null);
INSERT INTO `sys_operatelog` VALUES ('34', '100001', '291001', '1', '2017-05-12 11:11:54', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('35', '100001', '291001', '1', '2017-05-12 15:05:52', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('36', '100001', '291001', '1', '2017-05-12 15:10:43', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('37', '100001', '291001', '1', '2017-05-12 16:41:16', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('38', '100001', '291001', '1', '2017-05-12 16:46:48', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('39', '100001', '291001', '1', '2017-05-12 16:47:36', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('40', '100001', '291001', '1', '2017-05-12 16:49:06', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('41', '100001', '291001', '1', '2017-05-12 16:49:36', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('42', '100001', '291001', '1', '2017-05-12 16:51:31', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('43', '100001', '291001', '1', '2017-05-12 17:03:03', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('44', '100001', '291001', '1', '2017-05-12 17:08:24', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('45', '100001', '291001', '1', '2017-05-15 11:37:47', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('46', '100001', '291001', '1', '2017-05-15 11:43:12', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('47', '100001', '291001', '1', '2017-05-15 13:34:50', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('48', '100001', '291001', '1', '2017-05-15 16:29:04', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('49', '100001', '291001', '1', '2017-05-15 16:36:47', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('50', '100001', '291001', '1', '2017-05-15 17:53:27', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('51', '100001', '291001', '1', '2017-05-15 17:57:25', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('52', '100001', '291001', '1', '2017-05-15 17:58:55', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('53', '100001', '291001', '1', '2017-05-15 18:01:04', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('54', '100001', '291001', '1', '2017-05-15 18:07:12', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('55', '100001', '291001', '1', '2017-05-16 13:50:41', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('56', '100001', '291001', '1', '2017-05-16 13:57:38', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('57', '100001', '291001', '1', '2017-05-16 15:51:11', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('58', '100001', '230001', '1', '2017-05-16 15:52:49', '0:0:0:0:0:0:0:1', '/cramer/auth/roles/add', '增加角色', null);
INSERT INTO `sys_operatelog` VALUES ('59', '100001', '291001', '1', '2017-05-16 15:59:17', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('60', '100001', '291001', '1', '2017-05-16 16:41:41', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('61', '100001', '291001', '1', '2017-05-16 16:46:46', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('62', '100001', '291001', '1', '2017-05-16 16:49:24', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('63', '100001', '291001', '1', '2017-05-22 09:05:33', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('64', '100001', '230002', '1', '2017-05-22 09:06:04', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('65', '100001', '230002', '1', '2017-05-22 09:06:11', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('66', '100001', '230002', '1', '2017-05-22 09:10:21', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('67', '100001', '291001', '1', '2017-05-22 09:13:18', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('68', '100001', '230002', '1', '2017-05-22 09:15:43', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('69', '100001', '291001', '1', '2017-05-22 09:23:28', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('70', '100001', '230002', '1', '2017-05-22 09:24:00', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('71', '100001', '230002', '1', '2017-05-22 09:24:09', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('72', '100001', '230002', '1', '2017-05-22 09:24:13', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('73', '100001', '230002', '1', '2017-05-22 09:24:17', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('74', '100001', '230002', '1', '2017-05-22 09:24:22', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('75', '100001', '230001', '1', '2017-05-22 09:26:07', '0:0:0:0:0:0:0:1', '/cramer/auth/users/add/info', '新增用户', null);
INSERT INTO `sys_operatelog` VALUES ('76', '100001', '291001', '1', '2017-05-22 10:23:42', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('77', '100001', '230002', '1', '2017-05-22 10:23:52', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户', null);
INSERT INTO `sys_operatelog` VALUES ('78', '100001', '291001', '1', '2017-05-22 14:51:41', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('79', '100001', '291001', '1', '2017-05-22 15:06:07', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('80', '100001', '291001', '1', '2017-05-22 15:14:00', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('81', '100001', '291001', '1', '2017-05-22 18:14:35', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('82', '100001', '291001', '1', '2017-06-02 15:07:01', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('83', '100001', '291001', '1', '2017-06-02 16:37:30', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('84', '100001', '291001', '1', '2017-06-02 16:39:53', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('85', '100001', '291001', '1', '2017-06-02 16:46:38', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('86', '100001', '291001', '1', '2017-06-02 16:53:56', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', null);
INSERT INTO `sys_operatelog` VALUES ('87', '100001', '230002', '1', '2017-06-02 16:54:00', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户：admin2', null);
INSERT INTO `sys_operatelog` VALUES ('88', '100001', '291001', '1', '2017-06-02 17:10:10', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', '882d8c66-5aee-4326-8200-d3d8a12428ff');
INSERT INTO `sys_operatelog` VALUES ('89', '100001', '230002', '1', '2017-06-02 17:10:15', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户：admin2', 'bc6ef10b-49d4-4e6f-b575-0c1e29adf8fa');
INSERT INTO `sys_operatelog` VALUES ('90', '100001', '291001', '1', '2017-06-02 17:19:49', '0:0:0:0:0:0:0:1', '/cramer/login', '登录成功', '9b177e04-dd1a-486b-8714-cbf82324467f');
INSERT INTO `sys_operatelog` VALUES ('91', '100001', '230002', '1', '2017-06-02 17:19:53', '0:0:0:0:0:0:0:1', '/cramer/auth/users/update/info', '修改用户：admin2', 'ef517a06-a4b7-484e-9a10-5b9556c85456');

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
INSERT INTO `sys_role` VALUES ('4', '角色A', null, null, '1', '0', '3', null, null, null, null, null, '');

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
INSERT INTO `sys_role_authority` VALUES ('2', '101201');
INSERT INTO `sys_role_authority` VALUES ('2', '101202');
INSERT INTO `sys_role_authority` VALUES ('2', '101203');
INSERT INTO `sys_role_authority` VALUES ('2', '101204');
INSERT INTO `sys_role_authority` VALUES ('1', '101304');
INSERT INTO `sys_role_authority` VALUES ('1', '102001');
INSERT INTO `sys_role_authority` VALUES ('1', '102101');
INSERT INTO `sys_role_authority` VALUES ('1', '102102');
INSERT INTO `sys_role_authority` VALUES ('1', '102103');
INSERT INTO `sys_role_authority` VALUES ('1', '102104');
INSERT INTO `sys_role_authority` VALUES ('1', '102201');
INSERT INTO `sys_role_authority` VALUES ('1', '102202');
INSERT INTO `sys_role_authority` VALUES ('1', '109001');
INSERT INTO `sys_role_authority` VALUES ('1', '109101');
INSERT INTO `sys_role_authority` VALUES ('1', '109201');

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
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, '1', null, '1', '2', null, null, null, null, '1', '2017-06-02 17:19:53', 'dfsdfsghg');
INSERT INTO `sys_user` VALUES ('31', 'admin3', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, '1', '3', null, null, null, null, '1', '2017-05-22 09:24:13', '');
INSERT INTO `sys_user` VALUES ('32', 'admin4', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, '1', '4', null, null, null, null, '1', '2017-05-22 09:24:17', '');
INSERT INTO `sys_user` VALUES ('33', 'admin5', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, '1', '5', null, null, null, null, '1', '2017-05-22 09:24:22', '');
INSERT INTO `sys_user` VALUES ('34', 'admin6', 'e10adc3949ba59abbe56e057f20f883e', null, null, null, null, null, '1', '6', null, null, null, null, '1', '2017-05-22 10:23:51', '');

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
INSERT INTO `sys_user_department` VALUES ('31', '3');
INSERT INTO `sys_user_department` VALUES ('32', '3');
INSERT INTO `sys_user_department` VALUES ('33', '1');
INSERT INTO `sys_user_department` VALUES ('34', '1');
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
INSERT INTO `sys_user_role` VALUES ('31', '2');
INSERT INTO `sys_user_role` VALUES ('32', '2');
INSERT INTO `sys_user_role` VALUES ('33', '1');
INSERT INTO `sys_user_role` VALUES ('33', '2');
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
