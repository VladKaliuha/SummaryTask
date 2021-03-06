SET NAMES utf8;

drop database IF EXISTS ST4DB;
create DATABASE ST4DB CHARACTER SET utf8 COLLATE utf8_bin;

USE ST4DB;


create TABLE `role`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` varchar(31) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

insert into role(name)
values ('student');
insert into role(name)
values ('admin');
insert into role(name)
values ('blocked');


create TABLE `user`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) NOT NULL,
    `last_name`  varchar(255) NOT NULL,
    `login`      varchar(255) NOT NULL UNIQUE,
    `email`      varchar(255) NOT NULL UNIQUE,
    `password`   varchar(255) NOT NULL,
    `male`       varchar(255),
    `role_id`    INT DEFAULT 1 ,
    CONSTRAINT role_id_fk
           FOREIGN KEY (role_id)
           REFERENCES `role` (`id`) ON delete CASCADE,
    PRIMARY KEY (`id`)
);

insert into user(first_name, last_name, login, email, password, male, role_id)
values ('root', 'user', 'admin', 'admin@gmail.com', 'admin', 'male', 2);

insert into user(first_name, last_name, login, email, password, male, role_id)
values ('testStudent', 'tester', 'student', 'student@gmail.com', 'student', 'female', 1);


create TABLE `subject`
(
    `id`   INT          NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

insert into subject
values (default, 'Math');
insert into subject
values (default, 'Programming');
insert into subject
values (default, 'Химия');

create TABLE `result`
(
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    `date` varchar(255) NOT NULL,
    `test_name` varchar(255) NOT NULL,
    `user_id` INT NOT NULL REFERENCES `user` (`id`),
    `test_id` INT NOT NULL REFERENCES `test` (`id`),
    `result`  INT NOT NULL
);

create TABLE `answer_type`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` varchar(31) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);

insert into answer_type(name)
values ('oneTrue');
insert into answer_type(name)
values ('manyTrue');
insert into answer_type(name)
values ('yourAnswer');



create TABLE `test`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL UNIQUE,
    `subject_id` INT          NOT NULL,
    `complexity` INT          NOT NULL,
    `time`       INT         NOT NULL DEFAULT 10,
    PRIMARY KEY (`id`),
    CONSTRAINT subject_id_fk
           FOREIGN KEY (subject_id)
           REFERENCES `subject` (`id`) ON delete CASCADE,
    CONSTRAINT CK_test_complexity CHECK (complexity > 0 AND complexity < 10)
);

insert into test(name, subject_id, complexity, time)
values ('Первый тест по математике', 1, 5, 5);

insert into test(name, subject_id, complexity, time)
values ('Second math test', 1, 2, 10);

insert into test(name, subject_id, complexity, time)
values ('A', 1, 3, 8);

insert into test(name, subject_id, complexity, time)
values ('B', 1, 7, 10);

insert into test(name, subject_id, complexity, time)
values ('Тест по химии', 3, 6, 5);

insert into test(name, subject_id, complexity, time)
values ('Programming test', 2, 5, 5);



create TABLE `question`
(
    `id`             INT          NOT NULL AUTO_INCREMENT,
    `test_id`        INT          NOT NULL,
    `text`           varchar(511) NOT NULL,
    `answer_type_id` INT          NOT NULL DEFAULT 1,
    CONSTRAINT answer_type_id_fk
           FOREIGN KEY (answer_type_id)
           REFERENCES `answer_type` (`id`) ON delete CASCADE,
    CONSTRAINT test_id_fk
           FOREIGN KEY (test_id)
           REFERENCES `test` (`id`) ON delete CASCADE,
    PRIMARY KEY (`id`)
);

insert into question(test_id, text, answer_type_id)
values (1, '2 + 2 = ?', 1);
insert into question(test_id, text, answer_type_id)
values (1, '|x| = 1 Какие значения может принимать "х"?', 2);
insert into question(test_id, text, answer_type_id)
values (1, '2 + 2 * 2 = ?', 3);

insert into question(test_id, text, answer_type_id)
values (2, '3 + 3 = ?', 1);
insert into question(test_id, text, answer_type_id)
values (2, '5 - 3 + 4 = ?', 3);
insert into question(test_id, text, answer_type_id)
values (2, '231313 / 123 = ?', 3);

insert into question(test_id, text, answer_type_id)
values (3, 'Жидкий метал?', 1);
insert into question(test_id, text, answer_type_id)
values (3, 'Наименьшая неделимая частица элементов?', 1);
insert into question(test_id, text, answer_type_id)
values (3, 'Газ, поддерживающий горение?', 3);




create TABLE `answer`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `text`        varchar(511) NOT NULL,
    `is_right`    BOOLEAN      NOT NULL DEFAULT false,
    `question_id` INT          NOT NULL,
     CONSTRAINT question_id_fk
           FOREIGN KEY (question_id)
           REFERENCES `question` (`id`) ON delete CASCADE,
    PRIMARY KEY (`id`)
);


insert into answer(text, is_right, question_id)
values ('5', false, 1);
insert into answer(text, is_right, question_id)
values ('4', true, 1);

insert into answer(text, is_right, question_id)
values ('7', false, 2);
insert into answer(text, is_right, question_id)
values ('1', true, 2);
insert into answer(text, is_right, question_id)
values ('-1', true, 2);
insert into answer(text, is_right, question_id)
values ('5', false, 2);


insert into answer(text, is_right, question_id)
values ('6', true, 3);

insert into answer(text, is_right, question_id)
values ('7', false, 4);
insert into answer(text, is_right, question_id)
values ('5', false, 4);
insert into answer(text, is_right, question_id)
values ('6', true, 4);

insert into answer(text, is_right, question_id)
values ('6', true, 5);

insert into answer(text, is_right, question_id)
values ('1880.6', true, 6);

insert into answer(text, is_right, question_id)
values ('Ртуть', true, 7);
insert into answer(text, is_right, question_id)
values ('Железо', false, 7);
insert into answer(text, is_right, question_id)
values ('Вода', false, 7);

insert into answer(text, is_right, question_id)
values ('Атом', true, 8);
insert into answer(text, is_right, question_id)
values ('Молекула', false, 8);
insert into answer(text, is_right, question_id)
values ('Клетка', false, 8);

insert into answer(text, is_right, question_id)
values ('Кислород', true, 9);


select *
from user;
select *
from test;
select *
from question;
select *
from answer;
select *
from subject;
