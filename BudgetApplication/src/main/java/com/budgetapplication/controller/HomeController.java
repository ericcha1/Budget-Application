package com.budgetapplication.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.budgetapplication.dao.UserDAO;
import com.budgetapplication.model.User;

@Controller  
public class HomeController 
{  
	
	@Autowired
    private UserDAO userDAO;
	
	@RequestMapping(value={"", "/", "/home", "/BudgetApplication/home"})  
    public ModelAndView home() 
	{  
        String message = "Home Page";  
        return new ModelAndView("home", "message", message);  
    }
	
}  