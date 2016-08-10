package com.budgetapplication.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.budgetapplication.dao.UserDAO;
import com.budgetapplication.model.BudgetEntry;
import com.budgetapplication.model.User;

/*
 * Controller that handles the User model. This maps appropriate
 * URLs while calling functions from UserDAOImpl in order to 
 * create the CRUD functionality.
 */
@Controller 
public class UserController 
{
	@Autowired
    private UserDAO userDAO;
	
	//returns a view that contains a table of the current user
	@RequestMapping(value="/user")
	public ModelAndView listUser(ModelAndView model) throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<User> userList = userDAO.list();
	    model.addObject("userList", userList);
	    model.setViewName("user");
	 
	    return model;
	}
	
	//returns a view that contains a table of all of the users and account information
	@RequestMapping(value="/modifyUser")
	public ModelAndView listAllUsers(ModelAndView model) throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<User> userList = userDAO.list();
	    model.addObject("userList", userList);
	    model.setViewName("modifyUser");
	 
	    return model;
	}
	
	//returns the current user data, needed for the ajax call
	@RequestMapping(value="/currentData", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getCurrentUser() throws IOException
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();		
		
		//calls the list() function in the DAO implementation
	    List<User> userList = userDAO.list(currentUser);
	    
	    return userList;
	}
	
	//returns the entries of the user table, needed for the ajax call
	@RequestMapping(value="/userData", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getList() throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<User> userList = userDAO.list();
	    
	    return userList;
	}
	
	//returns a list of users with similar usernames to the one searched, needed for the ajax call
	@RequestMapping(value="/userSearch", method = RequestMethod.GET)
	@ResponseBody
	public List<User> search(HttpServletRequest request) throws IOException
	{	
		//calls the listSearch() function with the parameter from the request
	    List<User> userList = userDAO.listSearch(request.getParameter("search"));
	    
	    return userList;
	}

	//add a user
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView saveBudgetEntry(@ModelAttribute User user) 
	{
	    userDAO.insert(user);
	    return new ModelAndView("redirect:/");
	}
	
	//delete a user from the table
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request)
	{
	    String username = request.getParameter("username");
	    userDAO.delete(username);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing user in the table
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUserUser(HttpServletRequest request) 
	{
		//retrieve the existing user for the given username
		String username = request.getParameter("username");
	    User user = userDAO.get(username);
	    
	    //modify the variables for the user
	    user.setPassword(request.getParameter("password"));
	    user.setName(request.getParameter("name"));
	    user.setRole(request.getParameter("role"));
	    user.setEnabled(Boolean.parseBoolean(request.getParameter("Enabled")));
	    
	    //update user
	    userDAO.update(user);
	    return new ModelAndView("redirect:/");
	}
}
