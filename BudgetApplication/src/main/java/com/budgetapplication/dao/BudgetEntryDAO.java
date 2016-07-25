package com.budgetapplication.dao;

import java.util.List;
 
import com.budgetapplication.model.BudgetEntry;
 
/*
 * Declares DAO operations for the BudgetEntry model
 * through an interface.
 */
public interface BudgetEntryDAO 
{
    /**
     * 
     * @param entry
     */
    public void saveOrUpdate(BudgetEntry entry);
    
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
    public BudgetEntry get(int userId);
    
    /**
     * 
     * @return
     */
    public List<BudgetEntry> list();
}