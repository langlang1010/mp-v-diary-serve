/*
Navicat MySQL Data Transfer

Source Server         : withyan.cn
Source Server Version : 50728
Source Host           : withyan.cn:3306
Source Database       : diary

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-02-22 22:38:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `diary`
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary` (
  `pk_diaryid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `weather` char(20) DEFAULT NULL,
  `mood` char(20) DEFAULT NULL,
  `state` char(10) DEFAULT NULL,
  PRIMARY KEY (`pk_diaryid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of diary
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `pk_userid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `password` char(50) DEFAULT NULL,
  `uk_phone` char(15) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `role` char(255) DEFAULT NULL,
  PRIMARY KEY (`pk_userid`),
  UNIQUE KEY `uk_phone` (`uk_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_diary`
-- ----------------------------
DROP TABLE IF EXISTS `user_diary`;
CREATE TABLE `user_diary` (
  `fk_userid` bigint(20) unsigned NOT NULL,
  `fk_diaryid` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`fk_userid`,`fk_diaryid`),
  KEY `fk_diaryid` (`fk_diaryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_diary
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `pk_userid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `openid` char(32) DEFAULT NULL,
  `nickname` char(60) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `city` char(40) DEFAULT NULL,
  `province` char(40) DEFAULT NULL,
  `country` char(40) DEFAULT NULL,
  `avatarUrl` char(150) DEFAULT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`pk_userid`),
  UNIQUE KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------

