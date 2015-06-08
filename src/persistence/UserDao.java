package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import persistence.common.DatabaseDao;

public class UserDao extends DatabaseDao<User> {

	public final String _tableName = "users";
	public final String[] _columnNames = { "id", "name", "username", "password", "email", "admin" };

	public UserDao(Connection connection) {
		super(connection);
	}

	@Override
	public String getTableName() {
		return _tableName;
	}

	@Override
	public String[] getColumnNames() {
		return _columnNames;
	}

	@Override
	public List<User> fetchGroupResult(ResultSet result) throws SQLException {
		List<User> list = new ArrayList<>();

		while (result.next()) {
			int id = result.getInt(_columnNames[0]);
			String name = result.getString(_columnNames[1]);
			String username = result.getString(_columnNames[2]);
			String password = result.getString(_columnNames[3]);
			String email = result.getString(_columnNames[4]);
			boolean isAdmin = result.getBoolean(_columnNames[5]);

			User user = new User(id, name, username, email, isAdmin);
			user.setPassword(password);

			list.add(user);
		}

		return list;
	}

	@Override
	public User fetchSingleResult(ResultSet result) throws SQLException {
		if (result.next()) {
			int id = result.getInt(_columnNames[0]);
			String name = result.getString(_columnNames[1]);
			String username = result.getString(_columnNames[2]);
			String password = result.getString(_columnNames[3]);
			String email = result.getString(_columnNames[4]);
			boolean isAdmin = result.getBoolean(_columnNames[5]);

			User user = new User(id, name, username, email, isAdmin);
			user.setPassword(password);

			return user;
		}

		return null;
	}

	@Override
	public void update(User data) {
		try
        {
            PreparedStatement statement = this.prepareUpdateStatement();
            statement.setString(1, data.getName());
            statement.setString(2, data.getUsername());
            statement.setString(3, data.getPassword());
            statement.setString(4, data.getEmail());
            statement.setBoolean(5, data.isAdmin());
            statement.setInt(6, data.getId());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(LanguageDao.class.getName() + " ");
        }
	}

	@Override
	public void insert(User data) {
		try
        {
            PreparedStatement statement = this.prepareInsertStatement();
            statement.setString(1, data.getName());
            statement.setString(2, data.getUsername());
            statement.setString(3, data.getPassword());
            statement.setString(4, data.getEmail());
            statement.setBoolean(5, data.isAdmin());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(LanguageDao.class.getName() + " ");
        }
	}

	
	
}
