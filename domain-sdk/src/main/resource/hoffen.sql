 CREATE TABLE `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `accountant_id` bigint(20) DEFAULT NULL,
  `company_uu_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `plan_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `identifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `role_identifier` varchar(255) DEFAULT NULL,
  `user_uu_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `work_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `jwt_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `token` longtext DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `title` varchar(1000) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `from` varchar(100) DEFAULT NULL,
  `to` varchar(100) DEFAULT NULL,
  `body` longtext NOT NULL,
  `notification_type` int DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `time_to_live` int DEFAULT NULL,
  `collapse_key` varchar(50) DEFAULT NULL,
  `content_available` tinyint(1) DEFAULT NULL,
  `json_data` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `session` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `closed_at` datetime(6) DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `tracking_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  `tracking_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `api_key` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

ALTER TABLE `company`
ADD COLUMN `account_manager` VARCHAR(150) NULL DEFAULT NULL AFTER `plan_id`,
ADD INDEX `company_plan_id_idx` (`plan_id` ASC) VISIBLE;

ALTER TABLE `company`
ADD CONSTRAINT `company_plan_id`
  FOREIGN KEY (`plan_id`)
  REFERENCES `plan` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

-- plan script
INSERT INTO `plan` VALUES (1,'2021-07-19 18:30:16.903481',_binary '\0',1,'2021-07-19 18:30:16.903486','Pro plan','Pro'),(2,'2021-07-19 18:30:34.047472',_binary '\0',1,'2021-07-19 18:30:34.047475','Advance plan','Advance'),(3,'2021-07-19 18:30:43.430619',_binary '\0',1,'2021-07-19 18:30:43.430621','Custom plan','Custom'),(4,'2021-07-19 18:30:43.430619',_binary '\0',1,'2021-07-19 18:30:43.430621','Cancel plan','Cancel');


CREATE TABLE `plan_configuration` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `plan_key` VARCHAR(100) NULL,
  `plan_value` VARCHAR(20) NULL,
  `plan_id` BIGINT(20) NULL,
  `is_deleted` BIT(1) NULL,
  `status` INT NULL,
  `created_at` DATETIME(6) NULL,
  `updated_at` DATETIME(6) NULL,
  PRIMARY KEY (`id`),
  INDEX `plan_config_plan_id_idx` (`plan_id` ASC) VISIBLE,
  CONSTRAINT `plan_config_plan_id`
    FOREIGN KEY (`plan_id`)
    REFERENCES `plan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SELECT @last_id := id FROM hoffen.plan where name ='Pro';


-- plan id 1 is PRO
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('NUMBER_OF_USERS','5',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('FREE_TRIAL_DAYS','7',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('SESSION_RECORDED','NO',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('REPORTS','NO',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');


SELECT @last_id := id FROM hoffen.plan where name ='Advance';

-- plan id 2 is Advance
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('NUMBER_OF_USERS','1',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('FREE_TRIAL_DAYS','7',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('SESSION_RECORDED','YES',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
INSERT INTO `plan_configuration`
(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
VALUES
('REPORTS','YES',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');


SELECT @last_id := id FROM hoffen.plan where name ='Custom';

-- plan id 3 is Advance
--INSERT INTO `plan_configuration`
--(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
--VALUES
--('NUMBER_OF_USERS','1',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
--INSERT INTO `plan_configuration`
--(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
--VALUES
--('FREE_TRIAL_DAYS','7',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');
--INSERT INTO `plan_configuration`
--(`plan_key`,`plan_value`,`plan_id`,`is_deleted`,`status`,`created_at`,`updated_at`)
--VALUES
--('SESSION_RECORDED_STORED','YES',@last_id,0,1,'2021-07-21 10:05:47.000000','2021-07-21 10:05:47.000000');


ALTER TABLE `plan`
ADD COLUMN `plan_price` DECIMAL(12,2) NULL AFTER `name`;

ALTER TABLE `company`
ADD COLUMN `plan_price` DECIMAL(12,2) NULL AFTER `name`;

update plan set plan_price = 0;
update company set plan_price = 0 where plan_id is not null;

update plan set plan_price = 9 where id = 1;
update plan set plan_price = 29 where id = 2;

update company set plan_price = 9 where plan_id = 1;
update company set plan_price = 29 where plan_id = 2;



ALTER TABLE `plan`
ADD COLUMN `old_price` DECIMAL(12,2) NULL DEFAULT NULL AFTER `plan_price`,
ADD COLUMN `price_date` DATETIME(6) NULL AFTER `old_price`,
ADD COLUMN `old_price_date` DATETIME(6) NULL AFTER `price_date`,
ADD COLUMN `constant` VARCHAR(45) NULL AFTER `old_price_date`;
ALTER TABLE `company`
DROP COLUMN `plan_price`;
ALTER TABLE `company`
ADD UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE;


CREATE TABLE `company_future_plan` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_at` VARCHAR(45) NULL,
  `is_deleted` VARCHAR(45) NULL,
  `status` int(11) NULL,
  `updated_at` VARCHAR(45) NULL,
  `plan_id` BIGINT(20) NULL,
  `plan_price` DECIMAL(12,2) NULL,
  `config` VARCHAR(400) NULL,
  `company_id` BIGINT(20) NULL,
  PRIMARY KEY (`id`),
  INDEX `cfp_plan_id_idx` (`plan_id` ASC) VISIBLE,
  INDEX `cfp_company_id_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `cfp_plan_id`
    FOREIGN KEY (`plan_id`)
    REFERENCES `plan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cfp_company_id`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

update plan set constant ='CANCEL' where name= 'Cancel';
update plan set constant ='PREDEFINE' where name IN ('Pro','Advance');


CREATE TABLE `company_statistics` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created_at` VARCHAR(45) NULL,
  `is_deleted` VARCHAR(45) NULL,
  `status` int(11) NULL,
  `updated_at` VARCHAR(45) NULL,
  `active_user` int(11) NULL,
  `active_user_date` date NULL,
  `company_id` BIGINT(20) NULL,
  `session_time` TIME NULL,
  PRIMARY KEY (`id`),
  INDEX `cs_company_id_idx` (`company_id` ASC) VISIBLE,
  CONSTRAINT `cs_company_id`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `company`
ADD COLUMN `trial_end_date` DATE NULL DEFAULT NULL AFTER `account_manager`;

ALTER TABLE `user`
ADD COLUMN `plan_start_date` DATETIME(6) NULL AFTER `work_email`,
ADD COLUMN `plan_end_date` DATETIME(6) NULL AFTER `plan_start_date`;