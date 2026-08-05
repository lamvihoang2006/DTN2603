CREATE DATABASE TestingSystem;
USE TestingSystem;

CREATE TABLE Department (
    Department_ID INT AUTO_INCREMENT PRIMARY KEY,
    Department_Name VARCHAR(100) 
);

CREATE TABLE `Position` (
    Position_ID INT AUTO_INCREMENT PRIMARY KEY,
    Position_Name ENUM('DEV','TEST','SCRUM_MASTER','PM')
);

CREATE TABLE Account (
    Account_ID INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(100) UNIQUE,
    Username VARCHAR(50) UNIQUE,
    Full_Name VARCHAR(100),
    Department_ID INT,
    Position_ID INT,
    Create_Date DATETIME,
    
	CONSTRAINT FK_Account_Department FOREIGN KEY (Department_ID) REFERENCES Department(Department_ID),
	CONSTRAINT FK_Account_Position FOREIGN KEY (Position_ID) REFERENCES `Position`(Position_ID)
);

CREATE TABLE `Group` (
    Group_ID INT AUTO_INCREMENT PRIMARY KEY,
    Group_Name VARCHAR(100) UNIQUE,
    Creator_ID INT,
    Create_Date DATETIME,
    
	CONSTRAINT FK_Group_Creator_ID FOREIGN KEY (Creator_ID) REFERENCES Account(Account_ID)
);

CREATE TABLE `GroupAccount` (
    Group_ID INT,
    Account_ID INT,
    Join_Date DATETIME,
    PRIMARY KEY (Group_ID, Account_ID),
    
	CONSTRAINT FK_GroupAccount_Group_ID FOREIGN KEY (Group_ID) REFERENCES `Group`(Group_ID),
	CONSTRAINT FK_GroupAccount_Account_ID FOREIGN KEY (Account_ID) REFERENCES Account(Account_ID)
);

CREATE TABLE TypeQuestion (
    TypeID INT AUTO_INCREMENT PRIMARY KEY,
    TypeName ENUM('Essay', 'Multiple-Choice') NOT NULL UNIQUE
);

CREATE TABLE CategoryQuestion (
    Category_ID INT AUTO_INCREMENT PRIMARY KEY,
    Category_Name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Question (
    Question_ID INT AUTO_INCREMENT,
    Content TEXT,
    Category_ID INT,
    TypeID INT,
    Creator_ID INT,
    Create_Date DATETIME,
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

CREATE TABLE Exam (
    Exam_ID INT AUTO_INCREMENT,
    Code VARCHAR(20) NOT NULL UNIQUE,
    Title VARCHAR(100),
    Category_ID INT,
    Duration INT NOT NULL,
    Creator_ID INT NOT NULL,
    Create_Date DATETIME,
	PRIMARY KEY (Exam_ID, Category_ID),
    
	CONSTRAINT FK_Exam_Category_ID FOREIGN KEY (Category_ID) REFERENCES CategoryQuestion(Category_ID),
	CONSTRAINT FK_Exam_Creator_ID FOREIGN KEY (Creator_ID) REFERENCES Account(Account_ID)
);

CREATE TABLE ExamQuestion (
    ExamID INT NOT NULL,
    QuestionID INT NOT NULL,
    PRIMARY KEY (ExamID, QuestionID),
    
	CONSTRAINT FK_ExamQuestion_ExamID FOREIGN KEY (ExamID) REFERENCES Exam(ExamID),
	CONSTRAINT FK_ExamQuestion_QuestionID FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID)
);

