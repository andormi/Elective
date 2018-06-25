package elective.web.command;

import java.util.Map;
import java.util.TreeMap;

import elective.web.command.admin.DeleteCourseCommand;
import elective.web.command.admin.ListOfAllCoursesCommand;
import elective.web.command.student.ListCoursesCommand;

public class CommandContainer {

private static Map<String, ActionCommand> commands = new TreeMap<String, ActionCommand>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("listOfAllCoursesCommand", new ListOfAllCoursesCommand());
		commands.put("listCourses", new ListCoursesCommand());
		commands.put("deleteCourse", new DeleteCourseCommand());

	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static ActionCommand defineCommand(String commandName) {	
		
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("noCommand"); 
		}
		
		return commands.get(commandName);
	}
	
}
