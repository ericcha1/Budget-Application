package com.budgetapplication.config;

import javax.sql.DataSource;
import com.budgetapplication.dao.*;
import com.budgetapplication.daoimpl.*;
import com.budgetapplication.serviceimpl.MailServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
/*
 * Spring MVC Configuration class
 */
@Configuration
@ComponentScan(basePackages="com.budgetapplication")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{
	
	//setting the location of the views - the jsp pages
    @Bean
    public ViewResolver getViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
     
    //setting the location of the resources - css and js pages to be imported into the jsp
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
 
    //returns configured DataSource Bean
    @Bean
    public DataSource getDataSource() 
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        //connects the mySQL database called dbase which has a user table and a budget table
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbase");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
         
        return dataSource;
    }
    
    //returns the DAO implementation of the models
    //these beans are injected in the controller classes
    @Bean
    public UserDAO getUserDAO() 
    {
        return new UserDAOImpl(getDataSource());
    }
    
    @Bean
    public BudgetEntryDAO getBudgetEntryDAO() 
    {
        return new BudgetEntryDAOImpl(getDataSource());
    }
    
    @Bean
    public CategoryDAO getCategoryDAO() 
    {
        return new CategoryDAOImpl(getDataSource());
    }
    
    @Bean
    public DurationDAO getDurationDAO() 
    {
        return new DurationDAOImpl(getDataSource());
    }
    
    @Bean
    public LimitDAO getLimitDAO() 
    {
        return new LimitDAOImpl(getDataSource());
    }
}