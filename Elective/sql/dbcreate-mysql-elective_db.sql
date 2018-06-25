CREATE DATABASE elective_db

CHARACTER SET utf8
COLLATE utf8_general_ci;

SHOW CREATE DATABASE elective_db;

USE elective_db;

CREATE TABLE roles(
	`id` INT(11),
	`name` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE (`name`)
);

INSERT INTO roles (id, name) VALUES(0, 'admin');
INSERT INTO roles (id, name) VALUES(1, 'lecturer');
INSERT INTO roles (id, name) VALUES(2, 'student');

CREATE TABLE users(
	`id` INT(11) AUTO_INCREMENT,
	`login` VARCHAR(15) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	`first_name` VARCHAR(30) NOT NULL,
	`last_name` VARCHAR(30) NOT NULL,
	`role_id` int(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE (`login`),
	FOREIGN KEY (`role_id`) REFERENCES roles(`id`)
	ON DELETE CASCADE
	ON UPDATE RESTRICT
);

INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Ivan', 'Ivanov', 0); -- 1 (order id)
INSERT INTO users VALUES(DEFAULT, 'profdata', 'profdata', 'Datascience', 'Datascienceov', 1); -- 2
INSERT INTO users VALUES(DEFAULT, 'profapp', 'profapp', 'Appdata', 'Appdataov', 1); -- 3
INSERT INTO users VALUES(DEFAULT, 'profbigdata', 'profbigdata', 'Bigdata', 'Bigdataov', 1); -- 4
INSERT INTO users VALUES(DEFAULT, 'profbayes', 'profbayes', 'Bayesian', 'Bayesianov', 1); -- 5
INSERT INTO users VALUES(DEFAULT, 'profматан', 'profматан', 'Матанализ', 'Матанализов', 1); -- 6
INSERT INTO users VALUES(DEFAULT, 'student1', 'student1', 'Student1', 'Student1ov', 2); -- 7
INSERT INTO users VALUES(DEFAULT, 'student2', 'student2', 'Student2', 'Student2ov', 2); -- 8
INSERT INTO users VALUES(DEFAULT, 'student3', 'student3', 'Student3', 'Student3ov', 2); -- 9
INSERT INTO users VALUES(DEFAULT, 'student4', 'student4', 'Student4', 'Student4ov', 2); -- 10
INSERT INTO users VALUES(DEFAULT, 'студент5', 'студент5', 'Студент5', 'Студент5ов', 2); -- 11
INSERT INTO users VALUES(DEFAULT, 'студент6', 'студент6', 'Студент6', 'Студент6ов', 2); -- 12

-- --------------------------------------------------------------
-- STATUSES
-- statuses for courses
-- --------------------------------------------------------------
CREATE TABLE statuses(
	`id` INT(11),
	`name` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE (`name`)
);

-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Status entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------

INSERT INTO statuses (id, name) VALUES(0, 'finished');
INSERT INTO statuses (id, name) VALUES(1, 'progress');
INSERT INTO statuses (id, name) VALUES(2, 'expected');
	
-- --------------------------------------------------------------
-- CATEGORIES
-- categories names of courses
-- --------------------------------------------------------------
CREATE TABLE categories(
	`id` INT(11),
	`name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE (`name`)
);

INSERT INTO categories (id, name) VALUES(0, 'Data Science'); -- Наука о данных
INSERT INTO categories (id, name) VALUES(1, 'Mathematics and Logic'); -- Математика и логика

-- --------------------------------------------------------------
-- COURSES
-- --------------------------------------------------------------
CREATE TABLE courses(
	`id` INT(11) AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`duration` INT(11) NOT NULL,
	`category_id` INT(11) NOT NULL,
	`status_id` INT(11) NOT NULL,
	`professor_id` INT(11) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE (`name`),
	FOREIGN KEY (`category_id`) REFERENCES categories(`id`),
	FOREIGN KEY (`status_id`) REFERENCES statuses(`id`),
	FOREIGN KEY (`professor_id`) REFERENCES users(`id`)
);

-- Data Science
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)
								VALUES(DEFAULT, 'Data Science', '9', 0, 0, 2); -- 1 (order id)
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
								VALUES(DEFAULT, 'Statistics with R', '8', 0, 1, 2); -- 2
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
								VALUES(DEFAULT, 'Big Data', '15', 0, 2, 4); -- 3
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
								VALUES(DEFAULT, 'Applied Data Science with Python', '7', 0, 0, 3); -- 4

-- Mathematics and Logic	
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
								VALUES(DEFAULT, 'Математический анализ', '18', 1, 1, 6); -- 5
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
		                        VALUES(DEFAULT, 'Game Theory', '13', 1, 2, 5); -- 6
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
		                        VALUES(DEFAULT, 'Дискретная математика', '25', 1, 0, 5); -- 7
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
		                        VALUES(DEFAULT, 'Bayesian Statistics: From Concept to Data Analysis', '17', 1, 1, 5); -- 8
INSERT INTO courses (id, name, duration, category_id, status_id, professor_id)	
		                        VALUES(DEFAULT, 'Robotics: Mobility', '2', 1, 2, 6); -- 9

-- --------------------------------------------------------------
-- MARKS
-- marks for log
-- --------------------------------------------------------------
CREATE TABLE marks(
	`id` INT(11),
	`name` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE (`name`)
);

-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Status entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------

INSERT INTO marks (id, name) VALUES(2, 'D');
INSERT INTO marks (id, name) VALUES(3, 'C');
INSERT INTO marks (id, name) VALUES(4, 'B');
INSERT INTO marks (id, name) VALUES(5, 'A');

-- --------------------------------------------------------------
-- LOG
-- relation between users (students) and courses
-- each row of this table represents one mark for one student for one course
-- --------------------------------------------------------------
CREATE TABLE log(
	`student_id` INT(11) NOT NULL,
	`course_id` INT(11) NOT NULL,
	`mark_id` INT(11) NOT NULL,
	FOREIGN KEY (`student_id`) REFERENCES users(`id`),
	FOREIGN KEY (`course_id`) REFERENCES courses(`id`),
	FOREIGN KEY (`mark_id`) REFERENCES marks(`id`)
);

INSERT INTO log (student_id, course_id, mark_id) VALUES(7, 1, 5);
INSERT INTO log (student_id, course_id, mark_id) VALUES(8, 2, 4);
INSERT INTO log (student_id, course_id, mark_id) VALUES(8, 5, 3);
INSERT INTO log (student_id, course_id, mark_id) VALUES(9, 4, 2);
INSERT INTO log (student_id, course_id, mark_id) VALUES(9, 7, 5);
INSERT INTO log (student_id, course_id, mark_id) VALUES(9, 8, 4);
INSERT INTO log (student_id, course_id, mark_id) VALUES(10, 1, 3);
INSERT INTO log (student_id, course_id, mark_id) VALUES(10, 2, 2);
INSERT INTO log (student_id, course_id, mark_id) VALUES(10, 5, 5);
INSERT INTO log (student_id, course_id, mark_id) VALUES(10, 7, 4);
INSERT INTO log (student_id, course_id, mark_id) VALUES(11, 2, 3);
INSERT INTO log (student_id, course_id, mark_id) VALUES(11, 4, 2);
INSERT INTO log (student_id, course_id, mark_id) VALUES(11, 5, 5);
INSERT INTO log (student_id, course_id, mark_id) VALUES(11, 7, 4);
INSERT INTO log (student_id, course_id, mark_id) VALUES(11, 8, 3);
INSERT INTO log (student_id, course_id, mark_id) VALUES(12, 1, 2);
INSERT INTO log (student_id, course_id, mark_id) VALUES(12, 2, 5);
INSERT INTO log (student_id, course_id, mark_id) VALUES(12, 4, 4);
INSERT INTO log (student_id, course_id, mark_id) VALUES(12, 5, 3);
INSERT INTO log (student_id, course_id, mark_id) VALUES(12, 7, 2);
INSERT INTO log (student_id, course_id, mark_id) VALUES(12, 8, 5);

-- --------------------------------------------------------------
-- test database:
-- --------------------------------------------------------------
SELECT * FROM log;
SELECT * FROM courses;
SELECT * FROM marks;
SELECT * FROM categories;
SELECT * FROM statuses;
SELECT * FROM users;
SELECT * FROM roles;

# SHOW tables FROM elective_db;
# DROP DATABASE elective_db;