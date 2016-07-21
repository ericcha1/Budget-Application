package com.budgetapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller  
public class HomeController 
{  
	
	@RequestMapping(value={"", "/", "/home", "/BudgetApplication/home"})  
    public ModelAndView home() 
	{  
        String message = "Home Page";  
        return new ModelAndView("home", "message", message);  
    }
	
}  