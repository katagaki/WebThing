CREATE USER webthinger IDENTIFIED BY 'citrusEclipse62@';
GRANT ALL ON webthing.* TO webthinger;
CREATE DATABASE IF NOT EXISTS webthing;
USE webthing;
CREATE TABLE IF NOT EXISTS Item (
	ID INT,
	Name TEXT,
	Brand TEXT,
	Description TEXT,
	Cost DOUBLE
);
INSERT INTO Item VALUES (
    9107, "iPhone 7", "Apple", "Jet Blaaaaaaaaack.", 769.00
);
INSERT INTO Item VALUES (
    9110, "iPhone XS", "Apple", "Apple's iPhone from 2018.", 999.00
);
INSERT INTO Item VALUES (
    9011, "iPhone 11 Pro", "Apple", "Apple's iPhone from 2019.", 999.00
);
INSERT INTO Item VALUES (
    9012, "iPhone 12", "Apple", "Apple's iPhone from 2020.", 829.00
);
INSERT INTO Item VALUES (
    9014, "iPhone 14 Pro Max", "Apple", "Apple's iPhone from 2022.", 1699.00
);
INSERT INTO Item VALUES (
    1023, "Galaxy S22 Ultra", "Samsung", "Samsung's flagship from 2022.", 1199.00
);
CREATE TABLE IF NOT EXISTS User (
	Username TEXT,
	PasswordHash TEXT,
	Name TEXT,
	LastSignIn DATE NULL
);
-- Password: password
INSERT INTO User VALUES (
	"admin",
	"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8",
	"System Admin",
	NULL
);
-- Password: rosebud2
INSERT INTO User VALUES (
	"justin",
	"47b32ac7f0c68da109ee165d05793d17a0aaf6e0c730551c371b0aadfde1ceba",
	"Justin",
	NULL
);
