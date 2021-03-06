USE holdon;

DROP TABLE IF EXISTS account_info;
CREATE TABLE account_info (
  id          INT(20)      NOT NULL AUTO_INCREMENT,
  user_name   VARCHAR(50)  NOT NULL,
  password    VARCHAR(100) NOT NULL,
  salt        VARCHAR(50)  NOT NULL,
  status      TINYINT(2)   NOT NULL DEFAULT 0,
  type        TINYINT(2)   NOT NULL DEFAULT 0,
  create_time DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '注册时间',
  update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE (user_name)
)
  ENGINE = innodb
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;

