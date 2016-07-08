package com.budgetapplication.model;

/*
 * User model which contains basic user information.
 *
 */
public class User 
{
    private int id;
    private String username;
    private String password;
    private String name;
    private String role;
 
    //default constructor
    public User() 
    {
    	//empty
    }
 
    //constructor with parameters
    public User(int id, String username, String password, String name, String role) 
    {
    	this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
    }
 
    //getters and setters
    public void setId(int userId)
    {
    	id = userId;
    }
    
    public int getId()
    {
    	return id;
    }
    
    public void setUsername(String userUsername)
    {
    	username = userUsername;
    }
    
    public String getUsername()
    {
    	return username;
    }
    
    public void setPassword(String userPassword)
    {
    	password = userPassword;
    }
    
    public String getPassword()
    {
    	return password;
    }
    
    public void setName(String userName)
    {
    	name = userName;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public void setRole(String userRole)
    {
    	role = userRole;
    }
    
    public String getRole()
    {
    	return role;
    }
    
}