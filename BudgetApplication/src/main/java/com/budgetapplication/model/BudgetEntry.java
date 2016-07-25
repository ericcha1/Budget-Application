package com.budgetapplication.model;

/*
 * BudgetEntry model which contains attributes of a
 * user's expenditure.
 */
public class BudgetEntry
{
	//attributes of an entry in the budget table
    private int id;
    private String username;
    private String category;
    private double amount;

    //default constructor
    public BudgetEntry() 
    {
    	//empty
    }
 
    //constructor with parameters
    public BudgetEntry(int id, String username, String category, double amount) 
    {
    	this.id = id;
        this.username = username;
        this.category = category;
        this.amount = amount;
    }
 
    //getters and setters
    public void setId(int id)
    {
    	this.id = id;
    }
    
    public int getId()
    {
    	return id;
    }
    
    public void setUsername(String username)
    {
    	this.username = username;
    }
    
    public String getUsername()
    {
    	return username;
    }
    
    public void setCategory(String category)
    {
    	this.category = category;
    }
    
    public String getCategory()
    {
    	return category;
    }
    
    public void setAmount(double amount)
    {
    	this.amount = amount;
    }
    
    public double getAmount()
    {
    	return amount;
    }
    
}