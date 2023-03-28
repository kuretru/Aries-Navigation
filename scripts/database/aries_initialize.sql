/*
 Navicat Premium Data Transfer

 Source Server         : wsl
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32-0ubuntu0.22.04.2)
 Source Host           : localhost:3306
 Source Schema         : dev_aries

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32-0ubuntu0.22.04.2)
 File Encoding         : 65001

 Date: 28/03/2023 16:02:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务逻辑主键',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录上一次被动更新的时刻',
  `nickname` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像URL',
  `gemini_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '绑定的双子身份验证中心用户ID',
  `last_login` datetime NOT NULL COMMENT '上一次登录的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid` ASC) USING BTREE,
  INDEX `idx_gemini_id`(`gemini_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for web_category
-- ----------------------------
DROP TABLE IF EXISTS `web_category`;
CREATE TABLE `web_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务逻辑主键',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录上一次被动更新的时刻',
  `tag_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '所属标签ID',
  `name` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名称',
  `sequence` int UNSIGNED NOT NULL COMMENT '排序依据',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid` ASC) USING BTREE,
  INDEX `idx_tag_id`(`tag_id` ASC) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for web_site
-- ----------------------------
DROP TABLE IF EXISTS `web_site`;
CREATE TABLE `web_site`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务逻辑主键',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录上一次被动更新的时刻',
  `category_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '所属分类ID',
  `name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点名称',
  `image_url` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点图标URL',
  `site_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点超链接URL',
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '站点描述',
  `sequence` int UNSIGNED NOT NULL COMMENT '排序依据',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid` ASC) USING BTREE,
  INDEX `idx_name`(`name` ASC) USING BTREE,
  INDEX `idx_category_id`(`category_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for web_site_click_history
-- ----------------------------
DROP TABLE IF EXISTS `web_site_click_history`;
CREATE TABLE `web_site_click_history`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `site_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '站点ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_site_id`(`site_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点点击历史数据对象\r\n站点访问历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for web_tag
-- ----------------------------
DROP TABLE IF EXISTS `web_tag`;
CREATE TABLE `web_tag`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物理主键，自增',
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '业务逻辑主键',
  `create_time` datetime NOT NULL COMMENT '记录主动创建的时刻',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录上一次被动更新的时刻',
  `name` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
  `is_hidden` bit(1) NOT NULL COMMENT '是否为隐藏标签',
  `sequence` int UNSIGNED NOT NULL COMMENT '排序依据',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uuid`(`uuid` ASC) USING BTREE,
  UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站点标签表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
