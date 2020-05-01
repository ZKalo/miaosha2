/*
 Navicat Premium Data Transfer

 Source Server         : localhostMySQL
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 30/04/2020 10:46:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT 0,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (36, 'IphoneXs', 6999.00, '最好用iphone', 0, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554471598717&di=a491803743fdee79ea12b084a3087e8c&imgtype=0&src=http%3A%2F%2Fp0.ifengimg.com%2Fpmop%2F2018%2F1115%2F4A8B1763143DD802B10DBBD9D7D643EB0AD6D570_size71_w600_h400.jpeg');
INSERT INTO `item` VALUES (40, '小米水壶', 299.00, '小水杯', 4, 'https://i8.mifile.cn/a1/pms_1504498936.11861982.jpg');
INSERT INTO `item` VALUES (41, 'ROG枪神3', 19999.00, '英特尔酷睿i7，15.6英寸240hz便携轻薄手提吃鸡游戏笔记本', 0, 'https://img.alicdn.com/imgextra/i1/2745641605/O1CN01aHYtM01Nj8HTKg1nF_!!2745641605.jpg');
INSERT INTO `item` VALUES (42, 'ROG魔霸3', 16999.00, '九代英特尔酷睿i7 15.6英寸GTX1660ti/RTX2060学生便携游戏本手提笔记本', 5, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587478440621&di=bfef5a4384e8f971fe8fefc77ce63307&imgtype=0&src=http%3A%2F%2Fimage20.it168.com%2F201807_670x502%2F3227%2F1404929e68584b1b.jpg');
INSERT INTO `item` VALUES (43, '机械师M7鼠标', 286.00, '机械师M7无线可充电有线双模电竞游戏鼠标便携静音办公笔记本电脑吃鸡LOL专用非蓝牙吃鸡LOL专用非蓝牙', 4, 'https://img.alicdn.com/imgextra/i1/1730407557/O1CN01mg0aio25h9d0aWy6f_!!1730407557.jpg_430x430q90.jpg');
INSERT INTO `item` VALUES (44, '九阳榨汁机', 253.00, '家用水果小型全自动果蔬多功能炸果汁辅食料理机榨汁杯', 0, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587478049369&di=39ec57f78bd330f60e8e06d69fe8beb7&imgtype=0&src=http%3A%2F%2Fpic6.58cdn.com.cn%2Fzhuanzh%2Fn_v2604db4ba9cd041b9a63638605a368135.jpg%3Fw%3D750%26h%3D0');
INSERT INTO `item` VALUES (45, '野格', 148.00, '网红酒野格利口酒700ml力娇酒德国野格圣鹿头酒洋酒', 7, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587478195948&di=49c3963a6159dde4907bb5591db040ca&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fupload%2F20170518%2F4f77101cac2a42de8265bc4177ac8b84_th.png');
INSERT INTO `item` VALUES (46, 'chivas芝华士威士忌', 270.00, 'chivas芝华士威士忌12年500ml*2瓶装 鸡尾酒洋酒烈酒 口感浓郁醇和、丰润顺滑、细腻口感值得一尝', 0, 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2984337352,1641631954&fm=26&gp=0.jpg');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (20, 97, 36);
INSERT INTO `item_stock` VALUES (21, 100, 37);
INSERT INTO `item_stock` VALUES (22, 100, 38);
INSERT INTO `item_stock` VALUES (23, 100, 39);
INSERT INTO `item_stock` VALUES (24, 96, 40);
INSERT INTO `item_stock` VALUES (25, 25, 41);
INSERT INTO `item_stock` VALUES (26, 38, 42);
INSERT INTO `item_stock` VALUES (27, 107, 43);
INSERT INTO `item_stock` VALUES (28, 167, 44);
INSERT INTO `item_stock` VALUES (29, 344, 45);
INSERT INTO `item_stock` VALUES (30, 249, 46);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `amount` int(11) NOT NULL DEFAULT 0,
  `order_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `promo_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2019041000000000', 9, 36, 6999.00, 1, 6999.00, 0);
INSERT INTO `order_info` VALUES ('2019041000000100', 9, 36, 6999.00, 1, 6999.00, 0);
INSERT INTO `order_info` VALUES ('2019041000000200', 9, 36, 6999.00, 1, 6999.00, 0);
INSERT INTO `order_info` VALUES ('2019041200000300', 9, 40, 200.00, 1, 200.00, 2);
INSERT INTO `order_info` VALUES ('2020042200000566', 10, 43, 286.00, 1, 286.00, 1);
INSERT INTO `order_info` VALUES ('2020042200000666', 10, 43, 286.00, 1, 286.00, 1);
INSERT INTO `order_info` VALUES ('2020042200000766', 10, 43, 286.00, 1, 286.00, 1);
INSERT INTO `order_info` VALUES ('2020042200000866', 10, 43, 286.00, 1, 286.00, 1);
INSERT INTO `order_info` VALUES ('2020042200000966', 10, 45, 148.00, 1, 148.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001066', 10, 45, 148.00, 1, 148.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001166', 10, 45, 148.00, 1, 148.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001266', 10, 45, 148.00, 1, 148.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001366', 10, 42, 12888.00, 1, 12888.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001466', 10, 42, 12888.00, 1, 12888.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001566', 10, 42, 16999.00, 1, 16999.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001666', 10, 42, 16999.00, 1, 16999.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001766', 10, 42, 16999.00, 1, 16999.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001866', 10, 40, 200.00, 1, 200.00, 1);
INSERT INTO `order_info` VALUES ('2020042200001966', 10, 40, 200.00, 1, 200.00, 1);
INSERT INTO `order_info` VALUES ('2020042200002066', 10, 40, 299.00, 1, 299.00, 1);
INSERT INTO `order_info` VALUES ('2020042300002166', 10, 45, 148.00, 1, 148.00, 1);
INSERT INTO `order_info` VALUES ('2020042300002266', 10, 45, 148.00, 1, 148.00, 1);
INSERT INTO `order_info` VALUES ('2020042300002366', 10, 45, 148.00, 1, 148.00, 1);

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_date` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00',
  `end_date` datetime(0) NOT NULL DEFAULT '1000-01-01 00:00:00',
  `item_id` int(11) NOT NULL DEFAULT 0,
  `promo_item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES (2, '小米水壶限时抢购', '2020-04-22 00:00:00', '2020-04-22 00:03:00', 40, 200.00);
INSERT INTO `promo` VALUES (3, '小米水壶限时抢购', '2020-04-21 23:39:08', '2020-04-22 23:39:16', 40, 179.00);
INSERT INTO `promo` VALUES (4, '小米水壶限时抢购', '2020-04-23 00:06:00', '2020-04-23 00:06:20', 40, 88.00);

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `current_value` int(11) NOT NULL DEFAULT 0,
  `step` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 23, 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '\"\"',
  `gender` tinyint(4) NOT NULL DEFAULT -1 COMMENT '1为男性，2为女性',
  `age` int(11) NOT NULL DEFAULT 0,
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '“”',
  `register_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '“”' COMMENT '//byphone,bywechar,byalipay',
  `third_party_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '“”',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telphone_unique_index`(`telphone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '第一个用户', 1, 30, '15094944785', 'byphone', '123');
INSERT INTO `user_info` VALUES (6, 'test', 1, 22, '12312312311', 'byphone', '“”');
INSERT INTO `user_info` VALUES (9, 'root', 1, 22, '123456', 'byphone', '“”');
INSERT INTO `user_info` VALUES (10, 'kalo', 1, 20, '18344543391', 'byPhone', '11111111');
INSERT INTO `user_info` VALUES (11, 'kalo', 1, 22, '111111', 'byPhone', '11111111');
INSERT INTO `user_info` VALUES (12, '胖', 1, 10, '222222', 'byPhone', '11111111');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '“”',
  `user_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (1, 'asdfasdf', 1);
INSERT INTO `user_password` VALUES (3, 'ICy5YqxZB1uWSwcVLSNLcA==', 0);
INSERT INTO `user_password` VALUES (5, '4QrcOUm6Wau+VuBX8g+IPg==', 9);
INSERT INTO `user_password` VALUES (6, 'QJK+LoC9DG2WpHWFsnermg==', 10);
INSERT INTO `user_password` VALUES (7, 'QJK+LoC9DG2WpHWFsnermg==', 11);
INSERT INTO `user_password` VALUES (8, 'QJK+LoC9DG2WpHWFsnermg==', 12);

SET FOREIGN_KEY_CHECKS = 1;
