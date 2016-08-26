package com.budgetapplication.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.budgetapplication.dao.LimitDAO;
import com.budgetapplication.model.Limit;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the LimitDAO interface.
 * Allows for modification, insertion, or deletion
 * of entries in the budget table. Each limit represents 
 */
public class LimitDAOImpl implements LimitDAO 
{
 
    private JdbcTemplate jdbcTemplate;
    
    //constructor, initializes jdbcTemplate
    public LimitDAOImpl(DataSource dataSource) 
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    //insert new limit
    @Override
    public void insert(Limit limit) 
    {
        String sql = "INSERT INTO limit_table (username, category, amount, insertedBy)"
                    + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, limit.getUsername(), limit.getCategory(),
                limit.getAmount(), limit.getInsertedBy());
    }
    
    //update existing limit
    public void update(Limit limit)
    {
        String sql = "UPDATE limit_table SET username=?, category=?, amount=? "
                    + "WHERE id=?";
        jdbcTemplate.update(sql, limit.getUsername(), limit.getCategory(),
                limit.getAmount(), limit.getId());
    }
 
    //delete an existing limit
    @Override
    public void delete(int id) 
    {
    	String sql = "DELETE FROM limit_table WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
 
    //returns a list of all the entries in the table
    @Override
    public List<Limit> list() 
    {
    	String sql = "SELECT * FROM limit_table";

        List<Limit> limitList = jdbcTemplate.query(sql, new RowMapper<Limit>() 
        {
        	@Override
            public Limit mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Limit limit = new Limit();
     
                limit.setId(rs.getInt("id"));
                limit.setUsername(rs.getString("username"));
                limit.setCategory(rs.getString("category"));
                limit.setAmount(rs.getDouble("amount"));
                limit.setInsertedBy(rs.getString("insertedBy"));
                limit.setInsertedOn(rs.getDate("insertedOn").toString());
                return limit;
            }
        });
     
        return limitList;
    }
    
    //returns a list of all the entries corresponding to a certain username
    @Override
    public List<Limit> list(String username) 
    {
    	//append the username to the query
    	String sql = "SELECT * FROM limit_table WHERE username='" + username + "'";

        List<Limit> limitList = jdbcTemplate.query(sql, new RowMapper<Limit>() 
        {
        	@Override
            public Limit mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Limit limit = new Limit();
     
                limit.setId(rs.getInt("id"));
                limit.setUsername(rs.getString("username"));
                limit.setCategory(rs.getString("category"));
                limit.setAmount(rs.getDouble("amount"));
                limit.setInsertedBy(rs.getString("insertedBy"));
                limit.setInsertedOn(rs.getDate("insertedOn").toString());
                return limit;
            }
        });
     
        return limitList;
    }
 
    @Override
    public Limit get(int id) 
    {
    	String sql = "SELECT * FROM limit_table WHERE id=" + id;
    	
        return jdbcTemplate.query(sql, new ResultSetExtractor<Limit>() 
        {
     
            @Override
            public Limit extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    Limit limit = new Limit();
                    limit.setId(rs.getInt("id"));
                    limit.setUsername(rs.getString("username"));
                    limit.setCategory(rs.getString("category"));
                    limit.setAmount(rs.getDouble("amount"));
                    limit.setInsertedBy(rs.getString("insertedBy"));
                    limit.setInsertedOn(rs.getDate("insertedOn").toString());
                    return limit;
                }
     
                return null;
            }
        });
    }
    
    @Override
    public Limit get(String username, String category) 
    {
    	String sql = "SELECT * FROM limit_table WHERE username='" + username 
    				+ "' AND category='" + category + "'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Limit>() 
        {
     
            @Override
            public Limit extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    Limit limit = new Limit();
                    limit.setId(rs.getInt("id"));
                    limit.setUsername(rs.getString("username"));
                    limit.setCategory(rs.getString("category"));
                    limit.setAmount(rs.getDouble("amount"));
                    limit.setInsertedBy(rs.getString("insertedBy"));
                    limit.setInsertedOn(rs.getDate("insertedOn").toString());
                    return limit;
                }
     
                return null;
            }
        });
    }
}