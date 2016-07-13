package com.budgetapplication.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.budgetapplication.dao.*;
import com.budgetapplication.model.*;
 
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the UserDAO interface.
 * Allows for modification, insertion, or deletion
 * of users in the table.
 *
 */
public class UserDAOImpl implements UserDAO 
{
 
    private JdbcTemplate jdbcTemplate;
 
    //constructor, initializes jdbcTemplate
    public UserDAOImpl(DataSource dataSource) 
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    //update user information, or create new user if it doesn't exist
    @Override
    public void saveOrUpdate(User user) 
    {
    	if (user.getId() > 0) 
    	{
            // update
            String sql = "UPDATE user_table SET Username=?, Password=?, Name=?, "
                        + "Role=? WHERE id=?";
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword(),
                    user.getName(), user.getRole(), user.getId());
        } 
    	else 
    	{
            // insert
            String sql = "INSERT INTO user_table (Username, Password, Name, Role)"
                        + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, user.getUsername(),
                    user.getPassword(), user.getName(), user.getName());
        }
    }
 
    //delete an existing user
    @Override
    public void delete(int userId) 
    {
    	String sql = "DELETE FROM user_table WHERE id=?";
        jdbcTemplate.update(sql, userId);
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
     
                user.setId(rs.getInt("Id"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setName(rs.getString("Name"));
                user.setRole(rs.getString("Role"));
     
                return user;
            }
        });
     
        return userList;
    }
 
    //search for a user by id
    @Override
    public User get(int userId) 
    {
    	String sql = "SELECT * FROM user_table WHERE Id=" + userId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<User>() 
        {
     
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    User user = new User();
                    user.setId(rs.getInt("Id"));
                    user.setUsername(rs.getString("Username"));
                    user.setPassword(rs.getString("Password"));
                    user.setName(rs.getString("Name"));
                    user.setRole(rs.getString("Role"));
                    return user;
                }
     
                return null;
            }
        });
    }
 
}