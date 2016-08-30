CREATE DATABASE dbase;
USE dbase;

CREATE TABLE user_table(
	username	VARCHAR(20)	NOT NULL,
    password	VARCHAR(20)	NOT NULL,
    name		CHAR (45)	NOT NULL,
    role		CHAR (45)	NOT NULL,
    enabled 	TINYINT		NOT NULL DEFAULT 1,
    email		CHAR(45)	NOT NULL DEFAULT "None",
    insertedBy 	CHAR(45) 	NOT NULL DEFAULT "N/A",
    insertedOn	TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP,
    durationId	INT			NOT NULL DEFAULT -1,
    PRIMARY KEY (username)
);

INSERT INTO user_table (username, password, name, role, email)
VALUES ("ericcha", "pass", "Eric", "ROLE_USER", "ericcha1@umbc.edu");
INSERT INTO user_table (username, password, name, role)
VALUES ("user2", "user2", "Abe", "ROLE_USER");
INSERT INTO user_table (username, password, name, role)
VALUES ("tetris", "qwerty", "Bob", "ROLE_USER");

INSERT INTO user_table (username, password, name, role)
VALUES ("eric", "123", "Eric", "ROLE_ADMIN");
INSERT INTO user_table (username, password, name, role)
VALUES ("admin", "admin", "Admin", "ROLE_ADMIN");
INSERT INTO user_table (username, password, name, role)
VALUES ("lenovo", "123", "Cam", "ROLE_USER");
INSERT INTO user_table (username, password, name, role)
VALUES ("ravens44", "fullpass", "George", "ROLE_USER");
INSERT INTO user_table (username, password, name, role)
VALUES ("sc", "gg", "Star", "ROLE_USER");
INSERT INTO user_table (username, password, name, role)
VALUES ("nice00", "nice", "Nick", "ROLE_USER");
INSERT INTO user_table (username, password, name, role)
VALUES ("4galaxy", "phone", "Dan", "ROLE_USER");

CREATE TABLE category_table(
	category	VARCHAR(20)	NOT NULL,
    insertedBy 	CHAR(45) 	NOT NULL DEFAULT "N/A",
    insertedOn	TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (category)
);

INSERT INTO category_table (category)
VALUES ("Groceries");
INSERT INTO category_table (category)
VALUES ("Eating Out");
INSERT INTO category_table (category)
VALUES ("Income");
INSERT INTO category_table (category)
VALUES ("Sports");
INSERT INTO category_table (category)
VALUES ("Transport");
INSERT INTO category_table (category)
VALUES ("Entertainment");
INSERT INTO category_table (category)
VALUES ("Wardrobe");
INSERT INTO category_table (category)
VALUES ("Personal");
INSERT INTO category_table (category)
VALUES ("Kids");
INSERT INTO category_table (category)
VALUES ("Pets");
INSERT INTO category_table (category)
VALUES ("Household");
INSERT INTO category_table (category)
VALUES ("Phone");
INSERT INTO category_table (category)
VALUES ("Internet");
INSERT INTO category_table (category)
VALUES ("Electricity");
INSERT INTO category_table (category)
VALUES ("Electronics");
INSERT INTO category_table (category)
VALUES ("Utilities");
INSERT INTO category_table (category)
VALUES ("Mortgage");
INSERT INTO category_table (category)
VALUES ("Rent");
INSERT INTO category_table (category)
VALUES ("Loans");
INSERT INTO category_table (category)
VALUES ("Vacation");
INSERT INTO category_table (category)
VALUES ("Other");

CREATE TABLE budget_table(
	id			INT				NOT NULL AUTO_INCREMENT,
	username	VARCHAR(20)		NOT NULL,
    category	VARCHAR(20) 	NOT NULL,
    amount		DECIMAL(12,2)	NOT NULL,
    insertedBy 	CHAR(45) 		NOT NULL DEFAULT "N/A",
    insertedOn	TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (username) REFERENCES user_table(username),
    FOREIGN KEY (category) REFERENCES category_table(category)
);

CREATE TABLE duration_table(
	id			INT				NOT NULL AUTO_INCREMENT,
    username	VARCHAR(20)		NOT NULL,
    startDate	DATE			NOT NULL,
    endDate		DATE			NOT NULL,
    insertedBy 	CHAR(45) 		NOT NULL DEFAULT "N/A",
    insertedOn	TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (username) REFERENCES user_table(username)
);

INSERT INTO duration_table (username, startDate, endDate)
VALUES ("ericcha", "2016-8-24", "2016-8-30");
INSERT INTO duration_table (username, startDate, endDate)
VALUES ("ericcha", "2016-9-1", "2016-9-2");
INSERT INTO budget_table (username, category, amount)
VALUES ("ericcha", "Groceries", 20);
INSERT INTO budget_table (username, category, amount)
VALUES ("ericcha", "Electricity", 55);
INSERT INTO budget_table (username, category, amount)
VALUES ("ericcha", "Electricity", 12.5);
INSERT INTO budget_table (username, category, amount)
VALUES ("ericcha", "Electricity", 12.45);
INSERT INTO budget_table (username, category, amount, insertedOn)
VALUES ("ericcha", "Electricity", 12.00, "2016-1-9 00:00:00");
INSERT INTO budget_table (username, category, amount, insertedOn)
VALUES ("ericcha", "Rent", 12.00, "2016-1-9 00:00:01");

CREATE TABLE limit_table(
	id			INT				NOT NULL AUTO_INCREMENT,
    username	VARCHAR(20)		NOT NULL,
    category	VARCHAR(20)		NOT NULL,
    amount		DECIMAL(12,2)	NOT NULL,
    insertedBy 	CHAR(45) 		NOT NULL DEFAULT "N/A",
    insertedOn	TIMESTAMP		NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
	FOREIGN KEY (username) REFERENCES user_table(username),
    FOREIGN KEY (category) REFERENCES category_table(category)
);

SELECT id FROM duration_table WHERE username="ericcha" AND startDate="2016-07-27" AND endDate="2016-08-27";