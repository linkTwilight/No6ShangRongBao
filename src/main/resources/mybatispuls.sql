use mybatis_plus;
CREATE TABLE product
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
    price INT(11) DEFAULT 0 COMMENT '价格',
    version INT(11) DEFAULT 0 COMMENT '乐观锁版本号',
    PRIMARY KEY (id)
);

INSERT INTO product (id, NAME, price) VALUES (1, '笔记本', 100);



ALTER TABLE user ADD COLUMN creat_time DATETIME COMMENT '创建时间';
ALTER TABLE user ADD COLUMN update_time DATETIME COMMENT '修改时间';
ALTER TABLE user ADD COLUMN is_deleted tinyint DEFAULT 0 COMMENT '是否删除了 0-false-未删除 1-true-删除';

SELECT *
FROM user WHERE age > ? AND age < ?;