/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : study_test

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-07-16 10:48:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_name`
-- ----------------------------
DROP TABLE IF EXISTS `book_name`;
CREATE TABLE `book_name` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `type_id` tinyint(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of book_name
-- ----------------------------
INSERT INTO `book_name` VALUES ('1', '召唤武将', '1');
INSERT INTO `book_name` VALUES ('2', '宠魅', '2');

-- ----------------------------
-- Table structure for `book_type`
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type` (
  `id` tinyint(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES ('1', '历史');
INSERT INTO `book_type` VALUES ('2', '玄幻');

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` char(10) NOT NULL,
  `age` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'one', '123');
INSERT INTO `person` VALUES ('2', 'b', '31');
