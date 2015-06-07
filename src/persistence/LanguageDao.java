package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.common.DatabaseDao;
import persistence.common.IDataAccessObject;
import models.Language;

public class LanguageDao extends DatabaseDao<Language> implements IDataAccessObject<Language>{

	private final String _tableName = "languages";
    private final String[] _columnNames =
    {
        "id", "name"
    };
	
	public LanguageDao(Connection connection) {
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
	public List<Language> fetchGroupResult(ResultSet result)
			throws SQLException {
		List<Language> list = new ArrayList<>();
		
		while (result.next())
        {
            int id = result.getInt(_columnNames[0]);
            String name = result.getString(_columnNames[1]);

            Language data = new Language(id, name);

            list.add(data);
        }
        return list;
	}

	@Override
	public Language fetchSingleResult(ResultSet result) throws SQLException {		
		if (result.next())
        {
            int id = result.getInt(_columnNames[0]);
            String name = result.getString(_columnNames[1]);
            
            Language data = new Language(id, name);
            
            return data;
        }
		
        return null;
	}

	@Override
	public void update(Language data) {
		try
        {
            PreparedStatement statement = this.prepareUpdateStatement();
            statement.setString(1, data.getName());
            statement.setInt(2, data.getId());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(LanguageDao.class.getName() + " ");
        }
	}

	@Override
	public void insert(Language data) {
		try
        {
            PreparedStatement statement = this.prepareInsertStatement();
            statement.setString(1, data.getName());
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(LanguageDao.class.getName() + " ");
        }
	}
	
	

}
