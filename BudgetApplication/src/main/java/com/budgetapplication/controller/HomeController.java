package com.budgetapplication.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.budgetapplication.dao.BudgetEntryDAO;
import com.budgetapplication.model.BudgetEntry;

/*
 * Controller for the Home view.
 */
@Controller  
public class HomeController 
{  
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	
	//the home view will be shown for the following URLs
	@RequestMapping(value={"", "/", "/home"})  
    public ModelAndView home() throws IOException 
	{  
        double total = getTotal();
        return new ModelAndView("home", "total", total);  
    }
	
	//@RequestMapping(value="/total", method = RequestMethod.GET)
	//@ResponseBody
	public double getTotal() throws IOException
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    
	    return total;
	}
}  