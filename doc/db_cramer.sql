/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : db_cramer

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-04-07 17:22:48
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `User_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_name` varchar(32) DEFAULT NULL,
  `Password` varchar(32) DEFAULT NULL,
  `User_type` int(11) DEFAULT NULL,
  `Login_ip` varchar(32) DEFAULT NULL,
  `Login_time` varchar(32) DEFAULT NULL,
  `User_status` int(11) DEFAULT NULL,
  `Create_by` varchar(32) DEFAULT NULL,
  `Create_time` varchar(32) DEFAULT NULL,
  `Remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', null, null, '0', null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', '1', null, null, '0', null, null, null);
INSERT INTO `sys_user` VALUES ('7', 't1', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, '0', '1', '20170379112218', null);
INSERT INTO `sys_user` VALUES ('8', 't1', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, '0', '1', '20170379112511', null);
INSERT INTO `sys_user` VALUES ('9', 't1', 'e10adc3949ba59abbe56e057f20f883e', '0', null, null, '0', '1', '20170379112630', null);
INSERT INTO `sys_user` VALUES ('10', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379113745', null);
INSERT INTO `sys_user` VALUES ('12', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379113954', null);
INSERT INTO `sys_user` VALUES ('13', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379114052', null);
INSERT INTO `sys_user` VALUES ('14', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379114314', null);
INSERT INTO `sys_user` VALUES ('15', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379120128', null);
INSERT INTO `sys_user` VALUES ('16', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379014330', null);
INSERT INTO `sys_user` VALUES ('17', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379014505', null);
INSERT INTO `sys_user` VALUES ('18', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379014527', null);
INSERT INTO `sys_user` VALUES ('19', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379015437', null);
INSERT INTO `sys_user` VALUES ('20', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379015558', null);
INSERT INTO `sys_user` VALUES ('21', 't2', '96e79218965eb72c92a549dd5a330112', null, null, null, '0', '1', '20170379015857', null);

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
