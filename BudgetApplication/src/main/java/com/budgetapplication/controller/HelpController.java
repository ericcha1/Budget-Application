package com.budgetapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.budgetapplication.dao.BudgetEntryDAO;

/*
 * Controller for the Help view.
 */
@Controller  
public class HelpController 
{  
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	
	//the home view will be shown for the following URLs
	@RequestMapping(value="/help", method = RequestMethod.GET)  
    public ModelAndView help()
	{  
        return new ModelAndView("help", "total", getTotal());  
    }
	
	public String getTotal()
	{
		//store username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    String stringTotal = String.format("%.2f", total);
	    return stringTotal;
	}
	
}  