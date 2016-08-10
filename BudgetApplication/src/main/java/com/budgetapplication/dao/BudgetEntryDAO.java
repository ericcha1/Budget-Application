package com.budgetapplication.dao;

import java.util.List;
 
import com.budgetapplication.model.BudgetEntry;
 
/*
 * Declares DAO operations for the BudgetEntry model
 * through an interface. Allows for interaction with
 * the budget table in the database.
 */
public interface BudgetEntryDAO 
{
    /**
     * Update an entry's information, or insert a new entry
     * if it does not exist.
     * @param entry
     */
    public void insertOrUpdate(BudgetEntry entry);
    
    /**
     * Remove an entry from the budget table.
     * @param userId
     */
    public void delete(int userId);
    
    /**
     * Get an entry corresponding to an id.
     * @param userId
     * @return BudgetEntry
     */
    public BudgetEntry get(int userId);
    
    /**
     * Returns a list of all entries in the budget table.
     * @return List<BudgetEntry>
     */
    public List<BudgetEntry> list();

    /**
     * Returns a list of all entries corresponding to a
     * username.
     * @param username
     * @return List<BudgetEntry>
     */
	public List<BudgetEntry> list(String username);
	
	/**
	 * Returns the total spendings of a user,
	 * @param username
	 * @return double
	 */
	public double getTotal(String username);
}