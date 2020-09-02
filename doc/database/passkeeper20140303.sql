/*
Navicat MySQL Data Transfer

Source Server         : passkeeper
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : passkeeper

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2014-03-03 15:03:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pk_account`
-- ----------------------------
DROP TABLE IF EXISTS `pk_account`;
CREATE TABLE `pk_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `caption` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `account` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property1` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property2` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property3` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property4` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property5` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property6` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property7` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property8` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property9` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `property10` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `codeversion` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `updatedate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_user` (`userid`),
  KEY `pk_account` (`id`),
  KEY `idx_account_flag` (`flag`),
  KEY `idx_account_type` (`type`),
  CONSTRAINT `fk_account_user` FOREIGN KEY (`userid`) REFERENCES `pk_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pk_account
-- ----------------------------
INSERT INTO `pk_account` VALUES ('8', '1', '新浪', '新浪', 'outlawy@sina.com', null, '30,4,1,23,6,1,30,92,95,83,67,', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `pk_log`
-- ----------------------------
DROP TABLE IF EXISTS `pk_log`;
CREATE TABLE `pk_log` (
  `id` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `txid` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `source` text COLLATE utf8_unicode_ci,
  `changed` text COLLATE utf8_unicode_ci,
  `domain` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tablename` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pk_log
-- ----------------------------

-- ----------------------------
-- Table structure for `pk_user`
-- ----------------------------
DROP TABLE IF EXISTS `pk_user`;
CREATE TABLE `pk_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `realname` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `registerdate` date DEFAULT NULL,
  `logindate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pk_user` (`id`),
  KEY `idx_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of pk_user
-- ----------------------------
INSERT INTO `pk_user` VALUES ('1', 'test', '098F6BCD4621D373CADE4E832627B4F6', '测试', 'test@test.com', '1', '2014-02-14', null);
