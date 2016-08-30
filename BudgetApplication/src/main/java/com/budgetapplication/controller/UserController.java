package com.budgetapplication.controller;

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
import com.budgetapplication.model.User;

/*
 * Controller that handles the User model. This maps appropriate
 * URLs to allow CRUD to be applied to users.
 */
@Controller 
public class UserController 
{
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	@Autowired
    private UserDAO userDAO;
	
	//contains a table of the current user but can view others (no CRUD)
	@RequestMapping(value="/user")
	public ModelAndView listUser()
	{
	    return new ModelAndView("user", "total", getTotal());
	}
	
	//contains a table of all of the users and account information (with CRUD)
	@RequestMapping(value="/modifyUser")
	public ModelAndView listAllUsers(ModelAndView model)
	{
		return new ModelAndView("modifyUser", "total", getTotal());
	}
	
	//returns the current user data
	@RequestMapping(value="/currentData", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getCurrentUser()
	{
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
	    List<User> userList = userDAO.list(currentUser);   
	    return userList;
	}
	
	//list of all users
	@RequestMapping(value="/userData", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getList()
	{
	    List<User> userList = userDAO.list();
	    return userList;
	}
	
	//returns a list of users with similar usernames to the one searched
	@RequestMapping(value="/userSearch", method = RequestMethod.GET)
	@ResponseBody
	public List<User> search(HttpServletRequest request)
	{	
		//obtains list with similar usernames as search
	    List<User> userList = userDAO.listSearch(request.getParameter("search"));
	    return userList;
	}

	//add a user
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user)
	{
		//get username and insert
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();		
		user.setInsertedBy(currentUser);
	    userDAO.insert(user);
	    return new ModelAndView("redirect:/");
	}
	
	//delete a user
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request)
	{
	    String username = request.getParameter("username");
	    userDAO.delete(username);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing user
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request)
	{
		//retrieve existing user
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
	
	public String getTotal()
	{
		//store current username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    String stringTotal = String.format("%.2f", total);
	    return stringTotal;
	}
}
