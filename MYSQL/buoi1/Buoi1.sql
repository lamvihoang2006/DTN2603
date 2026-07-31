CREATE DATABASE TestingSystem;
USE TestingSystem;

CREATE TABLE Department (
    DepartmentID INT AUTO_INCREMENT PRIMARY KEY,
    DepartmentName VARCHAR(100) 
);

CREATE TABLE `Position` (
    PositionID INT AUTO_INCREMENT PRIMARY KEY,
    PositionName ENUM('DEV','TEST','SCRUM_MASTER','PM')
);

CREATE TABLE Account (
    AccountID INT AUTO_INCREMENT PRIMARY KEY,
    Email VARCHAR(100) UNIQUE,
    Username VARCHAR(50) UNIQUE,
    FullName VARCHAR(100),
    DepartmentID INT,
    PositionID INT,
    CreateDate DATETIME,
    
	CONSTRAINT FK_Account_Department FOREIGN KEY (DepartmentID) REFERENCES Department(DepartmentID),
	CONSTRAINT FK_Account_Position FOREIGN KEY (PositionID) REFERENCES `Position`(PositionID)
);

CREATE TABLE `Group` (
    GroupID INT AUTO_INCREMENT PRIMARY KEY,
    GroupName VARCHAR(100) UNIQUE,
    CreatorID INT,
    CreateDate DATETIME,
    
	CONSTRAINT FK_Group_CreatorID FOREIGN KEY (CreatorID) REFERENCES Account(AccountID)
);

CREATE TABLE `GroupAccount` (
    GroupID INT,
    AccountID INT,
    JoinDate DATETIME,
    PRIMARY KEY (GroupID, AccountID),
    
	CONSTRAINT FK_GroupAccount_GroupID FOREIGN KEY (GroupID) REFERENCES `Group`(GroupID),
	CONSTRAINT FK_GroupAccount_AccountID FOREIGN KEY (AccountID) REFERENCES Account(AccountID)
);

CREATE TABLE TypeQuestion (
    TypeID INT AUTO_INCREMENT PRIMARY KEY,
    TypeName ENUM('Essay', 'Multiple-Choice') NOT NULL UNIQUE
);

CREATE TABLE CategoryQuestion (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Question (
    QuestionID INT AUTO_INCREMENT,
    Content TEXT,
    CategoryID INT,
    TypeID INT,
    CreatorID INT,
    CreateDate DATETIME,
	PRIMARY KEY (QuestionID, CategoryID, TypeID),
    
	CONSTRAINT FK_Question_CategoryID FOREIGN KEY (CategoryID) REFERENCES CategoryQuestion(CategoryID),
	CONSTRAINT FK_Question_TypeID FOREIGN KEY (TypeID) REFERENCES TypeQuestion(TypeID),
	CONSTRAINT FK_Question_CreatorID FOREIGN KEY (CreatorID) REFERENCES Account(AccountID)
);

CREATE TABLE Answer (
    AnswerID INT AUTO_INCREMENT,
    Content TEXT,
    QuestionID INT,
    isCorrect BOOLEAN,
	PRIMARY KEY (AnswerID, QuestionID),
    
	CONSTRAINT FK_Answer_QuestionID FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID)
);

CREATE TABLE Exam (
    ExamID INT AUTO_INCREMENT,
    Code VARCHAR(20) NOT NULL UNIQUE,
    Title VARCHAR(100),
    CategoryID INT,
    Duration INT NOT NULL,
    CreatorID INT NOT NULL,
    CreateDate DATETIME,
	PRIMARY KEY (ExamID, CategoryID),
    
	CONSTRAINT FK_Exam_CategoryID FOREIGN KEY (CategoryID) REFERENCES CategoryQuestion(CategoryID),
	CONSTRAINT FK_Exam_CreatorID FOREIGN KEY (CreatorID) REFERENCES Account(AccountID)
);

CREATE TABLE ExamQuestion (
    ExamID INT NOT NULL,
    QuestionID INT NOT NULL,
    PRIMARY KEY (ExamID, QuestionID),
    
	CONSTRAINT FK_ExamQuestion_ExamID FOREIGN KEY (ExamID) REFERENCES Exam(ExamID),
	CONSTRAINT FK_ExamQuestion_QuestionID FOREIGN KEY (QuestionID) REFERENCES Question(QuestionID)
);

