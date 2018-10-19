/*
Navicat MySQL Data Transfer

Source Server         : mumuxiaoyang
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : huahua_saselomo

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-10-08 23:59:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `age` tinyint(3) DEFAULT NULL COMMENT '年龄',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `valid` tinyint(1) DEFAULT NULL COMMENT '客户状态',
  `skinCondition` varchar(100) DEFAULT NULL COMMENT '皮肤状态',
  `timetable` time DEFAULT NULL COMMENT '作息时间',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='客户';

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('15', '王媛媛', '女', '24', '河南省济源市', '1555555555', '1', 'I do not know', '23:00:00', '', '2018-07-28 12:55:07', null, '花花', null);
INSERT INTO `client` VALUES ('54', '迪丽热巴', '女', '23', '河南省济源市邵原镇', '15738847570', '1', 'I do not know', '23:00:00', '', '2018-09-10 11:12:43', null, '花花', null);
INSERT INTO `client` VALUES ('63', '杨木木', '男', '23', '河南省济源市邵原镇', '17600365220', null, '怎么办，我该怎么办', '23:00:00', '啊，你知道什么叫绝望么', '2018-09-15 14:50:05', null, null, null);
INSERT INTO `client` VALUES ('65', '慕容雪', '女', '21', '山东', '13838384388', null, '...', '22:00:00', '...', '2018-09-15 14:50:05', null, null, null);
INSERT INTO `client` VALUES ('66', '乔峰', '男', '47', '契丹', '13655332277', null, '武功盖世', '02:00:00', '降龙十八赞', '2018-09-15 14:50:05', null, null, null);
INSERT INTO `client` VALUES ('67', '段誉', '男', '35', '大理', '15688996655', null, '玉树临风', '01:00:00', '林波舞步', '2018-09-15 14:50:05', null, null, null);
INSERT INTO `client` VALUES ('68', '虚竹', '男', '38', '河南嵩山', '16266553342', null, '光头强', '00:00:00', '未知', '2018-09-15 14:50:05', null, null, null);
INSERT INTO `client` VALUES ('69', '王语嫣', '女', '22', '大燕', '13838384589', '1', '美若天仙', '22:00:00', '木头人', '2018-09-15 14:50:05', '2018-09-16 10:47:56', null, '花花');
INSERT INTO `client` VALUES ('70', '许嵩', '男', '30', '山东', '13965668988', null, '', null, '', '2018-09-15 14:50:05', null, null, null);
INSERT INTO `client` VALUES ('71', '杨啦啦', '女', '23', '河南省济源市邵原镇卫洼村', '13838384388', '1', '这个我还真不清楚怎么表达', '22:00:00', '', '2018-09-16 10:40:53', null, '花花', null);

-- ----------------------------
-- Table structure for `clientpurhistory`
-- ----------------------------
DROP TABLE IF EXISTS `clientpurhistory`;
CREATE TABLE `clientpurhistory` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `clientId` int(12) NOT NULL COMMENT '客户id',
  `purchaseDate` date DEFAULT NULL COMMENT '购买时间',
  `purProSimp` varchar(255) DEFAULT NULL COMMENT '购买产品简称',
  `purProFull` varchar(255) DEFAULT NULL COMMENT '购买产品全称',
  PRIMARY KEY (`id`,`clientId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clientpurhistory
-- ----------------------------
INSERT INTO `clientpurhistory` VALUES ('7', '36', '2018-08-25', '沐浴露*2,沐浴露*2', '自然肌悦滋养沐浴露*2,自然肌悦滋养沐浴露*2');

-- ----------------------------
-- Table structure for `client_sales`
-- ----------------------------
DROP TABLE IF EXISTS `client_sales`;
CREATE TABLE `client_sales` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `clientId` int(12) NOT NULL COMMENT '客户id',
  `salesId` int(12) NOT NULL COMMENT '售货单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='售货单与客户的对应关系';

-- ----------------------------
-- Records of client_sales
-- ----------------------------
INSERT INTO `client_sales` VALUES ('34', '54', '25');

-- ----------------------------
-- Table structure for `inventory`
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `productId` int(12) NOT NULL COMMENT '产品id',
  `inventoryCount` int(5) DEFAULT NULL COMMENT '库存总数',
  `inventoryAvailable` int(5) DEFAULT NULL COMMENT '库存可用',
  `inventoryFreeze` int(5) DEFAULT NULL COMMENT '库存冻结',
  `inventoryValid` int(1) DEFAULT NULL COMMENT '库存状态',
  `inventoryOrderForm` int(5) DEFAULT NULL COMMENT '订单数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='库存';

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES ('20', '28', '6', '4', '0', '3', '2');
INSERT INTO `inventory` VALUES ('21', '24', '5', '5', '0', '3', '0');
INSERT INTO `inventory` VALUES ('22', '27', '14', '14', '0', '3', '0');
INSERT INTO `inventory` VALUES ('23', '11', '3', '0', '0', '3', '3');
INSERT INTO `inventory` VALUES ('24', '5', '5', '2', '0', '3', '3');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `name` varchar(50) DEFAULT NULL COMMENT '产品名称',
  `abbreviation` varchar(50) DEFAULT NULL COMMENT '产品简称',
  `valid` tinyint(1) DEFAULT NULL COMMENT '是否有效',
  `firstStage` double(9,2) DEFAULT NULL COMMENT ' 一级(套/元)',
  `secondStage` double(9,2) DEFAULT NULL COMMENT ' 二级(套/元)',
  `supremacy` double(9,2) DEFAULT NULL COMMENT '至尊VIP(套/元)',
  `derivative` double(9,2) DEFAULT NULL COMMENT '微商(套/元)',
  `retail` double(9,2) DEFAULT NULL COMMENT '零售(套/元)',
  `cartonSize` int(5) DEFAULT NULL COMMENT '箱规(套/箱)',
  `effect` varchar(255) DEFAULT NULL COMMENT '功效',
  `sellingPoints` varchar(255) DEFAULT NULL COMMENT '卖点',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='产品';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '七天更新赋活精华面膜套', '七天', '1', '105.00', '135.00', '165.00', '145.00', '198.00', '20', '面膜啊，敷脸用', '厉害，牛逼，666', '此备注零', '2018-07-14 13:54:05', '2018-07-14 13:54:09', 'admin', 'admin');
INSERT INTO `product` VALUES ('2', '澳莱双重美白祛斑套装', '祛斑霜', '1', '135.00', '165.00', '195.00', '298.00', '398.00', '12', '祛斑', '功效强大', '柑橘有用', '2018-08-02 10:36:46', null, '花花', null);
INSERT INTO `product` VALUES ('5', '三草两木祛黄调理养肤BB霜', 'BB霜', '1', '75.00', '95.00', '115.00', '168.00', '198.00', '12', '调理养肤', '功效强大', '柑橘有用', '2018-08-02 10:43:22', null, '花花', null);
INSERT INTO `product` VALUES ('6', '珍萃多元恒美修护眼部礼盒', '眼霜', '1', '100.00', '125.00', '150.00', '238.00', '298.00', '12', '美修护眼', '功效强大', '柑橘有用', '2018-08-02 10:45:22', null, '花花', null);
INSERT INTO `product` VALUES ('7', '三草两木贝壳焕肤提亮魔法液', 'PS', '1', '65.00', '85.00', '105.00', '158.00', '178.00', '10', '焕肤提亮', '功效强大', '柑橘有用', '2018-08-02 10:47:41', null, '花花', null);
INSERT INTO `product` VALUES ('8', '自然之礼春生限量礼盒（水润）', '春', '1', '142.00', '172.00', '201.00', '299.00', '399.00', '10', '焕肤提亮', '功效强大', '柑橘有用', '2018-08-02 10:48:33', null, '花花', null);
INSERT INTO `product` VALUES ('9', '自然之礼四季限量礼盒（百合）', '美白', '1', '142.00', '172.00', '201.00', '299.00', '399.00', '10', '焕肤提亮', '功效强大', '柑橘有用', '2018-08-02 10:48:56', null, '花花', null);
INSERT INTO `product` VALUES ('10', '自然之礼秋实限量礼盒（极润）', '秋套', '1', '142.00', '172.00', '201.00', '299.00', '399.00', '10', '焕肤提亮', '功效强大', '柑橘有用', '2018-08-02 10:49:14', null, '花花', null);
INSERT INTO `product` VALUES ('11', '三草两木舒安修护霜', '舒安霜', '1', '85.00', '105.00', '125.00', '168.00', '208.00', '10', '舒安修护', '功效强大', '柑橘有用', '2018-08-02 10:50:26', null, '花花', null);
INSERT INTO `product` VALUES ('12', '三草两木珍萃多元修护活力眼膜', '眼膜', '1', '70.00', '100.00', '120.00', '168.00', '238.00', '10', '修护活力', '功效强大', '柑橘有用', '2018-08-02 10:51:16', null, '花花', null);
INSERT INTO `product` VALUES ('13', '莹珠百合匀净亮肤洁面乳', '净洁面', '1', '65.00', '83.00', '103.00', '158.00', '198.00', '10', '匀净亮肤洁面', '功效强大', '柑橘有用', '2018-08-02 10:52:08', null, '花花', null);
INSERT INTO `product` VALUES ('15', '三草两木防晒复萌冰肌套', '防晒', '1', '90.00', '120.00', '148.00', '178.00', '238.00', '10', '防晒复萌', '功效强大', '柑橘有用', '2018-08-02 10:53:30', null, '花花', null);
INSERT INTO `product` VALUES ('16', '三草两木舒敏健康水套装', '健康水', '1', '100.00', '120.00', '140.00', '198.00', '258.00', '10', '防晒复萌', '功效强大', '柑橘有用', '2018-08-02 10:54:01', null, '花花', null);
INSERT INTO `product` VALUES ('17', '极之兰养元补水修护面膜套', '小润', '1', '90.00', '110.00', '130.00', '188.00', '198.00', '10', '养元补水', '功效强大', '柑橘有用', '2018-08-02 10:54:49', null, '花花', null);
INSERT INTO `product` VALUES ('18', '三草两木瞬时舒缓补水喷雾', '喷雾', '1', '56.00', '66.00', '76.00', '79.00', '108.00', '10', '养元补水', '功效强大', '柑橘有用', '2018-08-02 10:55:29', null, '花花', null);
INSERT INTO `product` VALUES ('19', '野大豆籽深润洗发露', '洗头膏', '1', '50.00', '60.00', '70.00', '99.00', '129.00', '10', '深润洗发', '功效强大', '柑橘有用', '2018-08-02 10:56:09', null, '花花', null);
INSERT INTO `product` VALUES ('20', '野大豆籽深润洗护养发套装', '洗护', '1', '60.00', '70.00', '80.00', '115.00', '154.00', '10', '深润洗护养发', '功效强大', '柑橘有用', '2018-08-02 10:56:59', null, '花花', null);
INSERT INTO `product` VALUES ('21', '自然肌悦滋润紧致身体乳套装', '身体乳', '1', '40.00', '50.00', '60.00', '99.00', '169.00', '10', '滋润紧致身体', '功效强大', '柑橘有用', '2018-08-02 10:57:44', null, '花花', null);
INSERT INTO `product` VALUES ('22', '挚爱之书护手霜套盒', '护手霜', '1', '30.00', '40.00', '50.00', '79.00', '109.00', '10', '挚爱之书护手', '功效强大', '柑橘有用', '2018-08-02 10:58:26', null, '花花', null);
INSERT INTO `product` VALUES ('23', '三草两木补水透亮面膜', '小养', '1', '32.00', '38.00', '49.00', '68.00', '108.00', '10', '补水透亮', '功效强大', '柑橘有用', '2018-08-02 10:59:07', null, '花花', null);
INSERT INTO `product` VALUES ('24', '三草两木时光青春精华水', '20+', '1', '83.00', '103.00', '123.00', '168.00', '188.00', '10', '青春精华水，补水透亮', '功效强大', '柑橘有用', '2018-08-02 11:00:03', null, '花花', null);
INSERT INTO `product` VALUES ('25', '三草两木鲜活青春精华水', '25+', '1', '83.00', '103.00', '123.00', '168.00', '188.00', '10', '青春精华水，补水透亮', '功效强大', '柑橘有用', '2018-08-02 11:00:15', null, '花花', null);
INSERT INTO `product` VALUES ('26', '三草两木焕颜青春精华水', '30+', '1', '83.00', '103.00', '123.00', '168.00', '188.00', '10', '青春精华水，补水透亮', '功效强大', '柑橘有用', '2018-08-02 11:00:29', null, '花花', null);
INSERT INTO `product` VALUES ('27', '野大豆籽深润营养焗油膏', '焗油膏', '1', '35.00', '45.00', '55.00', '80.00', '99.00', '10', '深润营养焗油', '功效强大', '柑橘有用', '2018-08-02 11:01:30', null, '花花', null);
INSERT INTO `product` VALUES ('28', '自然肌悦滋养沐浴露', '沐浴露', '1', '46.00', '56.00', '66.00', '88.00', '99.00', '10', '肌悦滋养', '功效强大', '柑橘有用', '2018-08-02 11:02:08', null, '花花', null);
INSERT INTO `product` VALUES ('30', '测试专用产品', '测试', '1', '12.00', '13.00', '14.00', '15.00', '16.00', '10', '', '', '', '2018-08-06 09:44:55', '2018-09-03 12:24:27', '花花', '花花');

-- ----------------------------
-- Table structure for `product_price`
-- ----------------------------
DROP TABLE IF EXISTS `product_price`;
CREATE TABLE `product_price` (
  `id` int(12) NOT NULL,
  `grade` varchar(20) DEFAULT NULL COMMENT '级别分类(如:一级、二级、微商等)',
  `price` double(10,2) DEFAULT NULL COMMENT '价格',
  `productId` int(12) DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_price
-- ----------------------------

-- ----------------------------
-- Table structure for `product_receiving_single`
-- ----------------------------
DROP TABLE IF EXISTS `product_receiving_single`;
CREATE TABLE `product_receiving_single` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `productId` int(12) NOT NULL COMMENT '产品id',
  `receivingSingleId` int(12) NOT NULL COMMENT '收获单子项id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收获单子项，产品对应关系';

-- ----------------------------
-- Records of product_receiving_single
-- ----------------------------
INSERT INTO `product_receiving_single` VALUES ('225', '28', '230');
INSERT INTO `product_receiving_single` VALUES ('226', '27', '231');
INSERT INTO `product_receiving_single` VALUES ('227', '24', '232');
INSERT INTO `product_receiving_single` VALUES ('228', '27', '233');
INSERT INTO `product_receiving_single` VALUES ('229', '5', '234');
INSERT INTO `product_receiving_single` VALUES ('230', '11', '235');

-- ----------------------------
-- Table structure for `product_sales_single`
-- ----------------------------
DROP TABLE IF EXISTS `product_sales_single`;
CREATE TABLE `product_sales_single` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `productId` int(12) NOT NULL COMMENT '产品id',
  `salesSingleId` int(12) NOT NULL COMMENT '售货单子项id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收获单子项与产品对应关系';

-- ----------------------------
-- Records of product_sales_single
-- ----------------------------
INSERT INTO `product_sales_single` VALUES ('48', '26', '48');
INSERT INTO `product_sales_single` VALUES ('49', '28', '49');
INSERT INTO `product_sales_single` VALUES ('50', '28', '50');
INSERT INTO `product_sales_single` VALUES ('51', '5', '51');
INSERT INTO `product_sales_single` VALUES ('52', '11', '52');
INSERT INTO `product_sales_single` VALUES ('53', '28', '53');

-- ----------------------------
-- Table structure for `receiving`
-- ----------------------------
DROP TABLE IF EXISTS `receiving`;
CREATE TABLE `receiving` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `receiptCode` varchar(20) NOT NULL COMMENT '收货单编号',
  `valid` int(1) DEFAULT NULL COMMENT '收货单状态',
  `purchaseTime` date DEFAULT NULL COMMENT '进货时间',
  `totalPrice` double(10,2) DEFAULT NULL COMMENT '总价',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`,`receiptCode`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收获单';

-- ----------------------------
-- Records of receiving
-- ----------------------------
INSERT INTO `receiving` VALUES ('109', 'R20180824001', '3', '2018-08-24', '1380.00', '', '2018-08-24 11:00:20', '2018-08-25 16:18:09', '花花', null);
INSERT INTO `receiving` VALUES ('110', 'R20180910001', '3', '2018-09-10', '805.00', '', '2018-09-10 08:12:50', '2018-09-10 08:16:38', '花花', null);

-- ----------------------------
-- Table structure for `receiving_single`
-- ----------------------------
DROP TABLE IF EXISTS `receiving_single`;
CREATE TABLE `receiving_single` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `receivingId` int(12) NOT NULL COMMENT '收获单id',
  `valid` int(1) DEFAULT NULL COMMENT '收获单状态',
  `purchasePrice` double(10,2) DEFAULT NULL COMMENT '进货价格',
  `count` int(5) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收获单子项';

-- ----------------------------
-- Records of receiving_single
-- ----------------------------
INSERT INTO `receiving_single` VALUES ('230', '109', '3', '46.00', '10');
INSERT INTO `receiving_single` VALUES ('231', '109', '3', '45.00', '9');
INSERT INTO `receiving_single` VALUES ('232', '109', '3', '103.00', '5');
INSERT INTO `receiving_single` VALUES ('233', '110', '3', '35.00', '5');
INSERT INTO `receiving_single` VALUES ('234', '110', '3', '75.00', '5');
INSERT INTO `receiving_single` VALUES ('235', '110', '3', '85.00', '3');

-- ----------------------------
-- Table structure for `sales`
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `saleCode` varchar(20) NOT NULL COMMENT '售货单编号',
  `valid` int(1) DEFAULT NULL COMMENT '状态',
  `totalPrice` double(10,2) DEFAULT NULL COMMENT '总价',
  `saleDate` date DEFAULT NULL COMMENT '销售时间',
  `note` varchar(255) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(50) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`,`saleCode`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='售货单';

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES ('25', 'S20180910001', '1', '632.00', '2018-09-10', '', '2018-09-10 11:15:45', '2018-09-10 11:17:07', '花花', null);

-- ----------------------------
-- Table structure for `sales_single`
-- ----------------------------
DROP TABLE IF EXISTS `sales_single`;
CREATE TABLE `sales_single` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
  `salesId` int(12) NOT NULL COMMENT '售货单id',
  `valid` int(1) DEFAULT NULL COMMENT '状态',
  `salePrice` double(10,2) DEFAULT NULL COMMENT '销售价格',
  `count` int(5) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='售货单子项';

-- ----------------------------
-- Records of sales_single
-- ----------------------------
INSERT INTO `sales_single` VALUES ('51', '25', '1', '95.00', '3');
INSERT INTO `sales_single` VALUES ('52', '25', '1', '85.00', '3');
INSERT INTO `sales_single` VALUES ('53', '25', '1', '46.00', '2');

-- ----------------------------
-- Table structure for `sys_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限',
  `description` varchar(100) DEFAULT NULL COMMENT '权限描述',
  `createdUser` varchar(50) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedUser` varchar(50) DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `createdUser` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建日期',
  `modifiedUser` varchar(50) DEFAULT NULL COMMENT '修改人',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_roles_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permissions`;
CREATE TABLE `sys_roles_permissions` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `roleId` int(12) DEFAULT NULL COMMENT '角色Id',
  `permissionId` int(12) DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_users`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `valid` tinyint(4) DEFAULT NULL,
  `createdUser` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(50) DEFAULT NULL COMMENT '修改人',
  `modifiedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', 'a960ecc67b93d8ee6e1e13564ca1a619', '8a9320b6601fa5ee216526d2b9c1ebd0', 'admin', 'admin@qq.com', '17600365220', '1', 'admin', '2018-09-29 23:25:27', null, '2018-10-01 12:26:42');
INSERT INTO `sys_users` VALUES ('2', 'yanglin', '8634795761737db8b90e50850b2f249c', '44139ada-497f-4cd8-8fd2-317d41107924', '杨木木', 'yanglin@qq.com', '15738847570', '1', null, '2018-09-30 23:25:58', '杨木木', '2018-10-08 17:34:38');
INSERT INTO `sys_users` VALUES ('5', 'zhangsan', 'f57d44ae20c04d59215e3881010fc3eb', '34be5b43-c628-4071-aba4-4518f82c895e', '张三', 'zhangsan@qq.com', '13838384388', '1', '\n					杨木木\n				', '2018-10-08 14:40:49', '杨木木', '2018-10-08 15:53:58');

-- ----------------------------
-- Table structure for `sys_users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_roles`;
CREATE TABLE `sys_users_roles` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `userId` int(12) DEFAULT NULL COMMENT '用户id',
  `roleId` int(12) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_users_roles
-- ----------------------------
