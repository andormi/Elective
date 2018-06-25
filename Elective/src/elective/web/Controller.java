package elective.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elective.Path;
import elective.exception.AppException;
import elective.web.command.ActionCommand;
import elective.web.command.CommandContainer;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("CONTROLLER#Get");
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("CONTROLLER#Post");
		processRequest(request, response);
	}

private void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
	
	response.setContentType("text/html; charset=UTF-8");
	request.setCharacterEncoding("UTF-8");
	
	String commandName = request.getParameter("command");
	ActionCommand command = CommandContainer.defineCommand(commandName);
		
	System.out.println("command: " + command);
	
	String page = Path.PAGE_ERROR_PAGE;
	try {
		page = command.execute(request, response);
	} catch (AppException ex) {
		request.setAttribute("errorMessage", ex.getMessage());
	}

	System.out.println("page: " + page);
	

	boolean proxyTrue = Boolean.parseBoolean(request.getParameter("proxy"));

	System.out.println("proxyTrue: " + proxyTrue);
	
	if (proxyTrue) {
		System.out.println("HERE");
		response.sendRedirect("controller?command=" + commandName);
	} else {
		request.getRequestDispatcher(page).forward(request, response);
	}

	}
	
}
