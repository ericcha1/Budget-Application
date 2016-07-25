package com.budgetapplication.model;

/*
 * User model which contains basic user information.
 */
public class User 
{
	//user attributes
    private int id;
    private String username;
    private String password;
    private String name; //real name
    private String role; //used for access
    private boolean enabled; //whether account is active
 
    //default constructor
    public User() 
    {
    	//empty
    }
 
    //constructor with parameters, defaults enabled to true
    public User(int id, String username, String password, String name, String role) 
    {
    	this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.enabled = true;
    }
    
    //constructor with parameters, allows for enabled to be set explicitly
    public User(int id, String username, String password, String name, String role, boolean enabled) 
    {
    	this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.enabled = enabled;
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
    
    public void setEnabled(boolean userEnable)
    {
    	enabled = userEnable;
    }
    
    public boolean getEnabled()
    {
    	return enabled;
    }
    
}