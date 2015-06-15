package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Comment;
import models.Fragment;
import persistence.common.DatabaseDao;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class FragmentDao extends DatabaseDao<Fragment> {

	private final String _tableName = "fragments";
	private final String[] _columnNames = {"id", "title", "code", "at", "language"};
	
	public FragmentDao(Connection connection) {
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
	
	public List<Fragment> readAllWithNumberOfComments()
	{
		try {
			PreparedStatement statement = getConnection().prepareStatement("SELECT F.id, F.title, F.code, F.at, F.language, count(c.id) as comments FROM fragments F LEFT JOIN comments c ON F.id = C.fragment GROUP BY f.id;");
	
			ResultSet result = statement.executeQuery();
			
			List<Fragment> list = new ArrayList<>();
			
			while(result.next())
			{
				int id = result.getInt(_columnNames[0]);
				String title = result.getString(_columnNames[1]);
				String code = result.getString(_columnNames[2]);
				Date at = result.getDate(_columnNames[3]);
				int language = result.getInt(_columnNames[4]);
				int comments = result.getInt("comments");
				
				Fragment fragment = new Fragment(id, title, code, at, language, comments);
				
				list.add(fragment);
			}
			
			return list;
		}
		catch(SQLException e)
		{
			return new ArrayList<Fragment>();
		}
	}

	@Override
	public List<Fragment> fetchGroupResult(ResultSet result)
			throws SQLException {
		List<Fragment> list = new ArrayList<>();
		
		while(result.next())
		{
			int id = result.getInt(_columnNames[0]);
			String title = result.getString(_columnNames[1]);
			String code = result.getString(_columnNames[2]);
			Date at = result.getDate(_columnNames[3]);
			int language = result.getInt(_columnNames[4]);
			
			Fragment fragment = new Fragment(id, title, code, at, language);
			
			list.add(fragment);
		}
		
		return list;
	}

	@Override
	public Fragment fetchSingleResult(ResultSet result) throws SQLException {
		if(result.next())
		{
			int id = result.getInt(_columnNames[0]);
			String title = result.getString(_columnNames[1]);
			String code = result.getString(_columnNames[2]);
			Date at = result.getDate(_columnNames[3]);
			int language = result.getInt(_columnNames[4]);
			
			Fragment fragment = new Fragment(id, title, code, at, language);
			
			return fragment;
		}
		
		return null;
	}

	@Override
	public void insert(Fragment data) {
		try
        {
            PreparedStatement statement = this.prepareInsertStatement();
            statement.setString(1, data.getTitle());
            statement.setString(2, data.getCode());
            statement.setDate(3, new java.sql.Date(data.getAt().getTime()));
            statement.setInt(4, data.getLanguage());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(FragmentDao.class.getName() + " ");
        }
	}

	@Override
	public void update(Fragment data) {
		try
        {
            PreparedStatement statement = this.prepareUpdateStatement();
            statement.setString(1, data.getTitle());
            statement.setString(2, data.getCode());
            statement.setDate(3, new java.sql.Date(data.getAt().getTime()));
            statement.setInt(4, data.getLanguage());
            statement.setInt(5, data.getId());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(FragmentDao.class.getName() + " ");
        }
	}
	
	

}
