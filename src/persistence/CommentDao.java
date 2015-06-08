package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Comment;
import models.User;
import persistence.common.DatabaseDao;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class CommentDao extends DatabaseDao<Comment> {

	private UserDao userDao;
	private final String _tableName = "comments";
	private final String[] _columnNames = {"id","fragment", "user", "at", "what"};
	
	public CommentDao(Connection connection) {
		super(connection);
		userDao = new UserDao(connection);
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
	public List<Comment> fetchGroupResult(ResultSet result) throws SQLException {
		List<Comment> list = new ArrayList<Comment>();
		
		while (result.next())
        {
            int id = result.getInt(_columnNames[0]);
            int fragment = result.getInt(_columnNames[1]);
            User user = userDao.read(result.getInt(_columnNames[2]));
            Date at = result.getDate(_columnNames[3]);
            String what = result.getString(_columnNames[4]);

            Comment data = new Comment(id, fragment, user, at, what);

            list.add(data);
        }
        return list;
	}

	@Override
	public Comment fetchSingleResult(ResultSet result) throws SQLException {
		if (result.next())
        {
            int id = result.getInt(_columnNames[0]);
            int fragment = result.getInt(_columnNames[1]);
            User user = userDao.read(result.getInt(_columnNames[2]));
            Date at = result.getDate(_columnNames[3]);
            String what = result.getString(_columnNames[4]);

            Comment data = new Comment(id, fragment, user, at, what);

            return data;
        }
		return null;
	}

	@Override
	public void update(Comment data) {
		try
        {
            PreparedStatement statement = this.prepareUpdateStatement();
            statement.setInt(1, data.getFragment());
            statement.setInt(2, data.getUser().getId());
            statement.setDate(3, new java.sql.Date(data.getAt().getTime()));
            statement.setString(4, data.getWhat());
            statement.setInt(5, data.getId());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(FragmentDao.class.getName() + " ");
        }
	}

	@Override
	public void insert(Comment data) {
		try
        {
            PreparedStatement statement = this.prepareInsertStatement();
            statement.setInt(1, data.getFragment());
            statement.setInt(2, data.getUser().getId());
            statement.setDate(3, new java.sql.Date(data.getAt().getTime()));
            statement.setString(4, data.getWhat());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(FragmentDao.class.getName() + " ");
        }
	}	
}
