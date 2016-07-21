//package com.budgetapplication.config;
//
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	DataSource dataSource;
//	
//	@Autowired
//	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		
//	  auth.jdbcAuthentication().dataSource(dataSource)
//		.usersByUsernameQuery(
//			"SELECT username, password, enabled FROM user_table WHERE username=?")
//		.authoritiesByUsernameQuery(
//			"SELECT username, role FROM user_table WHERE username=?");
//	}	
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//	  http.authorizeRequests()
//		.antMatchers("/*").access("hasRole('ROLE_USER')")
//		.antMatchers("/home**").access("hasRole('ROLE_USER')")
//		.and()
//		  .formLogin()
//		  .usernameParameter("username").passwordParameter("password")
//	  		.defaultSuccessUrl("/home");
////	  		.and();
//		  //.loginPage("/login").failureUrl("/login?error")
//
////		.and()
////		  .logout().logoutSuccessUrl("/login?logout")
////		.and()
////		  .exceptionHandling().accessDeniedPage("/403")
////		.and()
////		  .csrf();
//	}
//}