CREATE TABLE IF NOT EXISTS employee (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  gender varchar(100) NOT NULL,
  position varchar(100) DEFAULT NULL,
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
truncate employee;