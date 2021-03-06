package com.budgetapplication.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.budgetapplication.dao.BudgetEntryDAO;
import com.budgetapplication.model.BudgetEntry;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the BudgetEntryDAO interface.
 * Allows for modification, insertion, or deletion
 * of entries in the budget table. Each entry represents
 * an addition to one's spendings. 
 */
public class BudgetEntryDAOImpl implements BudgetEntryDAO 
{
 
    private JdbcTemplate jdbcTemplate;
    
    //constructor, initializes jdbcTemplate
    public BudgetEntryDAOImpl(DataSource dataSource) 
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    //insert new entry
    @Override
    public void insert(BudgetEntry entry) 
    {
        String sql = "INSERT INTO budget_table (username, category, amount, insertedBy)"
                    + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, entry.getUsername(), entry.getCategory(),
                entry.getAmount(), entry.getInsertedBy());
    }
    
    //update existing entry
    public void update(BudgetEntry entry)
    {
        String sql = "UPDATE budget_table SET username=?, category=?, amount=? "
                    + "WHERE id=?";
        jdbcTemplate.update(sql, entry.getUsername(), entry.getCategory(),
                entry.getAmount(), entry.getId());
    }
 
    //delete an existing entry
    @Override
    public void delete(int entryId) 
    {
    	String sql = "DELETE FROM budget_table WHERE id=?";
        jdbcTemplate.update(sql, entryId);
    }
 
    //returns a list of all the entries in the table
    @Override
    public List<BudgetEntry> list() 
    {
    	String sql = "SELECT * FROM budget_table";

        List<BudgetEntry> entryList = jdbcTemplate.query(sql, new RowMapper<BudgetEntry>() 
        {
        	@Override
            public BudgetEntry mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                BudgetEntry entry = new BudgetEntry();
     
                entry.setId(rs.getInt("id"));
                entry.setUsername(rs.getString("username"));
                entry.setCategory(rs.getString("category"));
                entry.setAmount(rs.getDouble("amount"));
                entry.setInsertedBy(rs.getString("insertedBy"));
                entry.setInsertedOn(rs.getDate("insertedOn").toString());
                return entry;
            }
        });
     
        return entryList;
    }
    
    //returns a list of all the entries corresponding to a certain username
    @Override
    public List<BudgetEntry> list(String username) 
    {
    	//append the username to the query
    	String sql = "SELECT * FROM budget_table WHERE username=\"" + username + "\"";

        List<BudgetEntry> entryList = jdbcTemplate.query(sql, new RowMapper<BudgetEntry>() 
        {
        	@Override
            public BudgetEntry mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                BudgetEntry entry = new BudgetEntry();
     
                entry.setId(rs.getInt("id"));
                entry.setUsername(rs.getString("username"));
                entry.setCategory(rs.getString("category"));
                entry.setAmount(rs.getDouble("amount"));
                entry.setInsertedBy(rs.getString("insertedBy"));
                entry.setInsertedOn(rs.getDate("insertedOn").toString());
                return entry;
            }
        });
     
        return entryList;
    }
    
    //returns a list of entries for a certain username between two dates
    @Override
    public List<BudgetEntry> list(String username, Date start, Date end) 
    {
    	//append the username to the query
    	String sql = "SELECT * FROM budget_table WHERE username='" + username 
    			+ "' AND insertedOn BETWEEN '" + start.toString() + "' AND '" 
    			+ end.toString() + "'";

        List<BudgetEntry> entryList = jdbcTemplate.query(sql, new RowMapper<BudgetEntry>() 
        {
        	@Override
            public BudgetEntry mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                BudgetEntry entry = new BudgetEntry();
     
                entry.setId(rs.getInt("id"));
                entry.setUsername(rs.getString("username"));
                entry.setCategory(rs.getString("category"));
                entry.setAmount(rs.getDouble("amount"));
                entry.setInsertedBy(rs.getString("insertedBy"));
                entry.setInsertedOn(rs.getDate("insertedOn").toString());
                return entry;
            }
        });
     
        return entryList;
    }

    //get entry by id
    @Override
    public BudgetEntry get(int id) 
    {
    	String sql = "SELECT * FROM budget_table WHERE id=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<BudgetEntry>() 
        {
     
            @Override
            public BudgetEntry extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    BudgetEntry entry = new BudgetEntry();
                    entry.setId(rs.getInt("id"));
                    entry.setUsername(rs.getString("username"));
                    entry.setCategory(rs.getString("category"));
                    entry.setAmount(rs.getDouble("amount"));
                    entry.setInsertedBy(rs.getString("insertedBy"));
                    entry.setInsertedOn(rs.getDate("insertedOn").toString());
                    return entry;
                }
     
                return null;
            }
        });
    }
    
    //returns a list of the five most recent entries corresponding for a certain username
    @Override
    public List<BudgetEntry> listRecent(String username) 
    {
    	//select the user's rows and order by time inserted
    	String sql = "SELECT * FROM budget_table WHERE username=\"" + username 
    			+ "\"ORDER BY insertedOn DESC LIMIT 5;";

        List<BudgetEntry> entryList = jdbcTemplate.query(sql, new RowMapper<BudgetEntry>() 
        {
        	@Override
            public BudgetEntry mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                BudgetEntry entry = new BudgetEntry();
     
                entry.setId(rs.getInt("id"));
                entry.setUsername(rs.getString("username"));
                entry.setCategory(rs.getString("category"));
                entry.setAmount(rs.getDouble("amount"));
                entry.setInsertedBy(rs.getString("insertedBy"));
                entry.setInsertedOn(rs.getDate("insertedOn").toString());
                return entry;
            }
        });
     
        return entryList;
    }
    
    @Override
    public double getTotal(String username)
    {
    	double total = 0;

    	//get list of entries for the user
        List<BudgetEntry> entryList = list(username);
        
        for (int i = 0; i < entryList.size(); i++)
        	total += entryList.get(i).getAmount();
    	
    	return total;
    }
 
}