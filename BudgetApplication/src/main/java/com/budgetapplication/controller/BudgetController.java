package com.budgetapplication.controller;

import java.io.IOException;
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
public class BudgetController 
{
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	
	//goes to a view that displays a table with the current user's budget entries
	@RequestMapping(value="/entries")
	public ModelAndView listBudget() throws IOException
	{
	    return new ModelAndView("entries", "total", getTotal());
	}
	
	//goes to a view that displays a table with the current user's budget entries
	@RequestMapping(value="/modifyBudget")
	public ModelAndView allBudgets() throws IOException
	{
	    return new ModelAndView("modifyBudget", "total", getTotal());
	}

	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/budgetEntries", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetEntry> getList() throws IOException
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the list() function in the DAO implementation
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser);
	    
	    return budgetList;
	}
	
	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/allEntries", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetEntry> getAllList() throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<BudgetEntry> budgetList = budgetEntryDAO.list();
	    
	    return budgetList;
	}
	
	//save changes to an entry
	@RequestMapping(value = "/addEntry", method = RequestMethod.POST)
	public ModelAndView addBudgetEntry(@ModelAttribute BudgetEntry entry) throws IOException 
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//automatically set username for non-admin insertion
		entry.setInsertedBy(currentUser);
		entry.setUsername(currentUser);
	    budgetEntryDAO.insert(entry);
	    return new ModelAndView("redirect:/", "total", getTotal());
	}
	
	//save changes to an entry from admin view
	@RequestMapping(value = "/adminAddEntry", method = RequestMethod.POST)
	public ModelAndView adminAddEntry(@ModelAttribute BudgetEntry entry) throws IOException 
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		entry.setInsertedBy(currentUser);
	    budgetEntryDAO.insert(entry);
	    return new ModelAndView("redirect:/", "total", getTotal());
	}
	
	//delete an entry from the table
	@RequestMapping(value = "/deleteEntry", method = RequestMethod.POST)
	public ModelAndView deleteBudgetEntry(HttpServletRequest request) throws IOException
	{
	    int entryId = Integer.parseInt(request.getParameter("id"));
	    budgetEntryDAO.delete(entryId);
	    return new ModelAndView("redirect:/", "total", getTotal());
	}
	
	//edit an existing entry in the table from admin view
	@RequestMapping(value = "/adminEditEntry", method = RequestMethod.GET)
	public ModelAndView adminEditEntry(HttpServletRequest request) throws IOException 
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
	    return new ModelAndView("redirect:/", "total", getTotal());
	}
	
	//edit an existing entry in the table
	@RequestMapping(value = "/editEntry", method = RequestMethod.GET)
	public ModelAndView editBudgetEntry(HttpServletRequest request) throws IOException 
	{
		//retrieve the existing entry for the given id
	    int entryId = Integer.parseInt(request.getParameter("id"));
	    BudgetEntry entry = budgetEntryDAO.get(entryId);
	    
	    //modify the variables for the entry
	    //automatically set username for non-admin edits
	    entry.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	    entry.setAmount(Double.parseDouble(request.getParameter("amount")));
	    entry.setCategory(request.getParameter("category"));
	    
	    //update entry
	    budgetEntryDAO.update(entry);
	    return new ModelAndView("redirect:/", "total", getTotal());
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
