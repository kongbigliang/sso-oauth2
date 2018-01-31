/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1-3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : oauth2

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-01-31 23:04:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_access_token
-- ----------------------------
DROP TABLE IF EXISTS `t_access_token`;
CREATE TABLE `t_access_token` (
  `access_token` varchar(255) NOT NULL DEFAULT '' COMMENT '访问令牌',
  `create_time` datetime NOT NULL COMMENT 'token生成时间',
  `expires_in` varchar(255) NOT NULL COMMENT '过期时间：3600s',
  `refresh_token_fk` varchar(255) NOT NULL,
  `account_fk` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`access_token`),
  KEY `refresh_token_FK` (`refresh_token_fk`),
  KEY `account_FK` (`account_fk`),
  CONSTRAINT `account_FK` FOREIGN KEY (`account_fk`) REFERENCES `t_shop_user` (`account`),
  CONSTRAINT `refresh_token_FK` FOREIGN KEY (`refresh_token_fk`) REFERENCES `t_refresh_token` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_access_token
-- ----------------------------
INSERT INTO `t_access_token` VALUES ('12CC5956D74540FDA69CFC4DA14241B0', '2017-07-24 18:17:34', '1800', '239CEE47D0134CAB8DBCD01E869B51EF', '123');
INSERT INTO `t_access_token` VALUES ('151032A0747542A48254EB02D9945274', '2017-03-11 15:44:03', '1800', '64AA36BFD09640FC8008821774A2A201', '123');
INSERT INTO `t_access_token` VALUES ('6E58C141E1BD48329BE82A6730167417', '2017-03-11 00:20:51', '1800', '538FE1794E0740B5A8215155F5145DFC', '123');
INSERT INTO `t_access_token` VALUES ('8DA55F927CCA479ABC08A755DDEA71E6', '2018-01-31 22:43:07', '1800', '91C542C6D8FC480BAE7AC632732EE84A', '123');
INSERT INTO `t_access_token` VALUES ('8F3A3E89E0B64846A1B76D46B53BFB8C', '2017-03-11 15:46:51', '1800', '243795D82A944C6FA3020B095420D0E2', '123');
INSERT INTO `t_access_token` VALUES ('A5B0E86E1A9044F8AEE196E429F7747D', '2017-03-11 10:15:43', '1800', 'EF4016356EA54061B15F871BB019D386', '123');
INSERT INTO `t_access_token` VALUES ('A5D20350D56041B7B76EE98917354C94', '2017-07-22 12:06:59', '1800', '5C2885E1AAF3479A9D92C24A8A12F5C6', '123');
INSERT INTO `t_access_token` VALUES ('BFCC0F8C56AF4698AD37BE5B3CB24E8C', '2017-07-22 13:32:05', '1800', 'A8124D44C4034694807755184CACEDC7', '123');
INSERT INTO `t_access_token` VALUES ('D0471E11CFC043F4A5DF1D391AE46FB3', '2017-03-10 23:59:31', '1800', '03548D65804B4EF9A954B4FC261F398E', '123');
INSERT INTO `t_access_token` VALUES ('DC4F09720657408ABDF4C681727019C3', '2017-03-10 23:55:02', '1800', 'B7F97E86363545FFB6505E07FB08786C', '123');
INSERT INTO `t_access_token` VALUES ('E1CAF07B98064471B82B8FF6E2813102', '2018-01-31 22:45:45', '1800', '0376A9B4D19246E4B422E6E8A6F8D358', '123');
INSERT INTO `t_access_token` VALUES ('FE5B92F79F194B569930AF5E3263B605', '2017-03-10 23:55:12', '1800', 'B3885EDF93264237888583DCCC574B6D', '123');

