package elective.web.command;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import elective.Path;
import elective.db.ConnectionPool;
import elective.db.dao.impl.UserDao;
import elective.db.entity.User;
import elective.db.enumentity.Role;
import elective.exception.AppException;

public class LoginCommand extends ActionCommand {

	private static final long serialVersionUID = 1350839695715177955L;

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, AppException {

		System.out.println("LoginCommand#execute");
		
		HttpSession session = request.getSession();
		Connection conn = null;
		String page = null;
		try {
			conn = ConnectionPool.getConnection();
			String login = request.getParameter(PARAM_NAME_LOGIN);
			String password = request.getParameter(PARAM_NAME_PASSWORD);

			if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
				throw new AppException("Login/password cannot be empty");
			}

			User user = new UserDao(conn).getEntityByLogin(login);
			if (user == null || !password.equals(user.getPassword())) {
				throw new AppException("Cannot find user with such login/password");
			}

			Role userRole = Role.getRole(user);

			page = Path.PAGE_ERROR_PAGE;

			if (userRole == Role.ADMIN) {
				page = Path.COMMAND_ADMIN;
			}

			if (userRole == Role.LECTURER) {
				page = Path.COMMAND_LECTURER;
			}

			if (userRole == Role.STUDENT) {
				page = Path.COMMAND_LIST_COURSES;
			}

			session.setAttribute("user", user);
			session.setAttribute("userRole", userRole);

			session.setAttribute("fname", user.getFirstName());
			session.setAttribute("lname", user.getLastName());

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
		return page;
	}

}
