package elective.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class SessionRequestContent {

//	private HashMap<String, Object> requestAttributes;
	private HashMap<String, String> requestParameters;
//	private HashMap<String, Object> sessionAttributes;
	
	// конструкторы
	// метод извлечения информации из запроса
	public void extractValues(HttpServletRequest request) {
		String parameter = "command";
		requestParameters.put(parameter, request.getParameter(parameter));
	}
	
	public String getParameters(String nameParameter) {
		return requestParameters.get(nameParameter);
	}
	
	// метод добавления в запрос данных для передачи в jsp
	public void insertAttributes(HttpServletRequest request) {
	// реализация
	}
	
}
