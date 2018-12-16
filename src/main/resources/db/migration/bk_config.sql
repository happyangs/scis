CREATE TABLE IF NOT EXISTS `bk_config` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `config_type` VARCHAR(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '配置项',
  `code` INT(4) NOT NULL DEFAULT 0 COMMENT '配置code',
  `zh_name` VARCHAR(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '内容项',
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

INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('productType','1001','html/css');
INSERT INTO bk_config(config_type,CODE,zh_name) VALUES('productType','1002','数据库Web');