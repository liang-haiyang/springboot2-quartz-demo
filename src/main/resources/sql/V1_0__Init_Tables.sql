CREATE TABLE User(
  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '表主键',
  name VARCHAR(20) COMMENT '企业编码',
  phone VARCHAR(20) COMMENT '数据表编码',
  email VARCHAR(20) COMMENT '行业',
  address VARCHAR(40) COMMENT '已支持接入总线类型',
 `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间,此字段为无用字段, 发送kafka不需要'
);
CREATE INDEX device_bus_protocol_id_index ON User(id);
