package com.budgetapplication.serviceimpl;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.budgetapplication.service.MailService;

/*
 * Implements operations for the MailService class.
 * Allows the sending of an email (configuration
 * in Microsoft Outlook was necessary).
 */
public class MailServiceImpl implements MailService
{
	
	private MailSender mailSender;

	@Override
	public void setMailSender(MailSender mailSender) 
	{
		this.mailSender = mailSender;
	}

	@Override
	public void sendMail(String from, String to, String subject, String msg) 
	{
		//create message and apply the parameters
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		
		//send email
		mailSender.send(message);
	}
}
