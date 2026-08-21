USE TestingSystem;

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó.
DELIMITER $$
CREATE PROCEDURE sp_get_account_by_dept(IN in_dept_name VARCHAR(100))
BEGIN
    SELECT acc.* 
    FROM account acc
    JOIN department dep ON acc.department_id = dep.department_id
    WHERE dep.department_name = in_dept_name;
END $$ 
DELIMITER ;

-- Question 2: Tạo store để in ra số lượng account trong mỗi group.
DELIMITER $$
CREATE PROCEDURE sp_get_account_count_per_group()
BEGIN
    SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS total_account
    FROM `group` g
    LEFT JOIN groupaccount ga ON g.group_id = ga.group_id
    GROUP BY g.group_id, g.group_name;
END $$
DELIMITER ;

-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại.
DELIMITER $$
CREATE PROCEDURE sp_count_question_current_month()
BEGIN
	SELECT tq.type_id, tq.type_name, COUNT(q.question_id) AS total_question
    FROM typequestion tq
    LEFT JOIN question q ON tq.type_id = q.type_id
		AND MONTH(q.create_date) = MONTH(CURRENT_DATE) 
        AND YEAR(q.create_date) = YEAR(CURRENT_DATE)
    GROUP BY tq.type_id;
END $$
DELIMITER ;

-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất.
DELIMITER $$
CREATE PROCEDURE sp_get_max_type_question_id(OUT out_type_id VARCHAR(255))
BEGIN
	SELECT GROUP_CONCAT(type_id) INTO out_type_id
	FROM (
        SELECT type_id 
        FROM question
        GROUP BY type_id
        HAVING COUNT(question_id) = (
            SELECT MAX(q_count) 
            FROM (
                SELECT COUNT(question_id) AS q_count 
                FROM question 
                GROUP BY type_id
            ) AS temp
        )
    ) AS list_max_types;
END $$ 
DELIMITER ;

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question.
CALL sp_get_max_type_question_id(@max_id);
SELECT * 
FROM TypeQuestion 
WHERE FIND_IN_SET(type_id, @max_id);

-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào.
DELIMITER $$
CREATE PROCEDURE sp_search_group_or_user(IN in_search_string VARCHAR(50))
BEGIN
    SELECT 'Group' AS Type, group_name AS Name 
    FROM `group` 
    WHERE group_name LIKE CONCAT('%', in_search_string, '%');
    
    SELECT 'User' AS Type, username AS Name 
    FROM account 
    WHERE username LIKE CONCAT('%', in_search_string, '%');
END $$
DELIMITER ;

-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán: username sẽ giống email nhưng bỏ phần @..mail đi positionID: sẽ có default là developer departmentID: sẽ được cho vào 1 phòng chờ Sau đó in ra kết quả tạo thành công
DELIMITER $$
CREATE PROCEDURE sp_auto_create_account(IN in_full_name VARCHAR(100), IN in_email VARCHAR(100))
BEGIN
	DECLARE v_username VARCHAR(50);
    DECLARE v_position_id INT;
    DECLARE v_department_id INT;
    
	SET v_username = SUBSTRING_INDEX(in_email, '@', 1);
	SELECT position_id INTO v_position_id FROM `position` WHERE position_name = 'DEV' LIMIT 1;
    SELECT department_id INTO v_department_id FROM department WHERE department_name = 'Phòng chờ' LIMIT 1;
    
    INSERT INTO account(email, username, full_name, department_id, position_id)
    VALUES (in_email, v_username, in_full_name, v_department_id, v_position_id);
    
	SELECT 'Tạo tài khoản thành công!' AS Message;
END $$
DELIMITER ;

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất
DELIMITER $$
CREATE PROCEDURE sp_get_longest_question(IN in_type_name ENUM('Essay', 'Multiple-Choice'))
BEGIN
    SELECT q.*, LENGTH(q.content) AS content_length
    FROM question q
    JOIN typequestion tq ON q.type_id = tq.type_id
    WHERE tq.type_name = in_type_name
	AND LENGTH(q.content) = (
          SELECT MAX(LENGTH(q2.content))
          FROM question q2
          JOIN typequestion tq2 ON q2.type_id = tq2.type_id
          WHERE tq2.type_name = in_type_name
      );
END $$
DELIMITER ;

-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID
DELIMITER $$
CREATE PROCEDURE sp_delete_exam_by_id(IN in_exam_id INT)
BEGIN
    DELETE FROM examquestion WHERE examid = in_exam_id;
    DELETE FROM exam WHERE exam_id = in_exam_id;
