/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_cramer

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-04-17 08:53:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dapartment`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dapartment`;
CREATE TABLE `sys_dapartment` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(128) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `area_id` varchar(16) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` int(11) DEFAULT NULL,
  `create_time` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `undate_time` int(11) DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dapartment
-- ----------------------------

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
  `create_time` int(11) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` int(11) DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
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
INSERT INTO `sys_sequence` VALUES ('seq_sys_user_id', '21', '1');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `login_ip` varchar(32) DEFAULT NULL,
  `login_time` varchar(32) DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `create_time` varchar(32) DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_time` int(11) DEFAULT NULL,
  `Remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('7', 't1', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', null, null, null, '1', '20170379112218', null, null, null);
INSERT INTO `sys_user` VALUES ('8', 't1', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', null, null, null, '1', '20170379112511', null, null, null);
INSERT INTO `sys_user` VALUES ('9', 't1', 'e10adc3949ba59abbe56e057f20f883e', '0', '0', null, null, null, '1', '20170379112630', null, null, null);
INSERT INTO `sys_user` VALUES ('10', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379113745', null, null, null);
INSERT INTO `sys_user` VALUES ('12', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379113954', null, null, null);
INSERT INTO `sys_user` VALUES ('13', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379114052', null, null, null);
INSERT INTO `sys_user` VALUES ('14', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379114314', null, null, null);
INSERT INTO `sys_user` VALUES ('15', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379120128', null, null, null);
INSERT INTO `sys_user` VALUES ('16', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379014330', null, null, null);
INSERT INTO `sys_user` VALUES ('17', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379014505', null, null, null);
INSERT INTO `sys_user` VALUES ('18', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379014527', null, null, null);
INSERT INTO `sys_user` VALUES ('19', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379015437', null, null, null);
INSERT INTO `sys_user` VALUES ('20', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379015558', null, null, null);
INSERT INTO `sys_user` VALUES ('21', 't2', '96e79218965eb72c92a549dd5a330112', null, '0', null, null, null, '1', '20170379015857', null, null, null);

-- ----------------------------
-- Table structure for `sys_user_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_department`;
CREATE TABLE `sys_user_department` (
  `user_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_department
-- ----------------------------

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

-- ----------------------------
-- Function structure for `currval`
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(v_seq_name VARCHAR(50)) RETURNS int(11)
begin        
    declare value integer;         
    set value = 0;         
    select current_val into value  from sequence where seq_name = v_seq_name;   
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
    update sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;  
    return currval(v_seq_name);  
end
;;
DELIMITER ;
