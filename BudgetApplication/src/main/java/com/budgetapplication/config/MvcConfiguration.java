package com.budgetapplication.config;
 
import javax.sql.DataSource;
 
import com.budgetapplication.dao.*;
import com.budgetapplication.daoimpl.*;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@ComponentScan(basePackages="com.budgetapplication")
@EnableWebMvc

public class MvcConfiguration extends WebMvcConfigurerAdapter{
 
    @Bean
    public ViewResolver getViewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
     
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
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbase");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
         
        return dataSource;
    }
    
    //returns the DAO implementation
    //this bean is injected in the controller class
    @Bean
    public UserDAO getUserDAO() 
    {
        return new UserDAOImpl(getDataSource());
    }
}