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
import com.budgetapplication.dao.CategoryDAO;
import com.budgetapplication.model.BudgetEntry;
import com.budgetapplication.model.Category;

/*
 * Controller that handles the Category. Maps appropriate
 * URLs while calling functions from CategoryDAOImpl in order
 * to create CRUD functionality.
 */
@Controller 
public class CategoryController 
{
	@Autowired
    private BudgetEntryDAO budgetEntryDAO;
	@Autowired
    private CategoryDAO categoryDAO;
	
	//goes to a view that displays a table with all of the categories
	@RequestMapping(value="/category")
	public ModelAndView listCategory() throws IOException
	{
	    return new ModelAndView("category", "total", getTotal());
	}
	
	//URL for the xml form of the data, needed for ajax call
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategories() throws IOException
	{
		//calls the list() function in the DAO implementation
	    List<Category> catList = categoryDAO.list();
	    return catList;
	}
	
	//save changes to a category
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute Category cat) throws IOException 
	{
	    categoryDAO.insert(cat);
	    return new ModelAndView("redirect:/", "total", getTotal());
	}
	
	//delete an entry from the table
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	public ModelAndView deleteCategory(HttpServletRequest request) throws IOException
	{
	    String category = request.getParameter("category");
	    categoryDAO.delete(category);
	    return new ModelAndView("redirect:/", "total", getTotal());
	}
	
	//edit an existing entry in the table
	@RequestMapping(value = "/editCategory", method = RequestMethod.GET)
	public ModelAndView editCategory(HttpServletRequest request) throws IOException 
	{
		//retrieve the existing category
		String initialCategory = request.getParameter("initialCategory");
		String newCategory = request.getParameter("category");
	    
	    //update entry
	    categoryDAO.update(initialCategory, newCategory);
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
