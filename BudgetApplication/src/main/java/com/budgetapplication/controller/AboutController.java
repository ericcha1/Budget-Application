package com.budgetapplication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.budgetapplication.dao.BudgetEntryDAO;

/*
 * Controller for the About view.
 */
@Controller  
public class AboutController 
{  
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	
	//the home view will be shown for the following URLs
	@RequestMapping(value="/about", method = RequestMethod.GET)  
    public ModelAndView about() throws IOException 
	{   
        return new ModelAndView("about", "total", getTotal());  
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