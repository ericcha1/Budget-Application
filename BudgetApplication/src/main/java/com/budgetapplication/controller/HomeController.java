package com.budgetapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * Controller for the Home view.
 */
@Controller  
public class HomeController 
{  
	//the home view will be shown for the following URLs
	@RequestMapping(value={"", "/", "/home"})  
    public ModelAndView home() 
	{  
        String message = "Home Page";  
        return new ModelAndView("home", "message", message);  
    }
	
}  