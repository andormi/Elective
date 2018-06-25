package elective.web.command.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import elective.Path;
import elective.db.ConnectionPool;
import elective.db.bean.StudentCourseBean;
import elective.db.dao.impl.CourseDao;
import elective.db.entity.Course;
import elective.db.entity.User;
import elective.exception.AppException;
import elective.web.command.ActionCommand;

public class ListCoursesCommand extends ActionCommand {

	private static final long serialVersionUID = -3399138771272266900L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, AppException {

		HttpSession session = request.getSession(false);
		Connection conn = null;
		String page = null;
		List<StudentCourseBean> listCourses = null;
		try {
			conn = ConnectionPool.getConnection();

//			Long statusId = Long.parseLong(request.getParameter("status"));
			Long statusId = 2L;
			
			Long studentId = ((User) session.getAttribute("user")).getId();		
			listCourses = new CourseDao(conn).getCoursesyByStudentId(statusId, studentId);

//			listCourses = new CourseDao(conn).getAll();
			
			page = Path.PAGE_LIST_COURSES;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ConnectionPool.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("listCourses", listCourses);
		
		return page;
	}

}
