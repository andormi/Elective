package elective.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import elective.db.SQLQueries;
import elective.db.bean.StudentCourseBean;
import elective.db.dao.AbstractDAO;
import elective.db.entity.Course;
import elective.exception.AppException;
import elective.exception.CourseDaoException;

public class CourseDao extends AbstractDAO<Course> {

	public CourseDao(Connection connection) throws AppException {
		super(connection);
	}

	@Override
	public List<Course> getAll() {
		List<Course> courseList = new ArrayList<>();
		Statement stmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(SQLQueries.SQL_GET_ALL_COURSES);

			while (resultSet.next()) {
				courseList.add(extractCourse(resultSet));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(stmt, resultSet);
		}

		return courseList;
	}

	@Override
	public Course getEntityById(Long id) {
		Course course = null;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;
		
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_COURSE_BY_ID);
			prStmt.setLong(1, id);
			resultSet = prStmt.executeQuery();
			resultSet.next();
			course = extractCourse(resultSet);

		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt, resultSet);
		}

		return course;
	}

	@Override
	public boolean deleteEntity(Course course) throws CourseDaoException {
		return this.removeCourse(course);
	}
	
	// Not inherited method:
	public boolean deleteEntity(Long id) throws CourseDaoException {
		return this.removeCourse(id);
	}

	@Override
	public Course createEntity(Course course) throws CourseDaoException {
		Course newCourse = null;
		int count;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;
		try {
			count = createCourse(conn, course);
			if (count != 1) {
                throw new CourseDaoException("On update modify more then 1 record: " + count);
            }

		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt);
		}

		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_COURSE_BY_ID);
			prStmt.setString(1, "last_insert_id()");
			resultSet = prStmt.executeQuery();
			resultSet.next();
			newCourse = extractCourse(resultSet);
			
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt, resultSet);
		}
		
		return newCourse;
	}

	@Override
	public boolean updateEntity(Course course) throws CourseDaoException {
		boolean result = false;
		int count;
		Connection conn = super.connection;

		try {
			count = updateCourse(conn, course);
			if (count != 1) {
                throw new CourseDaoException("On update modify more then 1 record: " + count);
            } else {
            	result = true;
            }

		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		}

		return result;
	}
	
	// Not inherited methods:
	
	public List<StudentCourseBean> getCoursesyByStudentId(Long statusId, Long studentId) {
		List<StudentCourseBean> courseList = new ArrayList<>();
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_COURSE_BY_STUDENT_ID);
			prStmt.setLong(1, statusId);
			prStmt.setLong(2, studentId);
			resultSet = prStmt.executeQuery();

			while (resultSet.next()) {
				courseList.add(extractStudentCourseBean(resultSet));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed) HERE: " + e);
		} finally {
			close(prStmt, resultSet);
		}

		return courseList;
	}
	
	// Inner methods:
	
	private boolean removeCourse(Object object) throws CourseDaoException {
		boolean result = false;
		int count;
		PreparedStatement prStmt = null;
		Connection conn = super.connection;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_DELETE_COURSE);
			String name = object.getClass().getSimpleName();
		
			switch (name) {
			case "Long":
				prStmt.setLong(1, (long) object);
				break;
			case "Course":
				prStmt.setLong(1, ((Course) object).getId());
				break;
			}
			count = prStmt.executeUpdate();
			
			if (count != 1) {
                throw new CourseDaoException("On update modify more then 1 record: " + count);
            } else {
            	result = true;
            }
			
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt);
		}
		return result;
	}

	private Course extractCourse(ResultSet resultSet) throws SQLException {
		Course course = new Course();
		course.setId(resultSet.getLong("id"));
		course.setName(resultSet.getString("name"));
		course.setDuration(resultSet.getInt("duration"));
		course.setCategoryId(resultSet.getLong("category_id"));
		course.setStatusId(resultSet.getInt("status_id"));
		course.setProfessorId(resultSet.getLong("professor_id"));
		return course;
	}

	private int createCourse(Connection conn, Course course) throws SQLException {
		PreparedStatement prStmt = null;
		int count;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_INSERT_COURSE);
			int k = 1;
			prStmt.setString(k++, course.getName());
			prStmt.setInt(k++, course.getDuration());
			prStmt.setLong(k++, course.getCategoryId());
			prStmt.setInt(k++, course.getStatusId());
			prStmt.setLong(k, course.getProfessorId());
			count = prStmt.executeUpdate();
		} finally {
			close(prStmt);
		}
		return count;
	}
	
	private int updateCourse(Connection conn, Course course) throws SQLException {
		PreparedStatement prStmt = null;
		int count;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_UPDATE_COURSE);
			int k = 1;
			prStmt.setString(k++, course.getName());
			prStmt.setInt(k++, course.getDuration());
			prStmt.setLong(k++, course.getCategoryId());
			prStmt.setInt(k++, course.getStatusId());
			prStmt.setLong(k++, course.getProfessorId());
			prStmt.setLong(k, course.getId());
			count = prStmt.executeUpdate();
		} finally {
			close(prStmt);
		}
		return count;
	}

	//======================================
	
	private StudentCourseBean extractStudentCourseBean(ResultSet resultSet) throws SQLException {
		StudentCourseBean stCourseBean = new StudentCourseBean();
		stCourseBean.setId(resultSet.getLong("id"));
		stCourseBean.setCourseName(resultSet.getString("course_name"));
		stCourseBean.setMark(resultSet.getString("mark_name"));
		stCourseBean.setStatus(resultSet.getString("status_name"));
		stCourseBean.setProfessorLastName(resultSet.getString("last_name"));
		return stCourseBean;	
	}
	
}
