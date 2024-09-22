SET SQL_SAFE_UPDATES = 0;
DELETE FROM `quest_web`.`user` WHERE (`user_name` = 'mocktest');
delete from user_role where user_id='mocktest';
SET SQL_SAFE_UPDATES = 1;