
CONNECT 'jdbc:derby://localhost:1527/st4db;create=true;user=test;password=test';

DROP TABLE users_groups;
DROP TABLE students;
DROP TABLE groups;



CREATE TABLE students(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	name VARCHAR(777) NOT NULL
);

INSERT INTO students VALUES(DEFAULT, 'ivanov');

CREATE TABLE groups(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	name VARCHAR(777) NOT NULL
);

INSERT INTO groups VALUES(DEFAULT, 'teamA');


CREATE TABLE users_groups(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	user_name INTEGER NOT NULL REFERENCES students(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT,
	group_name INTEGER NOT NULL REFERENCES groups(id)
		ON DELETE CASCADE 
		ON UPDATE RESTRICT
);

SELECT * FROM students;
SELECT * FROM groups;
SELECT * FROM users_groups;

DISCONNECT;