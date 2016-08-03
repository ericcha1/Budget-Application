package com.budgetapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * Controller for the About view.
 */
@Controller  
public class AboutController 
{  
	//the home view will be shown for the following URLs
	@RequestMapping(value="/about", method = RequestMethod.GET)  
    public ModelAndView about() 
	{  
        String message = "About Page";  
        return new ModelAndView("about", "message", message);  
    }
	
}  