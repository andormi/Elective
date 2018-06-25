package elective.web.command;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elective.Path;
import elective.exception.AppException;

public class ViewSettingsCommand extends ActionCommand {

	private static final long serialVersionUID = -8685286088625781498L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException, AppException {
		// return Path.PAGE_SETTINGS;
		return null;
	}

}
