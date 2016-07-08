package com.budgetapplication.dao;

import java.util.List;
 
import com.budgetapplication.model.User;
 
/*
 * Defines DAO operations for the User model.
 *
 */
public interface UserDAO {
     
    public void saveOrUpdate(User User);
     
    public void delete(int UserId);
     
    public User get(int UserId);
     
    public List<User> list();
}