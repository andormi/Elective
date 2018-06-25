package elective.db.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class DAOFactory {

	private static final String FQN_DAOFACTORY =
			"ua.nure.dormidontov.SummaryTask4.db.dao.DAOFactory";

	private static DAOFactory instance;

	public static DAOFactory getInstance() {
		if (instance == null) {
			String className = null;
			try (BufferedReader in = new BufferedReader(new FileReader(FQN_DAOFACTORY))) {
				className = in.readLine();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			try {
				instance = (DAOFactory) Class.forName(className).newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
				ex.printStackTrace();
			}

		}
		return instance;
	}

	protected DAOFactory() {
	}

	public abstract AbstractDAO<?> getAbstractDAO();
	
}
