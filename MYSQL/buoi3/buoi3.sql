USE TestingSystem;

-- Question 1: Thêm ít nhất 10 record vào mỗi table
INSERT INTO Department(Department_Name) VALUES
('Sale'), ('Marketing'), ('HR'), ('IT'), ('Finance'),
('Engineering'), ('Customer Service'), ('Admin'), ('Operations'), ('R&D');

INSERT INTO `Position`(Position_Name) VALUES
('DEV'), ('TEST'), ('SCRUM_MASTER'), ('PM'), ('DEV'), ('TEST'), ('SCRUM_MASTER'), ('PM'), ('DEV'), ('TEST'), ('SCRUM_MASTER'), ('PM');

INSERT INTO Account(Email, Username, Full_Name, Department_ID, Position_ID, Create_Date) VALUES
('email1@gmail.com', 'user1', 'Lam Vi Hoang', 1, 1, '2020-01-01'),
('email2@gmail.com', 'user2', 'Dinh Thu Thao', 2, 2, '2019-12-01'),
('email3@gmail.com', 'user3', 'Nguyen Xuan Do', 3, 3, '2019-11-20'),
('email4@gmail.com', 'user4', 'Tran Thi Thuy Trang', 3, 4, '2021-03-03'), 
('email5@gmail.com', 'user5', 'Lam Kien Luong', 4, 1, '2020-02-15'),
('email6@gmail.com', 'user6', 'Tran Giai Dao', 5, 2, '2019-05-05'),
('email7@gmail.com', 'user7', 'Tran Quynh Nhi', 2, 3, '2019-12-01'),
('email8@gmail.com', 'user8', 'Nguyen Xuan Bach', 1, 4, '2018-12-12'),
('email9@gmail.com', 'user9', 'Bui Quang Huy', 3, 1, '2019-12-01'),
('email10@gmail.com', 'user10', 'Bui Luong Thanh', 2, 2, '2019-12-25');

INSERT INTO `Group`(Group_Name, Creator_ID, Create_Date) VALUES
('Group 1', 1, '2019-01-01'), ('Group 2', 2, '2019-10-15'),
('Group 3', 3, '2020-01-01'), ('Group 4', 4, '2021-05-05'),
('Group 5', 5, '2022-02-02'), ('Group 6', 6, '2023-01-01'),
('Group 7', 7, '2023-08-08'), ('Group 8', 8, '2023-10-10'),
('Group 9', 9, '2018-11-11'), ('Group 10', 10, '2019-12-19');

INSERT INTO GroupAccount(Group_ID, Account_ID, Join_Date) VALUES
(1, 1, '2019-01-05'), (1, 2, '2019-02-05'),
(2, 3, '2019-11-15'), (3, 4, '2020-01-10'),
(4, 5, '2021-06-06'), (5, 6, '2022-03-03'),
(6, 7, '2023-02-02'), (7, 8, '2023-09-09'),
(8, 9, '2023-11-11'), (9, 10, '2018-12-12');

INSERT INTO TypeQuestion(Type_Name) VALUES
('Essay'), ('Multiple-Choice'), ('Essay'), ('Multiple-Choice'), ('Essay'), ('Multiple-Choice'), ('Essay'), ('Multiple-Choice'), ('Essay'), ('Multiple-Choice');

INSERT INTO CategoryQuestion(Category_Name) VALUES
('Java'), ('SQL'), ('C++'), ('Ruby'), ('Python'),
('NodeJS'), ('HTML'), ('CSS'), ('ReactJS'), ('C#');

INSERT INTO Question(Content, Category_ID, Type_ID, Creator_ID, Create_Date) VALUES
('Câu hỏi 1', 1, 1, 1, '2019-05-01'), ('Câu hỏi 2', 2, 2, 2, '2020-05-01'),
('Câu hỏi 3', 3, 1, 3, '2021-05-01'), ('Câu hỏi 4', 4, 2, 4, '2022-05-01'),
('Câu hỏi 5', 5, 1, 5, '2023-05-01'), ('Câu hỏi 6', 6, 2, 6, '2019-12-01'),
('Câu hỏi 7', 7, 1, 7, '2020-12-01'), ('Câu hỏi 8', 8, 2, 8, '2021-12-01'),
('Câu hỏi 9', 9, 1, 9, '2022-12-01'), ('Câu hỏi 10', 10, 2, 10, '2023-12-01');

