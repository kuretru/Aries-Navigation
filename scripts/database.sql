/*
 Navicat Premium Data Transfer

 Source Server         : wsl
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : dev_aries

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 05/03/2021 10:32:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for web_category
-- ----------------------------
DROP TABLE IF EXISTS `web_category`;
CREATE TABLE `web_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务逻辑主键',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录上一次被动更新的时刻',
  `tag_id` bigint UNSIGNED NOT NULL COMMENT '所属标签ID',
  `name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `sequence` smallint UNSIGNED NOT NULL COMMENT '排序依据',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of web_category
-- ----------------------------

-- ----------------------------
-- Table structure for web_site
-- ----------------------------
DROP TABLE IF EXISTS `web_site`;
CREATE TABLE `web_site`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务逻辑主键',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录上一次被动更新的时刻',
  `category_id` bigint UNSIGNED NOT NULL COMMENT '所属分类ID',
  `name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点名称',
  `image_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点图标URL',
  `site_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点超链接URL',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点描述',
  `sequence` smallint UNSIGNED NOT NULL COMMENT '排序依据',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of web_site
-- ----------------------------

-- ----------------------------
-- Table structure for web_tag
-- ----------------------------
DROP TABLE IF EXISTS `web_tag`;
CREATE TABLE `web_tag`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务逻辑主键',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录上一次被动更新的时刻',
  `name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
  `sequence` smallint UNSIGNED NOT NULL COMMENT '排序依据',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of web_tag
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
