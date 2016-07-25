package com.budgetapplication.dao;

import java.util.List;
 
import com.budgetapplication.model.User;
 
/*
 * Declares DAO operations for the User model
 * through an interface. Allows for interaction
 * with the user table in the database.
 */
public interface UserDAO 
{
	/**
	 * Update an user's information, or insert a new user
     * if it does not exist.
	 * @param user
	 */
    public void insertOrUpdate(User user);
     
    /**
     * Remove an user from the user table.
     * @param userId
     */
    public void delete(int userId);
    
    /**
     * Get an user corresponding to a user's id.
     * @param userId
     * @return User
     */
    public User get(int userId);
     
    /**
     * Returns a list of all users in the user table.
     * @return List<User>
     */
    public List<User> list();
}