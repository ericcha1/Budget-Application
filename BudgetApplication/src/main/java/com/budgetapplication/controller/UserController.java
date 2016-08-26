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

import com.budgetapplication.dao.BudgetEntryDAO;
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
    private BudgetEntryDAO budgetEntryDAO;
	@Autowired
    private UserDAO userDAO;
	
	//returns a view that contains a table of the current user
	@RequestMapping(value="/user")
	public ModelAndView listUser() throws IOException
	{
	    return new ModelAndView("user", "total", getTotal());
	}
	
	//returns a view that contains a table of all of the users and account information
	@RequestMapping(value="/modifyUser")
	public ModelAndView listAllUsers(ModelAndView model) throws IOException
	{
		return new ModelAndView("modifyUser", "total", getTotal());
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
	public ModelAndView addUser(@ModelAttribute User user) throws IOException 
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();		
		user.setInsertedBy(currentUser);
		
	    userDAO.insert(user);
	    return new ModelAndView("redirect:/");
	}
	
	//delete a user from the table
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request) throws IOException
	{
	    String username = request.getParameter("username");
	    userDAO.delete(username);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing user in the table
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) throws IOException 
	{
		//retrieve the existing user for the given username
	    User user = userDAO.get(request.getParameter("username"));
	    
	    //modify the variables for the user
	    user.setPassword(request.getParameter("password"));
	    user.setName(request.getParameter("name"));
	    user.setRole(request.getParameter("role"));
	    user.setEnabled(Boolean.parseBoolean(request.getParameter("enabled")));
	    user.setEmail(request.getParameter("email"));
	    
	    //update user
	    userDAO.update(user);
	    return new ModelAndView("redirect:/");
	}
	
	public String getTotal() throws IOException
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    String stringTotal = String.format("%.2f", total);
	    return stringTotal;
	}
}
