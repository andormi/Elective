USE elective_db;

SELECT * FROM users;
SELECT * FROM courses;

SELECT u.first_name, u.last_name, c.name, m.name
	FROM users u, log l, courses c, marks m
	WHERE l.student_id=u.id AND l.course_id=c.id AND l.mark_id=m.id ORDER BY u.id;
