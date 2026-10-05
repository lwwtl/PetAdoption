-- 按 2020 年 MyBatis Mapper 的 insert 字段顺序补的建表脚本。
-- 表之间没有外键，和当时的实现一致。
-- 日志表的 id 由数据库自增。代码插入时会带上默认值 0，MySQL 会把它当成下一条自增 id。
-- 不要开启 NO_AUTO_VALUE_ON_ZERO。

CREATE DATABASE IF NOT EXISTS petadoption DEFAULT CHARACTER SET utf8;
USE petadoption;

DROP TABLE IF EXISTS t_userlog;
DROP TABLE IF EXISTS t_log;
DROP TABLE IF EXISTS t_apply;
DROP TABLE IF EXISTS t_pet;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_admin;

CREATE TABLE t_admin (
    adminId VARCHAR(64) NOT NULL PRIMARY KEY,
    adminAccount VARCHAR(64) NULL,
    adminPassword VARCHAR(64) NULL,
    adminName VARCHAR(64) NULL,
    adminAge VARCHAR(16) NULL,
    adminSex VARCHAR(16) NULL,
    adminTelephone VARCHAR(32) NULL,
    adminEmail VARCHAR(128) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_user (
    userId VARCHAR(64) NOT NULL PRIMARY KEY,
    userAccount VARCHAR(64) NULL,
    userPassword VARCHAR(64) NULL,
    userName VARCHAR(64) NULL,
    userAge VARCHAR(16) NULL,
    userSex VARCHAR(16) NULL,
    userTelephone VARCHAR(32) NULL,
    userEmail VARCHAR(128) NULL,
    userAddress VARCHAR(255) NULL,
    userState VARCHAR(32) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_pet (
    petId VARCHAR(64) NOT NULL PRIMARY KEY,
    petName VARCHAR(64) NULL,
    petSex VARCHAR(16) NULL,
    petSub VARCHAR(64) NULL,
    petType VARCHAR(64) NULL,
    petBir VARCHAR(32) NULL,
    petDetail TEXT NULL,
    petPic TEXT NULL,
    petState VARCHAR(32) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_apply (
    applyId VARCHAR(64) NOT NULL PRIMARY KEY,
    applyUserName VARCHAR(64) NULL,
    applyPetName VARCHAR(64) NULL,
    applyUserSex VARCHAR(16) NULL,
    applyUserAddress VARCHAR(255) NULL,
    applyUserTelephone VARCHAR(32) NULL,
    applyUserState VARCHAR(32) NULL,
    applyTime VARCHAR(32) NULL,
    applyState VARCHAR(32) NULL,
    applyUserId VARCHAR(64) NULL,
    applyPetId VARCHAR(64) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_log (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    aId VARCHAR(64) NULL,
    adminAction VARCHAR(64) NULL,
    object VARCHAR(255) NULL,
    createTime VARCHAR(32) NULL,
    url VARCHAR(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_userlog (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    userId VARCHAR(64) NULL,
    userAction VARCHAR(64) NULL,
    petId VARCHAR(64) NULL,
    createTime VARCHAR(32) NULL,
    url VARCHAR(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_admin (
    adminId, adminAccount, adminPassword, adminName, adminAge, adminSex, adminTelephone, adminEmail
) VALUES (
    'admin-seed', 'admin', 'admin', '管理员', '20', '男', '13800000000', 'admin@example.com'
);
