package elective.web;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class SessionRequestContent {

//	private HashMap<String, Object> requestAttributes;
	private HashMap<String, String> requestParameters;
//	private HashMap<String, Object> sessionAttributes;
	
	// ������������
	// ����� ���������� ���������� �� �������
	public void extractValues(HttpServletRequest request) {
		String parameter = "command";
		requestParameters.put(parameter, request.getParameter(parameter));
	}
	
	public String getParameters(String nameParameter) {
		return requestParameters.get(nameParameter);
	}
	
	// ����� ���������� � ������ ������ ��� �������� � jsp
	public void insertAttributes(HttpServletRequest request) {
	// ����������
	}
	
}
