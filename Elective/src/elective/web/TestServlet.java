package elective.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import elective.db.ConnectionPool;
import elective.db.SQLQueries;
import elective.db.dao.impl.UserDao;
import elective.db.entity.Category;
import elective.db.entity.Course;
import elective.db.entity.User;
import elective.db.enumentity.Mark;

@WebServlet("/")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {

			User newuser = new User();
			newuser.setLogin("новыйлогин7");
			newuser.setPassword("новыйпароль");
			newuser.setFirstName("новыйимя");
			newuser.setLastName("новыйфамилия");
			newuser.setRoleId(1);
			newuser.setId(26L);
			
			Connection conn = ConnectionPool.getConnection();
			
			conn.setAutoCommit(false);
			
			UserDao userdao = new UserDao(conn);
//			userdao.updateEntity(newuser);
//			userdao.deleteEntity(userdao.getEntityByLogin("новыйлогин7"));
			userdao.deleteEntity(29L);
//			userdao.createEntity(newuser);
//			userdao.createEntity(newuser);
			
			
			PreparedStatement prStmt = null;
			ResultSet resultSet = null;

			response.getWriter().println("<table border=\"1\">");
			
			try {
				prStmt = conn.prepareStatement(SQLQueries.SQL_GET_ALL_STUDENTS_FROM_LOG);
//				prStmt.setLong(1, 12);
				resultSet = prStmt.executeQuery();

				while (resultSet.next()) {
					response.getWriter().println("<tr>");
					
					response.getWriter().println("<td>");
					response.getWriter().println(resultSet.getString("first_name"));
					response.getWriter().println("</td>");
					
					response.getWriter().println("<td>");
					response.getWriter().println(resultSet.getString("last_name"));
					response.getWriter().println("</td>");
					
					response.getWriter().println("<td>");
					response.getWriter().println(resultSet.getString(3));
					response.getWriter().println("</td>");
					
					response.getWriter().println("<td>");
					response.getWriter().println(resultSet.getString(4));
					response.getWriter().println("</td>");
					
					response.getWriter().println("<td>");
					response.getWriter().println(resultSet.getString(5));
					response.getWriter().println("</td>");
					
					response.getWriter().println("</tr>");
				}
			} catch (SQLException e) {
				System.err.println("SQL exception (request or table failed): " + e);
			} finally {
				try {
					resultSet.close();
					prStmt.close();
					// conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			response.getWriter().println("</table>");
			
			conn.commit();
			
			response.getWriter().println("<br>");
			response.getWriter().println(newuser);
			response.getWriter().println("<br>");
//			response.getWriter().println("<H1>" + res + "</H1>");
			
//			response.getWriter().println(userList);
			
			ConnectionPool.closeConnection(conn);
			
		} catch (Exception e) {
			
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
