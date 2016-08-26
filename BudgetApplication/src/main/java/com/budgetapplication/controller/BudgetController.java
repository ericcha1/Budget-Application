package com.budgetapplication.controller;

import java.io.IOException;
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
 * Controller that handles the Limit. This maps appropriate
 * URLs while calling functions from LimitDAOImpl in order
 * to create the CRUD functionality.
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
	public ModelAndView addBudget(@ModelAttribute Duration duration)
	{
		//store the username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//set username for current user
		duration.setInsertedBy(currentUser);
		duration.setUsername(currentUser);
	    int id = durationDAO.insert(duration);
	    User user = userDAO.get(currentUser);
	    user.setDurationId(id);
	    userDAO.updateDuration(user);
	    return new ModelAndView("redirect:/");
	}
	
	//URL for the xml form of the data, needed for ajax call
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
	
	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/checkLimit", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkLimit(HttpServletRequest request)
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = userDAO.get(currentUser).getDurationId();
		String category = request.getParameter("category");
		double limit = limitDAO.get(currentUser, category).getAmount();
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		List<BudgetEntry> entries = getCatEntries(currentUser, id);
		double total = 0;
		for (int i = 0; i < entries.size(); i++)
		{
			if (entries.get(i).getCategory().equals(category))
				{ total += entries.get(i).getAmount(); }
		}
		
	    return total + amount < limit;
	}
	
	public List<BudgetEntry> getCatEntries(String username, int durationId)
	{
		String currentUser = username;
		int id = durationId;
		Duration duration = durationDAO.get(id);
		Date start = duration.getStartDate();
		Date end = duration.getEndDate();
		
		//calls the list() function in the DAO implementation
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser, start, end);
	    
	    return budgetList;
	}
	
	//check if the user has an active budget
	@RequestMapping(value="/activeBudget", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkActive(HttpServletRequest request)
	{
		//store the username of the user currently signed in
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
		
		if(currentDate.compareTo(start) < 0 || currentDate.compareTo(end) >= 0)
			return false;
		else
			return true;
	}
	
	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/allLimits", method = RequestMethod.GET)
	@ResponseBody
	public List<Limit> getAllLimits() throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<Limit> budgetList = limitDAO.list();
	    
	    return budgetList;
	}
	
	//save changes to an limit
	@RequestMapping(value = "/addLimit", method = RequestMethod.POST)
	public ModelAndView addLimit(@ModelAttribute Limit limit)
	{
		//store the username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//set username for current user
		limit.setInsertedBy(currentUser);
		limit.setUsername(currentUser);
	    limitDAO.insert(limit);
	    return new ModelAndView("redirect:/");
	}
	
	//delete an limit from the table
	@RequestMapping(value = "/deleteLimit", method = RequestMethod.POST)
	public ModelAndView deleteLimit(HttpServletRequest request)
	{
	    int limitId = Integer.parseInt(request.getParameter("id"));
	    limitDAO.delete(limitId);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing limit in the table
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
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    String stringTotal = String.format("%.2f", total);
	    return stringTotal;
	}
}
