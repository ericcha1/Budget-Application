package com.budgetapplication.controller;

import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.budgetapplication.dao.BudgetEntryDAO;
import com.budgetapplication.dao.DurationDAO;
import com.budgetapplication.model.BudgetEntry;
import com.budgetapplication.model.Duration;

/*
 * Controller that handles the History. Allows for the
 * retrieval of data between certain dates.
 */
@Controller 
public class HistoryController 
{
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	@Autowired
	private DurationDAO durationDAO;
	
	//displays a table with the current user's budget entries
	@RequestMapping(value="/history")
	public ModelAndView showHistory()
	{
	    return new ModelAndView("history", "total", getTotal());
	}
	
	//list of all dates for current user
	@RequestMapping(value="/dates", method = RequestMethod.GET)
	@ResponseBody
	public List<Duration> getDates()
	{
		//calls the list() function for user signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
	    List<Duration> durationList = durationDAO.list(currentUser);
	    
	    return durationList;
	}
	
	//gets a list of  entries that go between the start and end dates
	@RequestMapping(value="/dateEntries", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetEntry> getEntries(HttpServletRequest request)
	{
		//retrieve duration from user
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = Integer.parseInt(request.getParameter("id"));
		Duration duration = durationDAO.get(id);
		Date start = duration.getStartDate();
		Date end = duration.getEndDate();
		
		//get list that only includes entries between start and end
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser, start, end);
	    
	    return budgetList;
	}
	
	public String getTotal()
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    String stringTotal = String.format("%.2f", total);
	    return stringTotal;
	}
}
