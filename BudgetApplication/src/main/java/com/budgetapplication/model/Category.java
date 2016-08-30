package com.budgetapplication.model;

public class Category 
{
	//attributes of a category in the category_table
	private String category;
	private String insertedBy;
	private String insertedOn;
	
	public Category()
	{
		//default constructor
	}
	
	public Category(String category, String insertedBy, String insertedOn)
	{
		this.category = category;
		this.insertedBy = insertedBy;
		this.insertedOn	= insertedOn;
	}
	
	public Category(String category)
	{
		this.category = category;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
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
