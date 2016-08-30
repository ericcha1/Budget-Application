package com.budgetapplication.controller;

import java.sql.Date;
import java.util.Calendar;
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
import com.budgetapplication.dao.LimitDAO;
import com.budgetapplication.dao.UserDAO;
import com.budgetapplication.model.BudgetEntry;
import com.budgetapplication.model.Duration;
import com.budgetapplication.model.Limit;
import com.budgetapplication.model.User;

/*
 * Controller that handles the Limit and creates a budget.
 * Contains most of the interaction between limits and
 * budgets/budget entries.
 */
@Controller 
public class BudgetController 
{
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	@Autowired
    private LimitDAO limitDAO;
	@Autowired
    private DurationDAO durationDAO;
	@Autowired
    private UserDAO userDAO;
	
	//goes to a view that allows a user to start a budget and add limits
	@RequestMapping(value="/budget")
	public ModelAndView listBudget()
	{
	    return new ModelAndView("budget", "total", getTotal());
	}
	
	//save changes to an limit
	@RequestMapping(value = "/startBudget", method = RequestMethod.POST)
	public ModelAndView addBudget(HttpServletRequest request)
	{
		//store the username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//set fields
		Duration duration = new Duration();
		duration.setInsertedBy(currentUser);
		duration.setUsername(currentUser);
		duration.setStartDate(java.sql.Date.valueOf(request.getParameter("startDate")));
		duration.setEndDate(java.sql.Date.valueOf(request.getParameter("endDate")));
		
		//insert new duration and fetch the id to store in user
	    int id = durationDAO.insert(duration);
	    User user = userDAO.get(currentUser);
	    user.setDurationId(id);
	    
	    //store current duration in user table
	    userDAO.updateDuration(user);
	    return new ModelAndView("redirect:/");
	}
	
	//list of all budget limits for a user
	@RequestMapping(value="/limits", method = RequestMethod.GET)
	@ResponseBody
	public List<Limit> getList()
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the list() function in the DAO implementation
	    List<Limit> limitList = limitDAO.list(currentUser);
	    
	    return limitList;
	}
	
	//checks if the new entry will break the limit
	@RequestMapping(value="/checkLimit", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkLimit(HttpServletRequest request)
	{
		//get user and duration
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = userDAO.get(currentUser).getDurationId();
		
		//category and amount from ajax call
		String category = request.getParameter("category");
		double limit = limitDAO.get(currentUser, category).getAmount();
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		//get all entries for a user for the current budget
		List<BudgetEntry> entries = getEntries(currentUser, id);
		
		double total = 0;
		for (int i = 0; i < entries.size(); i++)
		{
			//total sum for entries of the same category
			if (entries.get(i).getCategory().equals(category))
				total += entries.get(i).getAmount();
		}
		
	    return total + amount < limit;
	}
	
	//get all entries for the user in a duration
	public List<BudgetEntry> getEntries(String username, int durationId)
	{
		//retrieve duration stored with user
		String currentUser = username;
		int id = durationId;
		Duration duration = durationDAO.get(id);
		
		//list of entries for this duration
		Date start = duration.getStartDate();
		Date end = duration.getEndDate();
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser, start, end);
	    
	    return budgetList;
	}
	
	//check if the user has an active budget
	@RequestMapping(value="/activeBudget", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkActive(HttpServletRequest request)
	{
		//get duration id
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = userDAO.get(currentUser).getDurationId();
		
		//default
		if(id == -1)
			return false;

		//check if today is between the duration
		Duration duration = durationDAO.get(id);
		Date start = duration.getStartDate();
		Date end = duration.getEndDate();
		Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
		
		if(currentDate.before(start) || currentDate.after(end))
			return false;
		else
			return true;
	}
	
	//list of all limits
	@RequestMapping(value="/allLimits", method = RequestMethod.GET)
	@ResponseBody
	public List<Limit> getAllLimits()
	{
		//calls the list() function in the DAO implementation
	    List<Limit> budgetList = limitDAO.list();
	    
	    return budgetList;
	}
	
	//add a limit
	@RequestMapping(value = "/addLimit", method = RequestMethod.POST)
	public ModelAndView addLimit(@ModelAttribute Limit limit)
	{
		//store the username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//set username for current user and insert
		limit.setInsertedBy(currentUser);
		limit.setUsername(currentUser);
	    limitDAO.insert(limit);
	    return new ModelAndView("redirect:/");
	}
	
	//delete limit
	@RequestMapping(value = "/deleteLimit", method = RequestMethod.POST)
	public ModelAndView deleteLimit(HttpServletRequest request)
	{
	    int limitId = Integer.parseInt(request.getParameter("id"));
	    limitDAO.delete(limitId);
	    return new ModelAndView("redirect:/");
	}
	
	//edit existing limit
	@RequestMapping(value = "/editLimit", method = RequestMethod.GET)
	public ModelAndView editLimit(HttpServletRequest request)
	{
		//retrieve the existing limit for the given id
	    int limitId = Integer.parseInt(request.getParameter("id"));
	    Limit limit = limitDAO.get(limitId);
	    
	    //modify the variables for the limit
	    limit.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	    limit.setAmount(Double.parseDouble(request.getParameter("amount")));
	    limit.setCategory(request.getParameter("category"));
	    
	    //update limit
	    limitDAO.update(limit);
	    return new ModelAndView("redirect:/");
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
