package com.budgetapplication.controller;

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
import com.budgetapplication.model.BudgetEntry;

/*
 * Controller that handles the BudgetEntry. This maps appropriate
 * URLs while calling functions from BudgetEntryDAOImpl in order
 * to create the CRUD functionality.
 */
@Controller 
public class EntryController 
{
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	
	//view that displays a table with the current user's budget entries
	@RequestMapping(value="/entries")
	public ModelAndView listBudget()
	{
	    return new ModelAndView("entries", "total", getTotal());
	}
	
	//view that displays a table with the all budget entries
	@RequestMapping(value="/modifyBudget")
	public ModelAndView allBudgets()
	{
	    return new ModelAndView("modifyBudget", "total", getTotal());
	}

	//gets a list of entries for the user signed in
	@RequestMapping(value="/budgetEntries", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetEntry> getList()
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the list() function in the DAO implementation for current user
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser);
	    
	    return budgetList;
	}
	
	//gets a list of all budget entries, for admin use
	@RequestMapping(value="/allEntries", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetEntry> getAllList()
	{
		//calls the list() function in the DAO implementation
	    List<BudgetEntry> budgetList = budgetEntryDAO.list();
	    
	    return budgetList;
	}
	
	//save changes to an entry
	@RequestMapping(value = "/addEntry", method = RequestMethod.POST)
	public ModelAndView addBudgetEntry(@ModelAttribute BudgetEntry entry)
	{
		//get username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//automatically set username for non-admin insertion
		entry.setInsertedBy(currentUser);
		entry.setUsername(currentUser);
		
		//insert into table
	    budgetEntryDAO.insert(entry);
	    return new ModelAndView("redirect:/");
	}
	
	//save changes to an entry from admin view
	@RequestMapping(value = "/adminAddEntry", method = RequestMethod.POST)
	public ModelAndView adminAddEntry(@ModelAttribute BudgetEntry entry)
	{
		//get username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		entry.setInsertedBy(currentUser);
		
		//insert into table
	    budgetEntryDAO.insert(entry);
	    return new ModelAndView("redirect:/");
	}
	
	//delete an entry from the table
	@RequestMapping(value = "/deleteEntry", method = RequestMethod.POST)
	public ModelAndView deleteBudgetEntry(HttpServletRequest request)
	{
	    int entryId = Integer.parseInt(request.getParameter("id"));
	    budgetEntryDAO.delete(entryId);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing entry in the table from admin view
	@RequestMapping(value = "/adminEditEntry", method = RequestMethod.GET)
	public ModelAndView adminEditEntry(HttpServletRequest request) 
	{
		//retrieve the existing entry for the given id
	    int entryId = Integer.parseInt(request.getParameter("id"));
	    BudgetEntry entry = budgetEntryDAO.get(entryId);
	    
	    //modify the variables for the entry
	    entry.setUsername(request.getParameter("username"));
	    entry.setAmount(Double.parseDouble(request.getParameter("amount")));
	    entry.setCategory(request.getParameter("category"));
	    
	    //update entry
	    budgetEntryDAO.update(entry);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing entry in the table
	@RequestMapping(value = "/editEntry", method = RequestMethod.GET)
	public ModelAndView editBudgetEntry(HttpServletRequest request) 
	{
		//retrieve the existing entry for the given id
	    int entryId = Integer.parseInt(request.getParameter("id"));
	    BudgetEntry entry = budgetEntryDAO.get(entryId);
	    
	    //modify the variables for the entry
	    entry.setAmount(Double.parseDouble(request.getParameter("amount")));
	    entry.setCategory(request.getParameter("category"));
	    
	    //update entry
	    budgetEntryDAO.update(entry);
	    return new ModelAndView("redirect:/");
	}
	
	public String getTotal()
	{
		//store current username
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the getTotal() function which obtains the total spent by this user
	    double total = budgetEntryDAO.getTotal(currentUser);
	    String stringTotal = String.format("%.2f", total);
	    return stringTotal;
	}
}
