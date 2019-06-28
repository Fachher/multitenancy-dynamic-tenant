use dbs;

CREATE TABLE `user` (
 `user_id` INT(11) NOT NULL,
 `password` VARCHAR(255) NOT NULL, `username` VARCHAR(255) NOT NULL,
 `active` BIT(1) NULL DEFAULT NULL, `tenant` VARCHAR(255) NULL DEFAULT NULL,
 PRIMARY KEY (`user_id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

CREATE TABLE `role` (
 `role_id` INT(11) NOT NULL, `role` VARCHAR(255) NULL DEFAULT NULL,
 PRIMARY KEY (`role_id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

CREATE TABLE `user_role` (
 `user_id` INT(11) NOT NULL, `role_id` INT(11) NOT NULL,
 PRIMARY KEY (`user_id`, `role_id`),
 INDEX `FKa68196081fvovjhkek5m97n3y` (`role_id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

insert into master_tenant values (1, '12345', 'master', 'jdbc:mysql://localhost:33060/dbs?useSSL=false', 'root', 0);
insert into user values (1, '$2a$10$dvYazhr0sZsdKgs25P1Uq.ryIItWiZLlxAyMH2nLlASGpYVZjZzDe', 'root', true, 'master');
insert into role values (1, 'ADMIN');
insert into user_role values (1,1);
commit;