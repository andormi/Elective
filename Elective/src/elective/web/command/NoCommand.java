package elective.web.command;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elective.Path;
import elective.exception.AppException;

public class NoCommand extends ActionCommand {

	private static final long serialVersionUID = 3093961309707291645L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException, AppException {

		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);

		return Path.PAGE_ERROR_PAGE;
	}

}
