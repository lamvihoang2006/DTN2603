CREATE DATABASE TestingSystem;
USE TestingSystem;

CREATE TABLE Department (
    Department_ID INT AUTO_INCREMENT PRIMARY KEY,
    Department_Name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE `Position` (
    Position_ID INT AUTO_INCREMENT PRIMARY KEY,
    Position_Name ENUM('DEV','TEST','SCRUM_MASTER','PM')
);

CREATE TABLE Account (
	Account_ID INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(100) NOT NULL UNIQUE,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Full_Name VARCHAR(100) NOT NULL,
    Department_ID INT NOT NULL,
    Position_ID INT NOT NULL,
    Create_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    
	CONSTRAINT FK_Account_Department FOREIGN KEY (Department_ID) REFERENCES Department(Department_ID),
	CONSTRAINT FK_Account_Position FOREIGN KEY (Position_ID) REFERENCES `Position`(Position_ID)
);

CREATE TABLE `Group` (
	Group_ID INT AUTO_INCREMENT PRIMARY KEY,
    Group_Name VARCHAR(100) NOT NULL UNIQUE,
    Creator_ID INT NOT NULL,
    Create_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    
	CONSTRAINT FK_Group_Creator_ID FOREIGN KEY (Creator_ID) REFERENCES Account(Account_ID)
);

CREATE TABLE `GroupAccount` (
	Group_ID INT NOT NULL,
    Account_ID INT NOT NULL,
    Join_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (Group_ID, Account_ID),
    
	CONSTRAINT FK_GroupAccount_Group_ID FOREIGN KEY (Group_ID) REFERENCES `Group`(Group_ID),
	CONSTRAINT FK_GroupAccount_Account_ID FOREIGN KEY (Account_ID) REFERENCES Account(Account_ID)
);

CREATE TABLE TypeQuestion (
    Type_ID INT AUTO_INCREMENT PRIMARY KEY,
    Type_Name ENUM('Essay', 'Multiple-Choice') NOT NULL
);

CREATE TABLE CategoryQuestion (
    Category_ID INT AUTO_INCREMENT PRIMARY KEY,
    Category_Name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Question (
	Question_ID INT AUTO_INCREMENT,
    Content TEXT NOT NULL,
    Category_ID INT NOT NULL,
    Type_ID INT NOT NULL,
    Creator_ID INT NOT NULL,
    Create_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (Question_ID, Category_ID, Type_ID),
    
	CONSTRAINT FK_Question_Category_ID FOREIGN KEY (Category_ID) REFERENCES CategoryQuestion(Category_ID),
	CONSTRAINT FK_Question_Type_ID FOREIGN KEY (Type_ID) REFERENCES TypeQuestion(Type_ID),
	CONSTRAINT FK_Question_Creator_ID FOREIGN KEY (Creator_ID) REFERENCES Account(Account_ID)
);

CREATE TABLE Answer (
    Answer_ID INT AUTO_INCREMENT,
	Content TEXT NOT NULL,
    Question_ID INT NOT NULL,
    is_Correct BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (Answer_ID, Question_ID),
    
	CONSTRAINT FK_Answer_Question_ID FOREIGN KEY (Question_ID) REFERENCES Question(Question_ID)
);

