package com.budgetapplication.dao;

import java.util.List;
 
import com.budgetapplication.model.Duration;
 
/*
 * Declares DAO operations for the Duration model
 * through an interface. Allows for interaction with
 * the duration table in the database.
 */
public interface DurationDAO 
{
    /**
     * Insert a new duration into the table.
     * @param duration
     * @return int
     */
    public int insert(Duration duration);
    
    /**
     * Update a duration's information.
     * @param duration
     */
    public void update(Duration duration);
    
    /**
     * Remove a duration from the table.
     * @param userId
     */
    public void delete(int id);
    
    /**
     * Get a duration corresponding to an id.
     * @param userId
     * @return Duration
     */
    public Duration get(int id);
    
    /**
     * Returns a list of all entries in the duration table.
     * @return List<Duration>
     */
    public List<Duration> list();

    /**
     * Returns a list of all entries corresponding to a
     * username.
     * @param username
     * @return List<Duration>
     */
	public List<Duration> list(String username);
}