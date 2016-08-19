package com.budgetapplication.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.budgetapplication.dao.CategoryDAO;
import com.budgetapplication.model.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 
/**
 * An implementation of the CategoryDAO interface.
 * Allows for modification, insertion, or deletion
 * of categories in the table.
 */
public class CategoryDAOImpl implements CategoryDAO 
{
 
    private JdbcTemplate jdbcTemplate;
    
    //constructor, initializes jdbcTemplate
    public CategoryDAOImpl(DataSource dataSource) 
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    //insert new category into table
    @Override
    public void insert(Category category) 
    {
        String sql = "INSERT INTO category_table (category, insertedBy) VALUES (?, ?)";
        jdbcTemplate.update(sql, category.getCategory(), category.getInsertedBy());
    }
    
    //update an existing category
    public void update(String initialCategory, String newCategory)
    {
        String sql = "UPDATE category_table SET category=? WHERE category=?";
        jdbcTemplate.update(sql, newCategory, initialCategory); 
    }
 
    //delete a category
    @Override
    public void delete(String category) 
    {
    	String sql = "DELETE FROM category_table WHERE category=?";
        jdbcTemplate.update(sql, category);
    }
 
    //get a category
    @Override
    public Category get(String category) 
    {
    	String sql = "SELECT * FROM category_table WHERE category='" + category + "'";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Category>() 
        {
     
            @Override
            public Category extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) 
                {
                    Category category = new Category(rs.getString("category"));  
                    category.setInsertedBy(rs.getString("insertedBy"));
                    //getDate().toString to exclude the time
                    category.setInsertedOn(rs.getString("insertedOn"));
                    return category;
                }
     
                return null;
            }
        });
    }
    
    //get a list of all the categories in the table
    @Override
    public List<Category> list() 
    {
    	String sql = "SELECT * FROM category_table";

        List<Category> categoryList = jdbcTemplate.query(sql, new RowMapper<Category>() 
        {
        	@Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException 
            {
                Category category = new Category(rs.getString("category"));
                category.setInsertedBy(rs.getString("insertedBy"));
                category.setInsertedOn(rs.getDate("insertedOn").toString());
                return category;
            }
        });
     
        return categoryList;
    }
}