package elective.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import elective.db.entity.Entity;
import elective.exception.AppException;
import elective.exception.UserDaoException;

public abstract class AbstractDAO<T extends Entity> {

	protected Connection connection;

	public AbstractDAO(Connection connection) throws AppException {
		this.connection = connection;
	}

	public abstract List<T> getAll();
	public abstract T createEntity(T entity) throws AppException;
	public abstract T getEntityById(Long id) throws AppException;
	public abstract boolean updateEntity(T entity) throws AppException;
	public abstract boolean deleteEntity(T entity) throws AppException;

	public void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(PreparedStatement prStmt) {
		try {
			if (prStmt != null) {
				prStmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt, ResultSet resultSet) {
		close(resultSet);
		close(stmt);
	}

	public void close(PreparedStatement prStmt, ResultSet resultSet) {
		close(resultSet);
		close(prStmt);
	}
	
}
