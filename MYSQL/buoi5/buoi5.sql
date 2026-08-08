-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
-- View	
CREATE VIEW v_sale_account AS
SELECT acc.* 
FROM account acc
JOIN department d ON acc.department_id = d.department_id
WHERE d.department_name = 'Sale';

SELECT * FROM v_sale_account;

-- Subquery
SELECT * 
FROM account 
WHERE department_id = (
    SELECT department_id 
    FROM department 
    WHERE department_name = 'Sale'
);

-- CTE

-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
-- View	
CREATE VIEW v_account_max_group AS
WITH GroupCount AS (
    SELECT account_id, COUNT(group_id) AS total_group
    FROM groupaccount
    GROUP BY account_id
)
SELECT a.account_id, a.full_name, gc.total_group
FROM account a
JOIN GroupCount gc ON a.account_id = gc.account_id
WHERE gc.total_group = (SELECT MAX(total_group) FROM GroupCount);

SELECT * FROM v_account_max_group;

-- Subquery
SELECT a.account_id, a.full_name, COUNT(ga.group_id) AS total_group
FROM account a
JOIN groupaccount ga ON a.account_id = ga.account_id
GROUP BY a.account_id, a.full_name
HAVING COUNT(ga.group_id) = (
    SELECT MAX(count_group)
    FROM (
        SELECT COUNT(group_id) AS count_group
        FROM groupaccount
        GROUP BY account_id
    ) AS temp
);

-- CTE

-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ được coi là quá dài) và xóa nó đi
-- View	
CREATE VIEW v_long_question AS
SELECT question_id, content
FROM question 
WHERE (LENGTH(content) - LENGTH(REPLACE(content, ' ', '')) + 1) > 300;

DELETE FROM question 
WHERE question_id IN (
    SELECT question_id 
    FROM (SELECT question_id FROM v_long_question) AS temp
);
-- Subquery
SELECT * 
FROM question 
WHERE (LENGTH(content) - LENGTH(REPLACE(content, ' ', '')) + 1) > 300;
-- CTE

-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
-- View	
CREATE VIEW v_max_account_department AS
WITH DeptCount AS (
    SELECT department_id, COUNT(account_id) AS total_account
    FROM account
    GROUP BY department_id
)
SELECT d.department_id, d.department_name, dc.total_account
FROM department d
JOIN DeptCount dc ON d.department_id = dc.department_id
WHERE dc.total_account = (SELECT MAX(total_account) FROM DeptCount);

SELECT * FROM v_max_account_department;
-- Subquery
SELECT d.department_id, d.department_name, COUNT(a.account_id) AS total_account
FROM department d
JOIN account a ON d.department_id = a.department_id
GROUP BY d.department_id, d.department_name
HAVING COUNT(a.account_id) = (
    SELECT MAX(count_acc)
    FROM (
        SELECT COUNT(account_id) AS count_acc
        FROM account
        GROUP BY department_id
    ) AS temp
);

-- CTE

-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo.
-- View	
CREATE OR REPLACE VIEW v_question_nguyen_creator AS
SELECT q.*, a.full_name AS creator_name
FROM question q
JOIN account a ON q.creator_id = a.account_id
WHERE a.full_name LIKE 'Nguyễn %';

SELECT * FROM v_question_nguyen_creator;

-- Subquery
SELECT * 
FROM question 
WHERE creator_id IN (
    SELECT account_id 
    FROM account 
    WHERE full_name LIKE 'Nguyễn %'
);

-- CTE