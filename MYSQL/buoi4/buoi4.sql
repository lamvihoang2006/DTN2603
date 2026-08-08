USE TestingSystem;

-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ
SELECT acc.*, dep.department_name
FROM account acc
LEFT JOIN department dep ON acc.department_id = dep.department_id;

-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010
SELECT *
FROM account
WHERE create_date > '2010-12-20';

-- Question 3: Viết lệnh để lấy ra tất cả các developer
SELECT acc.*
FROM account acc
JOIN position pos ON acc.position_id = pos.position_id
WHERE pos.position_name = 'DEV';

-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên
SELECT dep.department_id, dep.department_name, COUNT(acc.account_id) AS tongSL
FROM department dep
LEFT JOIN account acc ON dep.department_id = acc.department_id
GROUP BY dep.department_id
HAVING COUNT(acc.account_id) > 3;

-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất
SELECT q.question_id, q.content, COUNT(eq.exam_id) AS tong_exam
FROM question q
JOIN examquestion eq ON q.question_id = eq.question_id
GROUP BY q.question_id, q.content
HAVING COUNT(eq.exam_id) = (
    SELECT MAX(count_exam)
    FROM (
        SELECT COUNT(exam_id) AS count_exam
        FROM examquestion
        GROUP BY question_id
    ) AS temp
);

-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question
SELECT cq.category_id, cq.category_name, COUNT(q.question_id) AS tong_question
FROM categoryquestion cq
LEFT JOIN question q ON cq.category_id = q.category_id
GROUP BY cq.category_id;

-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam
SELECT q.question_id, q.content, COUNT(eq.exam_id) AS tong_exam
FROM question q
LEFT JOIN examquestion eq ON q.question_id = eq.question_id
GROUP BY q.question_id, q.content;

-- Question 8: Lấy ra Question có nhiều câu trả lời nhất
SELECT q.question_id, q.content, COUNT(ans.answer_id) AS tong_answer
FROM question q
JOIN answer ans ON q.question_id = ans.question_id
GROUP BY q.question_id, q.content
HAVING COUNT(ans.answer_id) = (
    SELECT MAX(count_ans)
    FROM (
        SELECT COUNT(answer_id) AS count_ans
        FROM answer
        GROUP BY question_id
    ) AS temp
);

-- Question 9: Thống kê số lượng account trong mỗi group
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS tong_account
FROM `group` g
LEFT JOIN groupaccount ga ON g.group_id = ga.group_id
GROUP BY g.group_id;

-- Question 10: Tìm chức vụ có ít người nhất
SELECT pos.position_id, pos.position_name, COUNT(acc.account_id) AS tong_account
FROM `position` pos
LEFT JOIN account acc ON pos.position_id = acc.position_id
GROUP BY pos.position_id
HAVING COUNT(acc.account_id) = (
    SELECT MIN(count_acc)
    FROM (
        SELECT COUNT(account_id) AS count_acc
        FROM account
        GROUP BY position_id
    ) AS temp
);

-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM
SELECT dep.department_id, dep.department_name,
	SUM(CASE WHEN pos.position_name = 'DEV' THEN 1 ELSE 0 END) AS so_dev,
    SUM(CASE WHEN pos.position_name = 'TEST' THEN 1 ELSE 0 END) AS so_test,
    SUM(CASE WHEN pos.position_name = 'SCRUM_MASTER' THEN 1 ELSE 0 END) AS so_scrum_master,
    SUM(CASE WHEN pos.position_name = 'PM' THEN 1 ELSE 0 END) AS so_pm
FROM department dep
LEFT JOIN account acc ON dep.department_id = acc.department_id
LEFT JOIN `position` pos ON acc.position_id = pos.position_id
GROUP BY dep.department_id;

-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, 
SELECT 
    q.question_id, 
    q.content AS question_content, 
    cq.category_name,
    tq.type_name, 
    acc.full_name AS creator_name, 
    ans.content AS answer_content, 
    ans.is_correct
FROM question q
JOIN categoryquestion cq ON q.category_id = cq.category_id
JOIN typequestion tq ON q.type_id = tq.type_id
JOIN account acc ON q.creator_id = acc.account_id
LEFT JOIN answer ans ON q.question_id = ans.question_id;

-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm
SELECT tq.type_id, tq.type_name, COUNT(q.question_id) AS tong_question
FROM typequestion tq
LEFT JOIN question q ON tq.type_id = q.type_id
GROUP BY tq.type_id;

-- Question 14:Lấy ra group không có account nào
SELECT g.*
FROM `group` g
LEFT JOIN groupaccount ga ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;

-- Question 15: Lấy ra group không có account nào
SELECT g.*
FROM `group` g
LEFT JOIN groupaccount ga ON g.group_id = ga.group_id
WHERE ga.account_id IS NULL;

-- Question 16: Lấy ra question không có answer nào.
SELECT q.*
FROM question q
LEFT JOIN answer ans ON q.question_id = ans.question_id
WHERE ans.answer_id IS NULL;

-- 2. Union.
--        Question 17:
-- a) Lấy các account thuộc nhóm thứ 1
SELECT acc.*
FROM account acc
JOIN groupaccount ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 1;

-- b) Lấy các account thuộc nhóm thứ 2
SELECT acc.*
FROM account acc
JOIN groupaccount ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 2;

-- c) Ghép 2 kết quả từ câu a) và câu b) sao cho không có record nào trùng nhau
SELECT acc.*
FROM account acc
JOIN groupaccount ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 1
UNION
SELECT acc.*
FROM account acc
JOIN groupaccount ga ON acc.account_id = ga.account_id
WHERE ga.group_id = 2;

-- Question 18:
-- a) Lấy các group có lớn hơn 5 thành viên
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS so_thanh_vien
FROM `group` g
JOIN groupaccount ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) > 5;

-- b) Lấy các group có nhỏ hơn 7 thành viên
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS so_thanh_vien
FROM `group` g
JOIN groupaccount ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) < 7;

-- c) Ghép 2 kết quả từ câu a) và câu b).
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS so_thanh_vien
FROM `group` g
JOIN groupaccount ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) > 5
UNION ALL
SELECT g.group_id, g.group_name, COUNT(ga.account_id) AS so_thanh_vien
FROM `group` g
JOIN groupaccount ga ON g.group_id = ga.group_id
GROUP BY g.group_id, g.group_name
HAVING COUNT(ga.account_id) < 7;