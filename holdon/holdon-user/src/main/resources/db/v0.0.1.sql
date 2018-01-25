USE holdon;

DROP TABLE IF EXISTS holdon_user;
CREATE TABLE holdon_user (
  id             INT(20)      NOT NULL AUTO_INCREMENT,
  login_name     VARCHAR(50)  NOT NULL,
  login_password VARCHAR(100) NOT NULL,
  create_date    DATETIME     NOT NULL DEFAULT current_timestamp COMMENT '注册时间',
  update_date    DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  PRIMARY KEY (id),
  UNIQUE (login_name)
)
  ENGINE = innodb
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;

