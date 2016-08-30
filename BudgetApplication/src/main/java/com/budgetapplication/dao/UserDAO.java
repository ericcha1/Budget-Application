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
	 * Insert a new user into the table.
	 * @param user
	 */
    public void insert(User user);
    
    /**
	 * Update an existing user into the table.
	 * @param user
	 */
    public void update(User user);
    
    /**
	 * Update a user's current budget duration id.
	 * @param user
	 */
    public void updateDuration(User user);
     
    /**
     * Remove a user from the user table.
     * @param username
     */
    public void delete(String username);
    
    /**
     * Get a user corresponding to a username.
     * Easiest way to get a user.
     * @param userId
     * @return User
     */
    public User get(String username);
    
    /**
     * Get an user corresponding to a user's email.
     * @param email
     * @return User
     */
    public User getUserByEmail(String email);
     
    /**
     * Returns a list of all users in the user table.
     * @return List<User>
     */
    public List<User> list();
    
    /**
     * Returns a list of all users corresponding to a
     * username. This should only return one user.
     * @param currentUsername
     * @return List<User>
     */
	public List<User> list(String currentUsername);
	
    /**
     * Returns a list of all users with usernames like
     * the given string.
     * @param search
     * @return List<User>
     */
	public List<User> listSearch(String search);
}