package com.budgetapplication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mail.SimpleMailMessage;

import com.budgetapplication.dao.UserDAO;
import com.budgetapplication.model.User;
import com.budgetapplication.serviceimpl.MailServiceImpl;

/*
 * Controller for resetting a user's password.
 */
@Controller
public class PasswordController 
{
	@Autowired
    private UserDAO userDAO;
	
	@Autowired
	private MailServiceImpl mailService;
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView forgotPassword(
			@RequestParam(value = "message", required = false) String message)
	{
		//The message is either empty (default), sends confirmation, 
		//or asks for a valid email address
	    return new ModelAndView("forgotPassword", "message", message);
	}
	
	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	@ResponseBody
	public String sendEmail(HttpServletRequest request)
	{
		//obtain the user to get access to username and password
		String userEmail = request.getParameter("email");
		User user = userDAO.getUserByEmail(userEmail);
		String message = "";
		
	    if (user != null) 
	    {
	    	//create fields for the email
	    	SimpleMailMessage email = new SimpleMailMessage();
			email.setFrom("kcha.eric@gmail.com");
			email.setTo(userEmail);
			String subject = "Budget Application - Password Reset";
			String emailText = "Username: " + user.getUsername() + "\nPassword: " + user.getPassword();
			mailService.sendMail("kcha.eric@gmail.com", userEmail, subject, emailText);
		    
			message = "Email Sent.";
	    }
	    else
	    {
	    	message = "Please enter in a valid email address.";
	    }
	    
		return message; 
	}

}