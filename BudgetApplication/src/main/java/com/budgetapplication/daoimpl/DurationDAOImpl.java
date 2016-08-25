package com.budgetapplication.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.budgetapplication.dao.DurationDAO;
import com.budgetapplication.model.Duration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the DurationDAO interface.
 */
public class DurationDAOImpl implements DurationDAO 
{
 
    private JdbcTemplate jdbcTemplate;
    
    //constructor, initializes jdbcTemplate
    public DurationDAOImpl(DataSource dataSource) 
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    //insert new duration
    @Override
    public void insert(Duration duration) 
    {
        String sql = "INSERT INTO duration_table (username, startDate, endDate, insertedBy)"
                    + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, duration.getUsername(), duration.getStartDate(),
                duration.getEndDate(), duration.getInsertedBy());
    }
    
    //update existing duration
    public void update(Duration duration)
    {
        String sql = "UPDATE duration_table SET username=?, startDate=?, endDate=? "
                    + "WHERE id=?";
        jdbcTemplate.update(sql, duration.getUsername(), duration.getStartDate(),
                duration.getEndDate(), duration.getId());
    }
 
    //delete an existing duration
    @Override
    public void delete(int id) 
    {
    	String sql = "DELETE FROM duration_table WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
 
    //returns a list of all the entries in the table
    @Override
    public List<Duration> list() 
    {
    	String sql = "SELECT * FROM duration_table";

        List<Duration> durationList = jdbcTemplate.query(sql, new RowMapper<Duration>() 
        {
        	@Override
            public Duration mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Duration duration = new Duration();
     
                duration.setId(rs.getInt("id"));
                duration.setUsername(rs.getString("username"));
                duration.setStartDate(rs.getDate("startDate"));
                duration.setEndDate(rs.getDate("endDate"));
                duration.setInsertedBy(rs.getString("insertedBy"));
                duration.setInsertedOn(rs.getDate("insertedOn").toString());
                return duration;
            }
        });
     
        return durationList;
    }
    
    //returns a list of all the entries corresponding to a certain username
    @Override
    public List<Duration> list(String username) 
    {
    	//append the username to the query
    	String sql = "SELECT * FROM duration_table WHERE username=\"" + username + "\"";

        List<Duration> durationList = jdbcTemplate.query(sql, new RowMapper<Duration>() 
        {
        	@Override
            public Duration mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Duration duration = new Duration();
     
                duration.setId(rs.getInt("id"));
                duration.setUsername(rs.getString("username"));
                duration.setStartDate(rs.getDate("startDate"));
                duration.setEndDate(rs.getDate("endDate"));
                duration.setInsertedBy(rs.getString("insertedBy"));
                duration.setInsertedOn(rs.getDate("insertedOn").toString());
                return duration;
            }
        });
     
        return durationList;
    }
 
    @Override
    public Duration get(int id) 
    {
    	String sql = "SELECT * FROM duration_table WHERE id=" + id;
        return jdbcTemplate.query(sql, new ResultSetExtractor<Duration>() 
        {
     
            @Override
            public Duration extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    Duration duration = new Duration();
                    
                    duration.setId(rs.getInt("id"));
                    duration.setUsername(rs.getString("username"));
                    duration.setStartDate(rs.getDate("startDate"));
                    duration.setEndDate(rs.getDate("endDate"));
                    duration.setInsertedBy(rs.getString("insertedBy"));
                    duration.setInsertedOn(rs.getDate("insertedOn").toString());
                    return duration;
                }
     
                return null;
            }
        });
    }
}