package com.budgetapplication.dao;

import java.util.List;
import com.budgetapplication.model.Category;
 
/*
 * Declares DAO operations for the Category model
 * through an interface. Allows for interaction
 * with the category table in the database.
 */
public interface CategoryDAO 
{
	/**
	 * Insert a new category into the table.
	 * @param category
	 */
    public void insert(Category category);
    
    /**
	 * Update an existing category into the table.
	 * @param initialCategory, newCategory
	 */
    public void update(String initialCategory, String newCategory);
     
    /**
     * Remove a category from the category table.
     * @param category
     */
    public void delete(String category);
    
    /**
     * Get a category.
     * @param category
     * @return Category
     */
    public Category get(String category);
    
    /**
     * Get a list of all of the categories.
     * @return List<Category>
     */
    public List<Category> list();
}