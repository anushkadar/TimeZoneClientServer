CREATE DATABASE IF NOT EXISTS sample_db;
USE sample_db;

CREATE TABLE IF NOT EXISTS `samle_db`.`date_tb` (
    `id`INT NOT NULL AUTO_INCREMENT,
    `date_str` VARCHAR(500) NULL,
    `date` DATETIME NULL,
    `local_time` TIME NULL,
    `local_date`  DATE NULL,
    `local_datetime_dt` DATETIME NULL,
    `local_datetime_ts` TIMESTAMP NULL,
    `offset_datetime` TIMESTAMP NULL,
    `zoned_datetime` TIMESTAMP NULL,

    PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `samle_db`.`date_tb` (
                `id`INT NOT NULL AUTO_INCREMENT,
                `date_str` VARCHAR(500) NULL,
                `date` DATETIME NULL,
                `local_time` TIME NULL,
                `local_date`  DATE NULL,
                `local_datetime` DATETIME NULL,
                `offset_datetime` DATETIME NULL,
                `zoned_datetime` DATETIME NULL,

                PRIMARY KEY (`id`));
