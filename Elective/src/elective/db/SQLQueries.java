package elective.db;

public final class SQLQueries {

	// Queries for operations with table 'users'
	
	public static final String SQL_GET_ALL_USERS =
			"SELECT * FROM users";
	public static final String SQL_GET_USER_BY_ID =
			"SELECT id, login, password, first_name, last_name, role_id FROM users WHERE id=?";
	public static final String SQL_DELETE_USER =
			"DELETE FROM users WHERE id=?";
	public static final String SQL_INSERT_USER =
			"INSERT INTO users VALUES(DEFAULT, ?, ?, ?, ?, ?)";
	public static final String SQL_UPDATE_USER =
			"UPDATE users SET login=?, password=?, first_name=?, last_name=?, role_id=? WHERE id=?";
	
	public static final String SQL_GET_USER_BY_LOGIN =
			"SELECT id, login, password, first_name, last_name, role_id FROM users WHERE login=?";
	public static final String SQL_GET_ALL_USERS_WITH_ROLES =
			"SELECT u.login, u.first_name, r.name FROM users u, roles r WHERE u.role_id=r.id ORDER BY u.id";
	
	// Queries for operations with table 'categories'
	
	public static final String SQL_GET_ALL_CATEGORIES =
			"SELECT * FROM categories";
	public static final String SQL_GET_CATEGORY_BY_ID =
			"SELECT id, name FROM category WHERE id=?";
	public static final String SQL_DELETE_CATEGORY =
			"DELETE FROM category WHERE id=?";
	public static final String SQL_INSERT_CATEGORY =
			"INSERT INTO CATEGORY VALUES(?, ?)";
	public static final String SQL_UPDATE_CATEGORY =
			"UPDATE categories SET name=? WHERE id=?";

	// Queries for operations with table 'courses'

	public static final String SQL_GET_ALL_COURSES =
			"SELECT * FROM courses";
	public static final String SQL_GET_COURSE_BY_ID =
			"SELECT id, name, duration, category_id, status_id, professor_id FROM courses WHERE id=?";
	public static final String SQL_GET_COURSE_BY_STUDENT_ID =
			"SELECT c.id, c.name AS course_name, m.name AS mark_name, s.name AS status_name, u.last_name\r\n" +
			" FROM log l, statuses s, marks m, courses c\r\n" +
			" INNER JOIN users u ON c.professor_id=u.id\r\n" +
			" WHERE l.course_id=c.id AND l.mark_id=m.id AND c.status_id=s.id\r\n" +
			" AND s.id=? AND l.student_id=?";
	public static final String SQL_DELETE_COURSE =
			"DELETE FROM courses WHERE id=?";
	public static final String SQL_INSERT_COURSE =
			"INSERT INTO courses VALUES(DEFAULT, ?, ?, ?, ?, ?)";
	public static final String SQL_UPDATE_COURSE =
			"UPDATE courses SET name=?, duration=?, category_id=?, status_id=?, professor_id=? WHERE id=?";
	
	// Queries for operations with table 'log'
	
	public static final String SQL_GET_ALL_STUDENTS_FROM_LOG =
			"SELECT u.first_name, u.last_name, c.name, s.name, m.name\r\n" + 
			" FROM users u, log l, courses c, statuses s, marks m\r\n" + 
			" WHERE l.student_id=u.id AND l.course_id=c.id AND c.status_id=s.id\r\n" + 
			" AND l.mark_id=m.id ORDER BY u.id, c.name";
	public static final String SQL_GET_COURSES_BY_STUDENTS_ID_FROM_LOG =
			"SELECT u.first_name, u.last_name, c.name, s.name, m.name\r\n" + 
			" FROM users u, log l, courses c, statuses s, marks m\r\n" + 
			" WHERE l.student_id=u.id AND l.course_id=c.id\r\n" +
			" AND c.status_id=s.id AND l.mark_id=m.id AND u.id=?";
	
	
}
