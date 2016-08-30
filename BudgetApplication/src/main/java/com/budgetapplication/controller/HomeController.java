package com.budgetapplication.controller;

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
    public ModelAndView home()
	{  
        return new ModelAndView("home", "total", getTotal());  
    }
	
	//get a list of the last five entries in the budget table
	@RequestMapping(value="/recent", method = RequestMethod.GET)
	@ResponseBody
    public List<BudgetEntry> getRecent() 
	{  
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        
		//call to database that retrieves most recent entries for user
		List<BudgetEntry> recentEntries = budgetEntryDAO.listRecent(currentUser);
        
		return recentEntries;
    }
	
	public String getTotal()
	{
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    String stringTotal = String.format("%.2f", total);
	    return stringTotal;
	}
}  