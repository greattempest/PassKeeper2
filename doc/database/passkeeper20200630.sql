/*
Navicat MySQL Data Transfer

Source Server         : localmysql
Source Server Version : 80020
Source Host           : localhost:3306
Source Database       : passkeeper

Target Server Type    : MYSQL
Target Server Version : 80020
File Encoding         : 65001

Date: 2020-06-30 10:28:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pk_account`
-- ----------------------------
DROP TABLE IF EXISTS `pk_account`;
CREATE TABLE `pk_account` (
  `id` int NOT NULL,
  `userid` int DEFAULT NULL,
  `account` varchar(30) DEFAULT NULL,
  `type` varchar(4) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `property1` varchar(100) DEFAULT NULL,
  `property2` varchar(100) DEFAULT NULL,
  `property3` varchar(100) DEFAULT NULL,
  `property4` varchar(100) DEFAULT NULL,
  `property5` varchar(100) DEFAULT NULL,
  `property6` varchar(100) DEFAULT NULL,
  `property7` varchar(100) DEFAULT NULL,
  `property8` varchar(100) DEFAULT NULL,
  `property9` varchar(100) DEFAULT NULL,
  `property10` varchar(100) DEFAULT NULL,
  `flag` int DEFAULT NULL,
  `codeversion` int DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `updatedate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pk_account
-- ----------------------------
INSERT INTO `pk_account` VALUES ('0', '1', 'zhoujie', '1', '1', null, null, null, null, null, null, null, null, null, null, '0', null, null, null);

-- ----------------------------
-- Table structure for `pk_file`
-- ----------------------------
DROP TABLE IF EXISTS `pk_file`;
CREATE TABLE `pk_file` (
  `id` varchar(50) NOT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `filename` varchar(500) DEFAULT NULL,
  `filedesc` varchar(500) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `addtime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`userid`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`userid`) REFERENCES `pk_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pk_file
-- ----------------------------

-- ----------------------------
-- Table structure for `pk_log`
-- ----------------------------
DROP TABLE IF EXISTS `pk_log`;
CREATE TABLE `pk_log` (
  `id` varchar(50) DEFAULT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `txid` varchar(32) DEFAULT NULL,
  `source` text,
  `changed` text,
  `domain` varchar(50) DEFAULT NULL,
  `tablename` varchar(50) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  KEY `FK_Reference_2` (`userid`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`userid`) REFERENCES `pk_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pk_log
-- ----------------------------

-- ----------------------------
-- Table structure for `pk_subject`
-- ----------------------------
DROP TABLE IF EXISTS `pk_subject`;
CREATE TABLE `pk_subject` (
  `id` varchar(50) NOT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `objtype` varchar(20) DEFAULT NULL,
  `objid` varchar(128) DEFAULT NULL,
  `objpass` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `objquerypass` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `tipquestion` varchar(100) DEFAULT NULL,
  `tipanswer` varchar(128) DEFAULT NULL,
  `weburl` varchar(500) DEFAULT NULL,
  `businame` varchar(200) DEFAULT NULL,
  `limits` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `addtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`userid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`userid`) REFERENCES `pk_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pk_subject
-- ----------------------------
INSERT INTO `pk_subject` VALUES ('1', '0', null, '1383103123', '123456', '123456', null, null, null, null, null, '信用卡', null, null, '2020-06-19 14:27:01', null);
INSERT INTO `pk_subject` VALUES ('c133a696-b5ed-11ea-bdc5-00ffe6376a1b', '0', 'card', '12345678', '122,126,66,71,13,125,96,91,112,119,66,83,126,7,121,64,6,64,67,92,74,83,8,11,', '9,1,124,110,125,29,87,6,114,99,94,79,90,125,93,65,113,98,66,71,100,117,8,11,', '', '', '', '', '', 'ICBC', '', '', '2020-06-24 07:38:51', null);

-- ----------------------------
-- Table structure for `pk_user`
-- ----------------------------
DROP TABLE IF EXISTS `pk_user`;
CREATE TABLE `pk_user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `realname` varchar(32) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `flag` int DEFAULT NULL,
  `registerdate` date DEFAULT NULL,
  `logindate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pk_user
-- ----------------------------
INSERT INTO `pk_user` VALUES ('0', 'zhoujie', '1', null, null, null, null, null);
