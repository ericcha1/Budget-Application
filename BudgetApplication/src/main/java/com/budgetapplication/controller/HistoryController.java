package com.budgetapplication.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.budgetapplication.dao.BudgetEntryDAO;
import com.budgetapplication.dao.DurationDAO;
import com.budgetapplication.model.BudgetEntry;
import com.budgetapplication.model.Duration;

/*
 * Controller that handles the BudgetEntry. This maps appropriate
 * URLs while calling functions from BudgetEntryDAOImpl in order
 * to create the CRUD functionality.
 */
@Controller 
public class HistoryController 
{
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	
	@Autowired
	private DurationDAO durationDAO;
	
	//goes to a view that displays a table with the current user's budget entries
	@RequestMapping(value="/history")
	public ModelAndView showHistory() throws IOException
	{
	    return new ModelAndView("history", "total", getTotal());
	}
	
	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/dates", method = RequestMethod.GET)
	@ResponseBody
	public List<Duration> getDates() throws IOException
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the list() function in the DAO implementation
	    List<Duration> durationList = durationDAO.list(currentUser);
	    
	    return durationList;
	}
	
	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/dateEntries", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetEntry> getEntries(HttpServletRequest request) throws IOException
	{
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = Integer.parseInt(request.getParameter("id"));
		Duration duration = durationDAO.get(id);
		Date start = duration.getStartDate();
		Date end = duration.getEndDate();
		
		//calls the list() function in the DAO implementation
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser, start, end);
	    
	    return budgetList;
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