INSERT INTO Answer(Content, Question_ID, is_Correct) VALUES
('Trả lời 1.1', 1, TRUE), ('Trả lời 1.2', 1, FALSE),
('Trả lời 1.3', 1, FALSE), ('Trả lời 1.4', 1, FALSE),
('Trả lời 2.1', 2, TRUE), ('Trả lời 3.1', 3, TRUE),
('Trả lời 4.1', 4, FALSE), ('Trả lời 5.1', 5, TRUE),
('Trả lời 6.1', 6, FALSE), ('Trả lời 7.1', 7, TRUE);

INSERT INTO Exam(Code, Title, Category_ID, Duration, Creator_ID, Create_Date) VALUES
('EX01', 'Đề thi 1', 1, 60, 1, '2019-10-10'), 
('EX02', 'Đề thi 2', 2, 45, 2, '2019-12-25'),
('EX03', 'Đề thi 3', 3, 90, 3, '2018-12-12'), 
('EX04', 'Đề thi 4', 4, 120, 4, '2020-01-01'),
('EX05', 'Đề thi 5', 5, 60, 5, '2021-01-01'),
('EX06', 'Đề thi 6', 6, 30, 6, '2019-11-11'),
('EX07', 'Đề thi 7', 7, 45, 7, '2022-02-02'),
('EX08', 'Đề thi 8', 8, 60, 8, '2023-03-03'),
('EX09', 'Đề thi 9', 9, 90, 9, '2019-12-19'), 
('EX10', 'Đề thi 10', 10, 45, 10, '2020-05-05');

INSERT INTO ExamQuestion(Exam_ID, Question_ID) VALUES
(1, 1), (1, 2), (2, 3), (3, 4), (4, 5),
(5, 6), (6, 7), (7, 8), (8, 9), (9, 10);

-- Question 2: lấy ra tất cả các phòng ban
SELECT * FROM Department;

-- Question 3: lấy ra id của phòng ban "Sale"
SELECT Department_ID 
FROM Department 
WHERE Department_Name = 'Sale';

-- Question 4: lấy ra thông tin account có full name dài nhất
SELECT * 
FROM Account 
WHERE CHAR_LENGTH(Full_Name) = (SELECT MAX(CHAR_LENGTH(Full_Name)) FROM Account);

-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id= 3
SELECT * 
FROM Account 
WHERE Department_ID = 3
AND CHAR_LENGTH(Full_Name) = (SELECT MAX(CHAR_LENGTH(Full_Name)) FROM Account WHERE Department_ID = 3);

-- Question 6: Lấy ra tên group đã tham gia trước ngày 20/12/2019
SELECT DISTINCT g.Group_Name 
FROM `Group` g
JOIN GroupAccount ga ON g.Group_ID = ga.Group_ID
WHERE ga.Join_Date < '2019-12-20 00:00:00';

-- Question 7: Lấy ra ID của question có >= 4 câu trả lời
SELECT Question_ID 
FROM Answer 
GROUP BY Question_ID 
HAVING COUNT(Answer_ID) >= 4;

-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày 20/12/2019
SELECT Code 
FROM Exam 
WHERE Duration >= 60 AND Create_Date < '2019-12-20 00:00:00';

-- Question 9: Lấy ra 5 group được tạo gần đây nhất
SELECT * 
FROM `Group` 
ORDER BY Create_Date DESC 
LIMIT 5;

-- Question 10: Đếm số nhân viên thuộc department có id = 2
SELECT * 
FROM `Group` 
ORDER BY Create_Date DESC 
LIMIT 5;

-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o"
SELECT * 
FROM Account 
WHERE Full_Name LIKE 'D%o';