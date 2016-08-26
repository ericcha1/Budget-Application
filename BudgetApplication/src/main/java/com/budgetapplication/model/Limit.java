package com.budgetapplication.model;

/*
 * Limit model which sets a limit on a
 * user's spendings in a certain category.
 */
public class Limit
{
	//attributes of an limit in the budget table
    private int id;
    private String username;
    private String category;
    private double amount;
    private String insertedBy;
    private String insertedOn;

    //default constructor
    public Limit() 
    {
    	//empty
    }
 
    //constructor with parameters
    public Limit(int id, String username, String category, 
    		double amount, String insertedBy, String insertedOn) 
    {
    	this.id = id;
        this.username = username;
        this.category = category;
        this.amount = amount;
        this.insertedBy = insertedBy;
        this.insertedOn = insertedOn;
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
    
	public String getInsertedBy()
	{
		return insertedBy;
	}
	
	public void setInsertedBy(String username)
	{
		this.insertedBy = username;
	}
	
	public String getInsertedOn()
	{
		return insertedOn;
	}
	
	public void setInsertedOn(String date)
	{
		this.insertedOn = date;
	}
    
}