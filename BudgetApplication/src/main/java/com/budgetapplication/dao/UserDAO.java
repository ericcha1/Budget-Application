package com.budgetapplication.dao;

import java.util.List;
 
import com.budgetapplication.model.User;
 
/*
 * Declares DAO operations for the User model
 * through an interface.
 */
public interface UserDAO 
{
	/**
	 * 
	 * @param user
	 */
    public void saveOrUpdate(User user);
     
    /**
     * 
     * @param userId
     */
    public void delete(int userId);
    
    /**
     * 
     * @param userId
     * @return
     */
    public User get(int userId);
     
    /**
     * 
     * @return
     */
    public List<User> list();
}