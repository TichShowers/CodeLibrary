package persistence.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public abstract class DatabaseDao<T> implements IDataAccessObject<T>
{
    Connection _connection;

    public DatabaseDao(Connection connection)
    {
        _connection = connection;
    }

    public Connection getConnection()
    {
        return _connection;
    }

    public abstract String getTableName();

    public abstract String[] getColumnNames();

    public abstract List<T> fetchGroupResult(ResultSet result)
            throws SQLException;

    public abstract T fetchSingleResult(ResultSet result)
            throws SQLException;

    protected final PreparedStatement prepareInsertStatement()
            throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(getTableName()).append("(");
        for (int i = 1; i < getColumnNames().length; i++)
        {
            sql.append(getColumnNames()[i]);
            if (i + 1 < getColumnNames().length)
            {
                sql.append(", ");
            }
        }
        sql.append(")");
        sql.append("VALUES (");
        for (int i = 1; i < getColumnNames().length; i++)
        {
            sql.append("?");
            if (i + 1 < getColumnNames().length)
            {
                sql.append(",");
            }
        }
        sql.append(")");
        return _connection.prepareStatement(sql.toString());
    }

    protected final PreparedStatement prepareSelectAllStatement()
            throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        for (String column : getColumnNames())
        {
            sql.append(column).append(", ");
        }
        sql.delete(sql.lastIndexOf(","), sql.length());
        sql.append(" FROM ").append(getTableName());
        return _connection.prepareStatement(sql.toString());
    }

    protected final PreparedStatement prepareSelectStatement()
            throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        for (String column : getColumnNames())
        {
            sql.append(column).append(", ");
        }
        sql.delete(sql.lastIndexOf(","), sql.length());
        sql.append(" FROM ").append(getTableName()).append(" WHERE ")
                .append(getColumnNames()[0]).append(" = ? ");
        
        return _connection.prepareStatement(sql.toString());
    }

    protected final PreparedStatement prepareDeleteStatement()
            throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(this.getTableName())
                .append(" WHERE ").append(getColumnNames()[0]).append(" = ? ");
        return _connection.prepareStatement(sql.toString());
    }

    protected final PreparedStatement prepareUpdateStatement()
            throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(this.getTableName()).append(" SET ");
        for (int i = 1; i < getColumnNames().length; i++)
        {
            sql.append(getColumnNames()[i]).append("=?");
            if (i + 1 < getColumnNames().length)
            {
                sql.append(", ");
            }
        }

        sql.append(" WHERE ").append(getColumnNames()[0]).append(" = ? ");

        return _connection.prepareStatement(sql.toString());
    }

    protected final PreparedStatement prepareSearchStatement(String key)
            throws SQLException
    {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        for (String column : getColumnNames())
        {
            sql.append(column).append(", ");
        }
        sql.delete(sql.lastIndexOf(","), sql.length());
        sql.append(" FROM ").append(getTableName()).append(" WHERE ")
                .append(key).append(" = ? ");
        return _connection.prepareStatement(sql.toString());
    }
    
    protected final PreparedStatement prepareGivenSearchStatement(String value) throws SQLException{
        return _connection.prepareStatement(value);
    }

    @Override
    public T read(int id)
    {
        try
        {
            PreparedStatement statement = this.prepareSelectStatement();
            statement.setInt(1, id);
            
            return fetchSingleResult(statement.executeQuery());
        }
        catch (SQLException ex)
        {
        	System.out.println(DatabaseDao.class.getName() + " " + ex.getMessage());
        	return null;
        }
    }

    @Override
    public List<T> readAll()
    {
        try
        {
            PreparedStatement statement = this.prepareSelectAllStatement();
            ResultSet result = statement.executeQuery();
            return fetchGroupResult(result);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    @Override
    public void update(T data)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(int id)
    {
        try
        {
            PreparedStatement statement = this.prepareDeleteStatement();
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(T data)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> search(String key, String value)
    {
        try
        {
            PreparedStatement statement = this.prepareSearchStatement(key);
            statement.setString(1, value);
            return fetchGroupResult(statement.executeQuery());

        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    @Override
    public List<T> search(String key, int value)
    {
        try
        {
            PreparedStatement statement = this.prepareSearchStatement(key);
            statement.setInt(1, value);
            ResultSet result = statement.executeQuery();
            return fetchGroupResult(result);

        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    @Override
    public List<T> search(String key, double value)
    {
        try
        {
            PreparedStatement statement = this.prepareSearchStatement(key);
            statement.setDouble(1, value);
            return fetchGroupResult(statement.executeQuery());

        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    @Override
    public List<T> search(String key, Date value)
    {
        try
        {
            PreparedStatement statement = this.prepareSearchStatement(key);
            statement.setDate(1, new java.sql.Date(value.getTime()));
            return fetchGroupResult(statement.executeQuery());

        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatabaseDao.class.getName())
                    .log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

    @Override
    public void insert(T data)
    {
        throw new UnsupportedOperationException();
    }
}
