package com.budgetapplication.service;

import org.springframework.mail.MailSender;

/*
 * Declares the MailService interface. This will
 * allow for the sending of an email (configuration
 * in Microsoft Outlook was necessary).
 */
public interface MailService 
{
	/**
	 * Sets the MailSender.
	 * @param mailSender
	 */
	public void setMailSender(MailSender mailSender);

	/**
	 * Sends an email with the given parameters.
	 * @param from
	 * @param to
	 * @param subject
	 * @param msg
	 */
	public void sendMail(String from, String to, String subject, String msg);
}
