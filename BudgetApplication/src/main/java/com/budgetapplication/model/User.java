package com.budgetapplication.model;

/*
 * User model which contains basic user information.
 */
public class User 
{
	//user attributes
    private String username;
    private String password;
    private String name; //real name
    private String role; //used for access
    private boolean enabled; //whether account is active
    private String email;
	private String insertedBy;
	private String insertedOn;
	private int durationId;
 
    //default constructor
    public User() 
    {
    	//empty
    }
 
    //constructor with parameters, defaults enabled to true
    public User(String username, String password, String name, String role, 
    		String email, String insertedBy, String insertedOn) 
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.enabled = true;
        this.email = email;
        this.insertedBy = insertedBy;
        this.insertedOn = insertedOn;
        this.durationId = -1;
    }
    
    //constructor with parameters, allows for enabled to be set explicitly
    public User(String username, String password, String name, String role,
    		boolean enabled, String email, String insertedBy, String insertedOn, int durationId) 
    {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.enabled = enabled;
        this.email = email;
        this.insertedBy = insertedBy;
        this.insertedOn = insertedOn;
        this.durationId = durationId;
    }
 
    //getters and setters
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
    
    public void setEnabled(boolean userEnabled)
    {
    	enabled = userEnabled;
    }
    
    public boolean getEnabled()
    {
    	return enabled;
    }
    
    public void setEmail(String userEmail)
    {
    	email = userEmail;
    }
    
    public String getEmail()
    {
    	return email;
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
	
	public int getDurationId()
	{
		return durationId;
	}
	
	public void setDurationId(int durationId)
	{
		this.durationId = durationId;
	}
}