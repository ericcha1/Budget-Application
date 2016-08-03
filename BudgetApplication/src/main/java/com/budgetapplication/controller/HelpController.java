package com.budgetapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * Controller for the Help view.
 */
@Controller  
public class HelpController 
{  
	//the home view will be shown for the following URLs
	@RequestMapping(value="/help", method = RequestMethod.GET)  
    public ModelAndView help() 
	{  
        String message = "Help Page";  
        return new ModelAndView("help", "message", message);  
    }
	
}  