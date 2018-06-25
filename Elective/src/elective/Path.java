package elective;

public final class Path {

	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/jsp/error_page.jsp";
	public static final String PAGE_ADMIN = "/WEB-INF/jsp/admin/admin_page.jsp";
	public static final String PAGE_STUDENT = "/WEB-INF/jsp/student/list_courses.jsp";
	public static final String PAGE_LIST_COURSES = "/WEB-INF/jsp/student/list_courses.jsp";
//	public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";
	
	// commands
	public static final String COMMAND_ADMIN = "/controller?command=listOfAllCoursesCommand&proxy=true";
	public static final String COMMAND_LECTURER = "/controller?command=lecturer";
	public static final String COMMAND_LIST_COURSES = "/controller?command=listCourses&proxy=true";
	public static final String COMMAND_DELETE_COURSE = "/controller?command=deleteCourse&proxy=true";
	
}
