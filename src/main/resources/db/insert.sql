SET FOREIGN_KEY_CHECKS = 0;  -- used to disable a foreign key in order to import the database

truncate table pet; -- used to remove all rows from a table or specified partition of a table, without logging the individual row deletions
truncate table store;

INSERT into store(`id`, `name`, `location`, `contact_no`)
VALUES(21, 'superstore', 'nassarawa', '78766566567');

INSERT into pet(`id`, `name`, `color`, `breed`, `age`, `pet_sex`, `store_id`)
VALUES(31, 'jill', 'blue', 'parrot', 6, 'MALE', 21),
(32, 'piggy', 'bridge', 'bird', 9, 'FEMALE', 21),
(33, 'ship', 'zinc', 'dragon', 11, 'MALE', 21);

INSERT into store(`id`, `name`, `location`, `contact_no`)
VALUES(67, 'stars', 'cally', '987065432');

SET FOREIGN_KEY_CHECKS = 1;  -- used to enable the foreign key after all database import and operations

