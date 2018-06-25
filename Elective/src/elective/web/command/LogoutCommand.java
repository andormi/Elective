package elective.web.command;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import elective.Path;
import elective.exception.AppException;

public class LogoutCommand extends ActionCommand {

	private static final long serialVersionUID = 1190601447925551578L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException, AppException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return Path.PAGE_LOGIN;
	}
}
