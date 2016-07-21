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
import com.budgetapplication.daoimpl.UserDAOImpl;
import com.budgetapplication.model.User;

@Controller 
public class UserController 
{
	@Autowired (required=false)
    private UserDAO userDAO;
	
	@RequestMapping(value="/allUsers")
	public ModelAndView listUser(ModelAndView model) throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<User> userList = userDAO.list();
	    model.addObject("userList", userList);
	    model.setViewName("allUsers");
	 
	    return model;
	}
	

	@RequestMapping(value="/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getList() throws IOException
	{
		//calls the list() function in the DAO implementation
	    //List<User> userList = userDAO.list();
	    
	    return userDAO.list();
	}
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView model) 
	{
	    User user = new User();
	    model.addObject("user", user);
	    model.setViewName("UserForm");
	    return model;
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute User user) 
	{
	    userDAO.saveOrUpdate(user);
	    return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) 
	{
	    int userId = Integer.parseInt(request.getParameter("id"));
	    userDAO.delete(userId);
	    return new ModelAndView("redirect:/");
	}
	
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
