package com.budgetapplication.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.budgetapplication.dao.UserDAO;
import com.budgetapplication.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the UserDAO interface.
 * Allows for modification, insertion, or deletion
 * of users in the table.
 */
public class UserDAOImpl implements UserDAO 
{
 
    private JdbcTemplate jdbcTemplate;
    
    //constructor, initializes jdbcTemplate
    public UserDAOImpl(DataSource dataSource) 
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    //insert new user into table
    @Override
    public void insert(User user) 
    {
        String sql = "INSERT INTO user_table (username, password, name, role, enabled,"
        		+ " email, insertedBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), 
        		user.getRole(), user.getEnabled(), user.getEmail(), user.getInsertedBy());
    }
    
    //update an existing user
    public void update(User user)
    {
        // update
        String sql = "UPDATE user_table SET password=?, name=?, role=?, " +
        		"enabled=?, email=? WHERE username=?";
        jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getRole(), 
        		user.getEnabled(), user.getEmail(), user.getUsername()); 
        //is username mutable?
    }
    
    public void updateDuration(User user)
    {
        String sql = "UPDATE user_table SET durationId=? WHERE username=?";
        jdbcTemplate.update(sql, user.getDurationId(), user.getUsername()); 
    }
 
    //delete an existing user
    @Override
    public void delete(String username) 
    {
    	String sql = "DELETE FROM user_table WHERE username=?";
        jdbcTemplate.update(sql, username);
    }
 
    //search for a user by username
    @Override
    public User get(String username) 
    {
    	String sql = "SELECT * FROM user_table WHERE username='" + username + "'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<User>() 
        {
     
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setRole(rs.getString("role"));
                    user.setEnabled(rs.getBoolean("enabled"));
                    user.setEmail(rs.getString("email"));
                    user.setInsertedBy(rs.getString("insertedBy"));
                    user.setInsertedOn(rs.getDate("insertedOn").toString());
                    user.setDurationId(rs.getInt("durationId"));
                    return user;
                }
     
                return null;
            }
        });
    }
    
    //search for a user by email
    @Override
    public User getUserByEmail(String email) 
    {
    	String sql = "SELECT * FROM user_table WHERE email='" + email + "'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<User>() 
        {
     
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setRole(rs.getString("role"));
                    user.setEnabled(rs.getBoolean("enabled"));
                    user.setEmail(rs.getString("email"));
                    user.setInsertedBy(rs.getString("insertedBy"));
                    user.setInsertedOn(rs.getDate("insertedOn").toString());
                    user.setDurationId(rs.getInt("durationId"));
                    return user;
                }
     
                return null;
            }
        });
    }
    
    //returns a list of all the users in the table
    @Override
    public List<User> list() 
    {
    	String sql = "SELECT * FROM user_table";

        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() 
        {
        	@Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                User user = new User();
     
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setEmail(rs.getString("email"));
                user.setInsertedBy(rs.getString("insertedBy"));
                user.setInsertedOn(rs.getDate("insertedOn").toString());
                user.setDurationId(rs.getInt("durationId"));
                return user;
            }
        });
     
        return userList;
    }
    
    //returns a list of all the users corresponding to a certain username (should be one user)
    @Override
    public List<User> list(String currentUsername) 
    {
    	//append the username to the query
    	String sql = "SELECT * FROM user_table WHERE username='" + currentUsername + "'";

        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() 
        {
        	@Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                User user = new User();
                
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setRole(rs.getString("role"));
                user.setEnabled(rs.getBoolean("enabled"));
                user.setEmail(rs.getString("email"));
                user.setInsertedBy(rs.getString("insertedBy"));
                user.setInsertedOn(rs.getDate("insertedOn").toString());
                user.setDurationId(rs.getInt("durationId"));
                return user;
            }
        });
     
        return userList;
    }

    //Returns a list of all users with usernames like the given string.
	@Override
	public List<User> listSearch(String search) 
	{
		//append the username to the query
		String sql = "SELECT * FROM user_table WHERE username "
				+ "LIKE '%" + search + "%'";
	
	    List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() 
	    {
	    	@Override
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException 
	        {
	            User user = new User();
	            
	            user.setUsername(rs.getString("username"));
	            user.setPassword(rs.getString("password"));
	            user.setName(rs.getString("name"));
	            user.setRole(rs.getString("role"));
	            user.setEnabled(rs.getBoolean("enabled"));
	            user.setEmail(rs.getString("email"));
	            user.setInsertedBy(rs.getString("insertedBy"));
                user.setInsertedOn(rs.getDate("insertedOn").toString());
                user.setDurationId(rs.getInt("durationId"));
	            return user;
	        }
	    });
	 
	    return userList;
	}
}