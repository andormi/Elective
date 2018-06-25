package elective.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ControllerProxy")
public class ControllerProxy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("CONTROLLERPROXY#Get");
		
		String page = (String) request.getParameter("command");
		System.out.println("PAGE: " + page);
		request.getRequestDispatcher("controller?command" + page).forward(request, response);
		
	}

}
