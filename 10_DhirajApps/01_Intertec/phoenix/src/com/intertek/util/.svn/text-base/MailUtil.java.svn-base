package com.intertek.util;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

import com.intertek.locator.ServiceLocator;

public class MailUtil{
	protected MailUtil(){
		
	}
	
	protected static JavaMailSender sender = (JavaMailSender) ServiceLocator.getInstance().getBean("sender");
	
	public static void sendMail(String from, String to, String subject, String msg) throws AddressException, MessagingException{
		MimeMessage message=sender.createMimeMessage();
		message.setFrom(new InternetAddress(from));
		message.setRecipients(RecipientType.TO, parseAddresses(to));
		message.setSubject(subject);
		message.setContent(msg, "text/html");
		sender.send(message);
	}
	
	protected static Address[] parseAddresses(String to) throws AddressException{
		String[] tos=to.split(",");
		Address[] address=new InternetAddress[tos.length];
		for(int i=0; i<tos.length; i++){
			address[i]=new InternetAddress(tos[i]);
		}
		return address;
	}
}
