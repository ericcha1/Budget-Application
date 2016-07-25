package com.budgetapplication.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.budgetapplication.dao.UserDAO;
import com.budgetapplication.model.User;

/*
 * Controller that handles the User model. This maps appropriate
 * URLs while calling functions from UserDAOImpl in order to 
 * create the CRUD functionality.
 */
@Controller 
public class UserController 
{
	@Autowired (required=false)
    private UserDAO userDAO;
	
	//returns a view that contains a table of all of the users and account information
	@RequestMapping(value="/allUsers")
	public ModelAndView listUser(ModelAndView model) throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<User> userList = userDAO.list();
	    model.addObject("userList", userList);
	    model.setViewName("allUsers");
	 
	    return model;
	}
	
	//displays the entries of the user table in xml format, needed for the ajax call
	@RequestMapping(value="/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getList() throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<User> userList = userDAO.list();
	    
	    return userList;
	}
	
	//add a row or BudgetEntry to the budget table
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView model) 
	{
	    User user = new User();
	    model.addObject("user", user);
	    model.setViewName("UserForm");
	    return model;
	}
	
	//save changes to an user
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) 
	{
	    userDAO.saveOrUpdate(user);
	    return new ModelAndView("redirect:/");
	}
	
	//delete a user from the table
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) 
	{
	    int userId = Integer.parseInt(request.getParameter("id"));
	    userDAO.delete(userId);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing entry in the table
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) 
	{
	    int userId = Integer.parseInt(request.getParameter("id"));
	    User user = userDAO.get(userId);
	    ModelAndView model = new ModelAndView("UserForm");
	    model.addObject("user", user);
	 
	    return model;
	}
}
