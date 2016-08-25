package com.budgetapplication.model;

import java.sql.Date;

/*
 * Duration model which contains duration of a
 * user's budget.
 */
public class Duration
{
	//attributes of an duration in the budget table
    private int id;
    private String username;
    private Date startDate;
    private Date endDate;
    private String insertedBy;
    private String insertedOn;

    //default constructor
    public Duration() 
    {
    	//empty
    }
 
    //constructor with parameters
    public Duration(int id, String username, Date startDate, 
    		Date endDate, String insertedBy, String insertedOn) 
    {
    	this.id = id;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
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
    
    public void setStartDate(Date startDate)
    {
    	this.startDate = startDate;
    }
    
    public Date getStartDate()
    {
    	return startDate;
    }
    
    public void setEndDate(Date endDate)
    {
    	this.endDate = endDate;
    }
    
    public Date getEndDate()
    {
    	return endDate;
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