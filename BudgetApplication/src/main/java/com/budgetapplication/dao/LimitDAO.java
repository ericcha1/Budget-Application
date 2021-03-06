package com.budgetapplication.dao;

import java.util.List;
import com.budgetapplication.model.Limit;
 
/*
 * Declares DAO operations for the Limit model
 * through an interface. Allows for interaction with
 * the limit table in the database.
 */
public interface LimitDAO 
{
    /**
     * Insert a new limit into the limit table.
     * @param limit
     */
    public void insert(Limit limit);
    
    /**
     * Update a limit's information.
     * @param limit
     */
    public void update(Limit limit);
    
    /**
     * Remove a limit from the limit table.
     * @param id
     */
    public void delete(int id);
    
    /**
     * Get a limit corresponding to an id.
     * @param id
     * @return Limit
     */
    public Limit get(int id);
    
    /**
     * Get a limit corresponding to an user and
     * category.
     * @param username, category
     * @return Limit
     */
    public Limit get(String username, String category);
    
    /**
     * Returns a list of all entries in the limit table.
     * @return List<Limit>
     */
    public List<Limit> list();

    /**
     * Returns a list of all entries corresponding to a
     * username.
     * @param username
     * @return List<Limit>
     */
	public List<Limit> list(String username);
}