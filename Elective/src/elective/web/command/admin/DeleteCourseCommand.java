package elective.web.command.admin;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import elective.Path;
import elective.db.ConnectionPool;
import elective.db.dao.impl.CourseDao;
import elective.db.entity.Course;
import elective.exception.AppException;
import elective.web.command.ActionCommand;

public class DeleteCourseCommand extends ActionCommand {

	private static final long serialVersionUID = -8410347044674234522L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException, AppException {

		System.out.println("DeleteCourseCommand#execute");
		
		
		HttpSession session = request.getSession(false);
		Connection conn = null;
		String page = null;
		boolean deleteResult = false;
		String[] courseId;
		Long longId;
		try {
			conn = ConnectionPool.getConnection();

			page = Path.PAGE_ERROR_PAGE;
			
			courseId = request.getParameterValues("courseId");
			
			System.out.println("reguest: " + request.getAttribute("listOfAllCourses"));
			System.out.println("courseId: " + courseId.length);
			
			longId = Long.parseLong(courseId[0]);
			
			System.out.println("longId: " + longId);
			
			deleteResult = new CourseDao(conn).deleteEntity(longId);
			
			System.out.println("result: " + deleteResult);
			
			if (deleteResult) {
				page = Path.COMMAND_ADMIN;
				System.out.println("page: " + page);
			}
			
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
		
//		request.setAttribute("listOfAllCourses", listOfAllCourses);
		
		
		
		return page;
		
	}

}
