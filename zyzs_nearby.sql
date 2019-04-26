/*
Navicat MySQL Data Transfer

Source Server         : T01
Source Server Version : 50641
Source Host           : 39.107.110.108:3306
Source Database       : zyzs_nearby

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2019-04-26 08:52:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zyzs_offical_sellers
-- ----------------------------
DROP TABLE IF EXISTS `zyzs_offical_sellers`;
CREATE TABLE `zyzs_offical_sellers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `shop_name` varchar(20) NOT NULL,
  `shop_address` varchar(50) NOT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `zyzsId` varchar(12) DEFAULT NULL,
  `shop_imgs` varchar(255) DEFAULT NULL,
  `business_license_num` varchar(50) DEFAULT NULL,
  `business_license_img` varchar(50) DEFAULT NULL,
  `thumbs` int(11) DEFAULT NULL,
  `isDelete` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zyzs_offical_sellers
-- ----------------------------
INSERT INTO `zyzs_offical_sellers` VALUES ('3', '赵奎', '15210785738', 'zyzsbj', '昆明湖南路52', '39.1432800', '117.2556610', '1', '2019-04-24 11:22:02', '2019-04-24 11:39:50', '1000', '1.jpg', '1214245456', '1.jpg', '0', '0');

-- ----------------------------
-- Table structure for zyzs_sellers_relation_sku
-- ----------------------------
DROP TABLE IF EXISTS `zyzs_sellers_relation_sku`;
CREATE TABLE `zyzs_sellers_relation_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `zyzsId` varchar(12) DEFAULT NULL,
  `pdc_id` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `operate` varchar(50) DEFAULT NULL COMMENT '操作人',
  `is_delete` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zyzs_sellers_relation_sku
-- ----------------------------
INSERT INTO `zyzs_sellers_relation_sku` VALUES ('1', '1000', '1', '2019-04-24 13:28:34', '2019-04-24 14:47:44', '1000', '1');
INSERT INTO `zyzs_sellers_relation_sku` VALUES ('2', '1000', '2', '2019-04-24 13:28:48', '2019-04-24 14:47:44', '1000', '1');
INSERT INTO `zyzs_sellers_relation_sku` VALUES ('3', '1000', '1', '2019-04-24 14:47:33', '2019-04-24 14:47:44', '1000', '1');
INSERT INTO `zyzs_sellers_relation_sku` VALUES ('4', '1000', '2', '2019-04-24 14:47:33', '2019-04-24 14:47:44', '1000', '1');
INSERT INTO `zyzs_sellers_relation_sku` VALUES ('5', '1000', '1', '2019-04-24 14:47:44', null, '1000', '0');

-- ----------------------------
-- Table structure for zyzs_sellers_sku
-- ----------------------------
DROP TABLE IF EXISTS `zyzs_sellers_sku`;
CREATE TABLE `zyzs_sellers_sku` (
  `id` bigint(19) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `ico` varchar(100) DEFAULT NULL,
  `detail_imgs` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `isDelete` varchar(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zyzs_sellers_sku
-- ----------------------------
INSERT INTO `zyzs_sellers_sku` VALUES ('1', '五粮 45', '0', '1.jpg', '2.jpg', '459.02', '2019-04-24 13:20:14', '0');
INSERT INTO `zyzs_sellers_sku` VALUES ('2', '渝州大红毯', '0', '1.jpg', '1.jpg', '2000.00', '2019-04-24 13:20:53', '0');

-- ----------------------------
-- Table structure for zyzs_sku_type
-- ----------------------------
DROP TABLE IF EXISTS `zyzs_sku_type`;
CREATE TABLE `zyzs_sku_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_delete` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zyzs_sku_type
-- ----------------------------
INSERT INTO `zyzs_sku_type` VALUES ('1', '酒', '0', '2019-04-24 13:27:44', null, '0');
INSERT INTO `zyzs_sku_type` VALUES ('2', '烟', '1', '2019-04-24 13:27:57', null, '0');
INSERT INTO `zyzs_sku_type` VALUES ('3', '茶', '2', '2019-04-24 13:28:13', null, '0');

-- ----------------------------
-- Table structure for zyzs_smoker_share_sellers
-- ----------------------------
DROP TABLE IF EXISTS `zyzs_smoker_share_sellers`;
CREATE TABLE `zyzs_smoker_share_sellers` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `shopName` varchar(20) DEFAULT NULL,
  `shopAddress` varchar(50) DEFAULT NULL,
  `latitude` decimal(10,7) DEFAULT NULL,
  `longitude` decimal(10,7) DEFAULT NULL,
  `shop_imgs` varchar(255) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `zyzsId` varchar(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  `isDelete` varchar(1) DEFAULT '0',
  `thumbs` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zyzs_smoker_share_sellers
-- ----------------------------
INSERT INTO `zyzs_smoker_share_sellers` VALUES ('1', '中国烟草', '河北', '1.0020000', '1.0020000', '1', '1', '1000000002', '2019-04-17 16:10:43', '2019-04-17 16:10:46', '0', '0');
