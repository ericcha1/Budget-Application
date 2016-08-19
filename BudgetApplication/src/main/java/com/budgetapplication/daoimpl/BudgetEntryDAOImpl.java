package com.budgetapplication.daoimpl;

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
        String sql = "INSERT INTO budget_table (username, category, amount)"
                    + " VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, entry.getUsername(), entry.getCategory(),
                entry.getAmount());
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
     
                return entry;
            }
        });
     
        return entryList;
    }
    
    //returns a list of all the entries corresponding to a certain username
    @Override
    public List<BudgetEntry> list(String currentUsername) 
    {
    	//append the username to the query
    	String sql = "SELECT * FROM budget_table WHERE username=\"" + currentUsername + "\"";

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
     
                return entry;
            }
        });
     
        return entryList;
    }
 
    @Override
    public BudgetEntry get(int entryId) 
    {
    	String sql = "SELECT * FROM budget_table WHERE id=" + entryId;
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

                    return entry;
                }
     
                return null;
            }
        });
    }
    
    @Override
    public double getTotal(String username)
    {
    	double total = 0;

    	//get list of entries for the user
        List<BudgetEntry> entryList = list(username);
        
        for (int i = 0; i < entryList.size(); i++)
        {
        	total += entryList.get(i).getAmount();
        }
    	
    	return total;
    }
 
}