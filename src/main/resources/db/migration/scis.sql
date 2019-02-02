-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `admin` VALUES (1, 1001, '管理员', '男', '123456', 123456);

-- ------------------------------
-- Table structure for bk_config
-- ------------------------------
DROP TABLE IF EXISTS `bk_config`;
CREATE TABLE `bk_config` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `config_type` VARCHAR(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '配置项',
  `code` INT(4) NOT NULL DEFAULT 0 COMMENT '配置code',
  `zh_name` VARCHAR(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '内容项',
  `add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_config_type` (`config_type`) USING BTREE COMMENT '补录单号索引',
  KEY `idx_code` (`code`)USING BTREE COMMENT '更新时间索引'
)
ENGINE=INNODB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
ROW_FORMAT=COMPACT
COMMENT='系统配置表'
;
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('ProductType','1','html/css');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('ProductType','2','数据库');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('PictureSize','1','210×210');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('PictureSize','2','1000×563');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('PictureType','1','首页');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('PictureType','2','内容');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('ProductTheme','1','节日');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('ProductTheme','2','汽车');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('TaoBaoUrl','1','http://www.taobao.com');

-- ----------------------------
-- Table structure for bk_order
-- ----------------------------
DROP TABLE IF EXISTS bk_order;
CREATE TABLE `bk_order` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `order_id` INT(15) NOT NULL DEFAULT '0' COMMENT '订单编号',
  `product_id` INT(10) NOT NULL DEFAULT '0' COMMENT '作品ID',
  `product_name` VARCHAR(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '作品名称',
  `price` DECIMAL(6,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `product_type` INT(4) NOT NULL DEFAULT '0' COMMENT '作品类型 0-html 1-数据库 2-asp 3-python',
  `buyer_school` VARCHAR(24) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '学校',
  `buyer_email` VARCHAR(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '邮箱',
  `order_status` INT(3) NOT NULL DEFAULT 1 COMMENT '订单状态 1.交易成功 2.交易失败',
  `send_time` TIMESTAMP NOT NULL DEFAULT '1992-01-18 00:00:00' COMMENT '发送时间',
  `remarks` VARCHAR(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注 失败原因',
  `sales_man` VARCHAR(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '销售员',
  `add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` INT(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  UNIQUE INDEX(`order_id`),
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`) USING BTREE COMMENT '订单编号索引',
  KEY `idx_product_name` (`product_name`) USING BTREE COMMENT '作品名称索引',
  KEY `idx_product_type` (`product_type`) USING BTREE COMMENT '作品类型索引',
  KEY `idx_buyer_school` (`buyer_school`) USING BTREE COMMENT '学校索引',
  KEY `idx_order_status` (`order_status`) USING BTREE COMMENT '订单状态索引',
  KEY `idx_add_time` (`add_time`) USING BTREE COMMENT '添加时间索引',
  KEY `idx_update_time` (`update_time`) USING BTREE COMMENT '更新时间索引'
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='订单表';

-- ---------------------------------
-- Table structure for bk_product
-- ---------------------------------
DROP TABLE IF EXISTS bk_product;
CREATE TABLE `bk_product` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `product_id` int(8) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `product_type` int(4) NOT NULL DEFAULT '0' COMMENT '商品类型',
  `product_theme` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '主题',
  `product_name` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '商品名称',
  `show_path` varchar(300) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '首页图片链接',
  `price` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `html_num` INT(3) NOT NULL DEFAULT 10 COMMENT '页面张数',
  `link` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '商品存放链接',
  `link_code` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '提取码',
  `product_desc` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '商品描述',
  `product_synopsis` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '详情简介',
  `remark` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  UNIQUE INDEX(`product_id`),
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`) USING BTREE COMMENT '商品编码索引',
  KEY `idx_product_name` (`product_name`) USING BTREE COMMENT '商品名称索引',
  KEY `idx_price` (`price`) USING BTREE COMMENT '商品价格索引',
  KEY `idx_update_time` (`update_time`) USING BTREE COMMENT '更新时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='商品表';

-- -----------------------------------------
-- Table structure for bk_product_picture
-- -----------------------------------------
DROP TABLE IF EXISTS bk_product_picture;
CREATE TABLE `bk_product_picture` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `product_id` int(8) NOT NULL DEFAULT '0' COMMENT '商品编码',
  `picture_type` int(8) NOT NULL DEFAULT '0' COMMENT '图片类型 1-首页 2-内容',
  `picture_size` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '图片尺寸',
  `picture_path` varchar(300) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '图片链接',
  `remark` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`) USING BTREE COMMENT '商品编码索引',
  KEY `idx_picture_type` (`picture_type`) USING BTREE COMMENT '商品类型索引',
  KEY `idx_update_time` (`update_time`) USING BTREE COMMENT '更新时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='商品图片';