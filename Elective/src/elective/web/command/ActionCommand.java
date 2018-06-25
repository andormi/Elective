package elective.web.command;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elective.exception.AppException;

public abstract class ActionCommand implements Serializable {
	private static final long serialVersionUID = 1153518661806057404L;

	public abstract String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException, AppException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}

}
