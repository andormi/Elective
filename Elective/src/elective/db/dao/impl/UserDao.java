package elective.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import elective.db.SQLQueries;
import elective.db.dao.AbstractDAO;
import elective.db.entity.User;
import elective.exception.AppException;
import elective.exception.UserDaoException;

public class UserDao extends AbstractDAO<User> {

	public UserDao(Connection connection) throws AppException {
		super(connection);
	}

	@Override
	public List<User> getAll() {
		List<User> userList = new ArrayList<>();
		Statement stmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;

		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(SQLQueries.SQL_GET_ALL_USERS);

			while (resultSet.next()) {
				userList.add(extractUser(resultSet));
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(stmt, resultSet);
		}

		return userList;
	}

	@Override
	public User getEntityById(Long id) {
		User user = null;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_USER_BY_ID);
			prStmt.setLong(1, id);
			resultSet = prStmt.executeQuery();
			resultSet.next();
			user = extractUser(resultSet);

		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt, resultSet);
		}

		return user;
	}

	@Override
	public boolean deleteEntity(User user) throws UserDaoException {
		return this.removeUser(user);
	}

	// Not inherited method:
	public boolean deleteEntity(Long id) throws UserDaoException {
		return this.removeUser(id);
	}
	
	@Override
	public User createEntity(User user) throws UserDaoException {
		User newUser = null;
		int count;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;
		try {
			count = createUser(conn, user);
			if (count != 1) {
                throw new UserDaoException("On update modify more then 1 record: " + count);
            }
			
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		}
		
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_USER_BY_ID);
			prStmt.setString(1, "last_insert_id()");
			resultSet = prStmt.executeQuery();
			resultSet.next();
			newUser = extractUser(resultSet);
			
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt, resultSet);
		}

		return newUser;
	}

	@Override
	public boolean updateEntity(User user) throws UserDaoException {
		boolean result = false;
		int count;
		Connection conn = super.connection;

		try {
			count = updateUser(conn, user);
			if (count != 1) {
                throw new UserDaoException("On update modify more then 1 record: " + count);
            } else {
            	result = true;
            }
			
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		}

		return result;
	}

	// Not inherited methods:

	public User getEntityByLogin(String login) {
		User user = null;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		Connection conn = super.connection;

		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_USER_BY_LOGIN);
			prStmt.setString(1, login);
			resultSet = prStmt.executeQuery();
			resultSet.next();
			user = extractUser(resultSet);

		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt, resultSet);
		}
		return user;
	}

	// Inner methods:
	
	private boolean removeUser(Object object) throws UserDaoException {
		boolean result = false;
		int count;
		PreparedStatement prStmt = null;
		Connection conn = super.connection;	
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_DELETE_USER);
			String name = object.getClass().getSimpleName();
		
			switch (name) {
			case "Long":
				prStmt.setLong(1, (long) object);
				break;
			case "User":
				prStmt.setLong(1, ((User) object).getId());
				break;
			}
			count = prStmt.executeUpdate();
			
			if (count != 1) {
                throw new UserDaoException("On update modify more then 1 record: " + count);
            } else {
            	result = true;
            }
			
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			close(prStmt);
		}
		return result;
	}

	private User extractUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong("id"));
		user.setLogin(resultSet.getString("login"));
		user.setPassword(resultSet.getString("password"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setRoleId(resultSet.getInt("role_id"));
		return user;
	}

	private int createUser(Connection conn, User user) throws SQLException {
		PreparedStatement prStmt = null;
		int count;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_INSERT_USER);
			int k = 1;
			prStmt.setString(k++, user.getLogin());
			prStmt.setString(k++, user.getPassword());
			prStmt.setString(k++, user.getFirstName());
			prStmt.setString(k++, user.getLastName());
			prStmt.setInt(k, user.getRoleId());
			count = prStmt.executeUpdate();
		} finally {
			close(prStmt);
		}
		return count;
	}
	
	private int updateUser(Connection conn, User user) throws SQLException {
		PreparedStatement prStmt = null;
		int count;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_UPDATE_USER);
			int k = 1;
			prStmt.setString(k++, user.getLogin());
			prStmt.setString(k++, user.getPassword());
			prStmt.setString(k++, user.getFirstName());
			prStmt.setString(k++, user.getLastName());
			prStmt.setInt(k++, user.getRoleId());
			prStmt.setLong(k, user.getId());
			count = prStmt.executeUpdate();
		} finally {
			close(prStmt);
		}
		return count;
	}
	
}