-- ----------------------------
-- Table structure for t_client_info
-- ----------------------------
DROP TABLE IF EXISTS `t_client_info`;
CREATE TABLE `t_client_info` (
  `client_id` varchar(255) NOT NULL DEFAULT '' COMMENT '应用的id',
  `client_name` varchar(255) NOT NULL,
  `client_secret` varchar(255) NOT NULL COMMENT 'RS与AS共享的密钥',
  `ip` varchar(255) NOT NULL,
  `domain_name` varchar(255) NOT NULL COMMENT '域名',
  `status` varchar(5) DEFAULT NULL COMMENT '状态：001-启用；000-停用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update` varchar(255) DEFAULT NULL COMMENT '最后修改人',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_client_info
-- ----------------------------
INSERT INTO `t_client_info` VALUES ('111', '电子商城', '111', '127.0.0.1', 'www.shop.taobao.com', '001', '2017-02-28 23:45:52', '11', '2017-02-28 23:45:55');
INSERT INTO `t_client_info` VALUES ('3015A630', 'CRM', '123456', '127.0.0.1', 'www.baidu.com', '001', '2017-07-24 18:08:26', null, '2017-07-24 18:08:26');
INSERT INTO `t_client_info` VALUES ('3016DE67', '交易系统', '111111', '127.0.0.1', 'www.trade.taobao.com', '001', '2017-03-01 11:34:17', null, '2017-03-01 11:34:17');
INSERT INTO `t_client_info` VALUES ('301CC814', '123', '111111', '127.0.0.1', '123', '000', '2017-03-11 15:36:06', null, '2017-03-11 15:36:06');

-- ----------------------------
-- Table structure for t_code
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code` (
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '加密后的授权码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `expires_in` varchar(255) NOT NULL COMMENT '存活时间',
  `account_fk` varchar(255) NOT NULL,
  PRIMARY KEY (`code`),
  KEY `account_FK2` (`account_fk`),
  CONSTRAINT `account_FK2` FOREIGN KEY (`account_fk`) REFERENCES `t_shop_user` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_code
-- ----------------------------
INSERT INTO `t_code` VALUES ('073DCB9BF9024889A0FE5E970273551A', '2017-07-22 13:34:38', '180', '123');
INSERT INTO `t_code` VALUES ('2EAA68E5DFE8440CB5465F62D63B45C2', '2018-01-31 22:43:07', '180', '123');
INSERT INTO `t_code` VALUES ('38FE4AC5A8174CC5A4ED49C8D97D51DE', '2018-01-31 22:45:45', '180', '123');
INSERT INTO `t_code` VALUES ('4E678A7FF0D04979BC24A30CDD22AEBD', '2017-03-11 00:20:51', '180', '123');
INSERT INTO `t_code` VALUES ('77E225DA2C1F4D4A82A177307BF7884F', '2017-07-22 13:29:31', '180', '123');
INSERT INTO `t_code` VALUES ('8B27DFB4DC0048BEB53B01A43812E341', '2017-07-24 18:17:33', '180', '123');
INSERT INTO `t_code` VALUES ('8EE9BF73E6B2421185652E3B95A34B90', '2017-03-11 10:14:52', '180', '123');
INSERT INTO `t_code` VALUES ('9BDB40865B0D412BBDF89A77C5FCA400', '2017-03-11 15:44:02', '180', '123');
INSERT INTO `t_code` VALUES ('B1F991CF4F534AD98891913D36924510', '2017-03-11 00:15:15', '180', '123');
INSERT INTO `t_code` VALUES ('C4A30E6F090045ABB394DA506E0013D7', '2017-07-22 12:06:59', '180', '123');
INSERT INTO `t_code` VALUES ('D5635EA7B9E2402E94A7AB332B2AEBED', '2017-03-11 00:12:41', '180', '123');
INSERT INTO `t_code` VALUES ('DB82AEE6954E4E62BDC6E8382255103A', '2017-03-11 15:46:51', '180', '123');

-- ----------------------------
-- Table structure for t_oauth2_user
-- ----------------------------
DROP TABLE IF EXISTS `t_oauth2_user`;
CREATE TABLE `t_oauth2_user` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `last_update` varchar(255) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_oauth2_user
-- ----------------------------
INSERT INTO `t_oauth2_user` VALUES ('1', 'admin', 'ISMvKXpXpadDiUoOSoAfww==', '001', '15521097962', '001', 'SSO服务器管理员-kongbig', '1', '2017-02-26 00:00:00', '1', '2017-07-22 12:02:05');
INSERT INTO `t_oauth2_user` VALUES ('67B1489CB5184863AB6103A85B63F75A', 'oauth02', 'pih6OJ0jH9ODNBRJ5pAJhw==', '002', '15522222222', '001', 'SSO服务器管理员', '1', '2017-03-01 09:46:14', '1', '2017-03-01 09:51:37');
INSERT INTO `t_oauth2_user` VALUES ('A19FA5E1CD8F4915990829A407CDEDD3', 'oauth01', '9qGgx60W4u38yCS/Y1jWvQ==', '001', '15522222222', '001', 'SSO服务器管理员', '1', '2017-02-27 00:00:00', '1', '2017-03-01 10:28:40');

-- ----------------------------
-- Table structure for t_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `t_refresh_token`;
CREATE TABLE `t_refresh_token` (
  `refresh_token` varchar(255) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL,
  `expires_in` varchar(50) NOT NULL,
  PRIMARY KEY (`refresh_token`),
  KEY `refresh_token` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_refresh_token
-- ----------------------------
INSERT INTO `t_refresh_token` VALUES ('03548D65804B4EF9A954B4FC261F398E', '2017-03-10 23:59:31', '86400');
INSERT INTO `t_refresh_token` VALUES ('0376A9B4D19246E4B422E6E8A6F8D358', '2018-01-31 22:45:45', '86400');
INSERT INTO `t_refresh_token` VALUES ('239CEE47D0134CAB8DBCD01E869B51EF', '2017-07-24 18:17:34', '86400');
INSERT INTO `t_refresh_token` VALUES ('243795D82A944C6FA3020B095420D0E2', '2017-03-11 15:46:51', '86400');
INSERT INTO `t_refresh_token` VALUES ('538FE1794E0740B5A8215155F5145DFC', '2017-03-11 00:20:51', '86400');
INSERT INTO `t_refresh_token` VALUES ('5C2885E1AAF3479A9D92C24A8A12F5C6', '2017-07-22 12:06:59', '86400');
INSERT INTO `t_refresh_token` VALUES ('64AA36BFD09640FC8008821774A2A201', '2017-03-11 15:44:03', '86400');
INSERT INTO `t_refresh_token` VALUES ('91C542C6D8FC480BAE7AC632732EE84A', '2018-01-31 22:43:07', '86400');
INSERT INTO `t_refresh_token` VALUES ('A8124D44C4034694807755184CACEDC7', '2017-07-22 13:32:05', '86400');
INSERT INTO `t_refresh_token` VALUES ('B3885EDF93264237888583DCCC574B6D', '2017-03-10 23:55:12', '86400');
INSERT INTO `t_refresh_token` VALUES ('B7F97E86363545FFB6505E07FB08786C', '2017-03-10 23:55:02', '86400');
INSERT INTO `t_refresh_token` VALUES ('EF4016356EA54061B15F871BB019D386', '2017-03-11 10:15:43', '86400');

-- ----------------------------
-- Table structure for t_shop_user
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_user`;
CREATE TABLE `t_shop_user` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `encode_string` varchar(255) DEFAULT NULL COMMENT '与accessToken一起解码',
  PRIMARY KEY (`id`),
  KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shop_user
-- ----------------------------
INSERT INTO `t_shop_user` VALUES ('1', '123', '123', '123');
INSERT INTO `t_shop_user` VALUES ('2', '222', '222', '222');

-- ----------------------------
-- Table structure for t_temp_token
-- ----------------------------
DROP TABLE IF EXISTS `t_temp_token`;
CREATE TABLE `t_temp_token` (
  `temp_token` varchar(255) NOT NULL DEFAULT '' COMMENT '临时令牌',
  `client_id` varchar(255) NOT NULL COMMENT '客户端的id',
  `re_uri_ip` varchar(255) NOT NULL COMMENT 'redirectURI的ip',
  `return_uri` varchar(255) NOT NULL COMMENT '目标地址',
  PRIMARY KEY (`temp_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_temp_token
-- ----------------------------
INSERT INTO `t_temp_token` VALUES ('10A22F2FAD394035A8C95B1EF7651D60', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('1426578A4C4D46F9A853A1B4C2A4233B', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('1CC0DFEA118342B2A7932DCBDE324EE0', '111', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('2FFB82499D524851AD8F02830BE73D82', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('3235CC724C2748649348C2178E09F9A0', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('4AF78F5291FF4E8985D3F3A87FBA6CF8', '111', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('501B0C2C2F2C41EAA33597109EDF2E0B', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('6330DFAF403C4928856EBDD369A9A77A', '111', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('9F835810D83244E4877B8850C463FAEB', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('A2D8BE0A3E1D4FCDAEED4B43316BDFDC', '111', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('A737BF3CF93B4C09AAB7F3F4C87E9E21', '111', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('B64E646CC1E94B59A64902D42878A6F4', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('BA8A64BD12174A3FA9B390566B082315', '111', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('BCA9709FEDFF4FE795026926973FFCFA', '111', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('C23F333DEBD841D0A3F28FC45558ACCF', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('DD7BD0D29913463A96EBD9C579143790', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('F8D9DEC7FC4041FB975E51164C98D6DC', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('FAB787ABFF9647169C5CB45D4471FF60', '3016DE67', '127.0.0.1', '/index.jsp');
INSERT INTO `t_temp_token` VALUES ('FC1DFD71EF6D447CACE473F527CD3C9B', '3016DE67', '127.0.0.1', '/index.jsp');
