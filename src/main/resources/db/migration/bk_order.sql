DROP TABLE IF EXISTS bk_order;
CREATE TABLE `bk_order` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `order_id` int(15) NOT NULL DEFAULT '0' COMMENT '订单编号',
  `product_id` int(10) NOT NULL DEFAULT '0' COMMENT '作品ID',
  `product_name` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '作品名称',
  `price` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `product_type` int(4) NOT NULL DEFAULT '0' COMMENT '作品类型 0-html 1-数据库 2-asp 3-python',
  `buyer_shcool` varchar(24) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '学校',
  `buyer_email` varchar(30) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '邮箱',
  `order_status` INT(3) NOT NULL DEFAULT 1 COMMENT '订单状态 1.交易成功 2.交易失败',
  `send_time` timestamp NOT NULL DEFAULT '1992-01-18 00:00:00' COMMENT '发送时间',
  `remarks` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '备注 失败原因',
  `sales_man` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '销售员',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  UNIQUE INDEX(`order_id`),
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`) USING BTREE COMMENT '订单编号索引',
  KEY `idx_product_name` (`product_name`) USING BTREE COMMENT '作品名称索引',
  KEY `idx_product_type` (`product_type`) USING BTREE COMMENT '作品类型索引',
  KEY `idx_buyer_shcool` (`buyer_shcool`) USING BTREE COMMENT '学校索引',
  KEY `idx_order_status` (`order_status`) USING BTREE COMMENT '订单状态索引',
  KEY `idx_add_time` (`add_time`) USING BTREE COMMENT '添加时间索引',
  KEY `idx_update_time` (`update_time`) USING BTREE COMMENT '更新时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='订单表';

INSERT INTO bk_order (order_id,product_id,product_name,price,product_type,buyer_shcool,buyer_email,order_status,sales_man) VALUE ('k20181001','1001','途牛焦糖','26.05',0,'安徽工业大学','ahuthj@163.com',1,'华剑');