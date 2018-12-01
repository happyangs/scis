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

INSERT INTO bk_order (order_id,product_id,product_name,price,product_type,buyer_shcool,buyer_email,order_status,sales_man) VALUE ('20181001','1001','途牛焦糖','26.05',0,'安徽工业大学','ahuthj@163.com',1,'华剑');