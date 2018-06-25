package elective.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import elective.db.ConnectionPool;
import elective.db.SQLQueries;
import elective.db.dao.AbstractDAO;
import elective.db.entity.Category;
import elective.exception.AppException;

public class CategoryDao extends AbstractDAO<Category> {

	public CategoryDao(Connection connection) throws AppException {
		super(connection);
	}

	@Override
	public List<Category> getAll() {
		List<Category> categoryList = new ArrayList<>();
		Connection conn = connection;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;

		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_ALL_CATEGORIES);
			resultSet = prStmt.executeQuery();

			while (resultSet.next()) {
				Category ñategory = new Category();
				ñategory.setId(resultSet.getLong("id"));
				ñategory.setName(resultSet.getString("name"));
				categoryList.add(ñategory);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			try {
				resultSet.close();
				prStmt.close();
				ConnectionPool.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return categoryList;
	}

	@Override
	public Category getEntityById(Long id) {
		Category category = new Category();
		Connection conn = connection;
		PreparedStatement prStmt = null;
		ResultSet resultSet = null;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_GET_CATEGORY_BY_ID);
			prStmt.setLong(1, id);
			resultSet = prStmt.executeQuery();
			resultSet.next();

			category.setId(id);
			category.setName(resultSet.getString("name"));
			
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			try {
				resultSet.close();
				prStmt.close();
				ConnectionPool.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return category;
	}

	@Override
	public boolean deleteEntity(Category category) {
		return this.removeEntity(category);
	}

	@Override
	public Category createEntity(Category category) {
		boolean result = false;

		Connection conn = connection;
		PreparedStatement prStmt = null;
		// ResultSet resultSet = null;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_INSERT_CATEGORY);
			prStmt.setLong(1, category.getId());
			prStmt.setString(2, category.getName());
			prStmt.executeUpdate();

			/////////////////////
			// User usertmp = this.getEntityById((long) id);
			// if (usertmp != null) {
			// result = true;
			// }

		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			try {
				// resultSet.close();
				prStmt.close();
				ConnectionPool.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;	
		
	}

	@Override
	public Category updateEntity(Category category) {
		Connection conn = connection;
		PreparedStatement prStmt = null;
		// ResultSet resultSet = null;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_UPDATE_CATEGORY);
			prStmt.setString(1, category.getName());
			prStmt.setLong(2, category.getId());
			prStmt.executeUpdate();

		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			try {
				// resultSet.close();
				prStmt.close();
				ConnectionPool.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return category;
	}
	
	// Not inherited methods:
	
	public boolean deleteEntity(Long id) {
		return this.removeEntity(id);
	}
	
	public boolean removeEntity(Object object) {
		boolean result = false;

		Connection conn = connection;
		PreparedStatement prStmt = null;
		// ResultSet resultSet = null;
		try {
			prStmt = conn.prepareStatement(SQLQueries.SQL_DELETE_CATEGORY);
			String name = object.getClass().getSimpleName();
		
			switch (name) {
			case "Long":
				prStmt.setLong(1, (long) object);
				break;
			case "Category":
				prStmt.setLong(1, ((Category) object).getId());
				break;
			}
			prStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			try {
				// resultSet.close();
				prStmt.close();
				ConnectionPool.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
