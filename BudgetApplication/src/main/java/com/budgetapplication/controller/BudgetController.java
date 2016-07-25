package com.budgetapplication.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	
	//goes to a view that displays a table with all of the budget entries
	@RequestMapping(value="/budget")
	public ModelAndView listBudget(ModelAndView model) throws IOException
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the list() function in the DAO implementation
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser);
	    model.addObject("budgetList", budgetList);
	    model.setViewName("budget");
	 
	    return model;
	}

	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/entries", method = RequestMethod.GET)
	@ResponseBody
	public List<BudgetEntry> getList() throws IOException
	{
		//store the username of the user currently signed in
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//calls the list() function in the DAO implementation
	    List<BudgetEntry> budgetList = budgetEntryDAO.list(currentUser);
	    
	    return budgetList;
	}
	
	//add a row or BudgetEntry to the budget table
	@RequestMapping(value = "/newBudgetEntry", method = RequestMethod.GET)
	public ModelAndView newBudgetEntry(ModelAndView model) 
	{
	    BudgetEntry entry = new BudgetEntry();
	    model.addObject("entry", entry);
	    model.setViewName("BudgetForm");
	    return model;
	}
	
	//save changes to an entry
	@RequestMapping(value = "/saveEntry", method = RequestMethod.POST)
	public ModelAndView saveBudgetEntry(@ModelAttribute BudgetEntry entry) 
	{
	    budgetEntryDAO.insertOrUpdate(entry);
	    return new ModelAndView("redirect:/");
	}
	
	//delete an entry from the table
	@RequestMapping(value = "/deleteEntry", method = RequestMethod.GET)
	public ModelAndView deleteBudgetEntry(HttpServletRequest request) 
	{
	    int budgetId = Integer.parseInt(request.getParameter("id"));
	    budgetEntryDAO.delete(budgetId);
	    return new ModelAndView("redirect:/");
	}
	
	//edit an existing entry in the table
	@RequestMapping(value = "/editEntry", method = RequestMethod.GET)
	public ModelAndView editBudgetEntry(HttpServletRequest request) 
	{
	    int entryId = Integer.parseInt(request.getParameter("id"));
	    BudgetEntry entry = budgetEntryDAO.get(entryId);
	    ModelAndView model = new ModelAndView("BudgetForm");
	    model.addObject("entry", entry);
	 
	    return model;
	}
}