END $$
DELIMITER ;

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử dụng store ở câu 9 để xóa) Sau đó in số lượng record đã remove từ các table liên quan trong khi removing
DELIMITER $$
CREATE PROCEDURE sp_delete_exams_3_years_ago_while()
BEGIN
    DECLARE v_exam_id INT;
    DECLARE v_count_exam INT DEFAULT 0;
    DECLARE v_count_examquestion INT DEFAULT 0;
    DECLARE v_temp_count INT DEFAULT 0;

    WHILE (SELECT COUNT(*) FROM Exam WHERE Create_Date <= DATE_SUB(NOW(), INTERVAL 3 YEAR)) > 0 DO
        
        SELECT Exam_ID INTO v_exam_id 
        FROM Exam 
        WHERE Create_Date <= DATE_SUB(NOW(), INTERVAL 3 YEAR) 
        LIMIT 1;

        SELECT COUNT(*) INTO v_temp_count 
        FROM ExamQuestion 
        WHERE Exam_ID = v_exam_id;

        SET v_count_examquestion = v_count_examquestion + v_temp_count;
        SET v_count_exam = v_count_exam + 1;

        CALL sp_delete_exam_by_id(v_exam_id);
        
    END WHILE;

    SELECT 
        v_count_exam AS 'Số lượng Exam đã xóa',
        v_count_examquestion AS 'Số lượng ExamQuestion đã xóa';
        
END $$
DELIMITER ;

-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được chuyển về phòng ban default là phòng ban chờ việc
DELIMITER $$
CREATE PROCEDURE sp_delete_department(IN in_dept_name VARCHAR(100))
BEGIN
    DECLARE v_target_dept_id INT;
    DECLARE v_waiting_dept_id INT;
    
	SELECT department_id INTO v_target_dept_id FROM department WHERE department_name = in_dept_name;
	SELECT department_id INTO v_waiting_dept_id FROM department WHERE department_name = 'Phòng chờ việc' LIMIT 1;
    
	UPDATE account SET department_id = v_waiting_dept_id WHERE department_id = v_target_dept_id;
	DELETE FROM department WHERE department_id = v_target_dept_id;
    
    SELECT CONCAT('Đã xóa phòng ', in_dept_name, ' và chuyển nhân viên sang Phòng chờ.') AS Message;
END $$
DELIMITER ;

-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
DELIMITER $$
CREATE PROCEDURE sp_count_questions_per_month()
BEGIN
	SELECT 
        MONTH(Create_Date) AS `Tháng`,
        COUNT(Question_ID) AS `Số lượng câu hỏi`
    FROM Question
    WHERE YEAR(Create_Date) = YEAR(CURRENT_TIMESTAMP)
    GROUP BY MONTH(Create_Date)
    ORDER BY MONTH(Create_Date) ASC;
END $$
DELIMITER ;

-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng")
DELIMITER $$
CREATE PROCEDURE sp_count_questions_last_6_months()
BEGIN
    SELECT 
        CONCAT('Tháng ', t.Month_Value, '-', t.Year_Value) AS `Thời gian`,
		CASE 
            WHEN COUNT(q.Question_ID) = 0 THEN 'không có câu hỏi nào trong tháng'
            ELSE CAST(COUNT(q.Question_ID) AS CHAR)
        END AS `Số lượng câu hỏi`
        
    FROM (
        SELECT MONTH(CURRENT_TIMESTAMP) AS Month_Value, YEAR(CURRENT_TIMESTAMP) AS Year_Value
        UNION SELECT MONTH(CURRENT_TIMESTAMP - INTERVAL 1 MONTH), YEAR(CURRENT_TIMESTAMP - INTERVAL 1 MONTH)
        UNION SELECT MONTH(CURRENT_TIMESTAMP - INTERVAL 2 MONTH), YEAR(CURRENT_TIMESTAMP - INTERVAL 2 MONTH)
        UNION SELECT MONTH(CURRENT_TIMESTAMP - INTERVAL 3 MONTH), YEAR(CURRENT_TIMESTAMP - INTERVAL 3 MONTH)
        UNION SELECT MONTH(CURRENT_TIMESTAMP - INTERVAL 4 MONTH), YEAR(CURRENT_TIMESTAMP - INTERVAL 4 MONTH)
        UNION SELECT MONTH(CURRENT_TIMESTAMP - INTERVAL 5 MONTH), YEAR(CURRENT_TIMESTAMP - INTERVAL 5 MONTH)
    ) AS t
    
    LEFT JOIN Question q 
        ON t.Month_Value = MONTH(q.Create_Date) 
        AND t.Year_Value = YEAR(q.Create_Date)
        
    GROUP BY t.Year_Value, t.Month_Value
    ORDER BY t.Year_Value ASC, t.Month_Value ASC;
    
END $$
DELIMITER ;