package ua.kiev.prog.security;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	final static String username = "documsservice@gmail.com";
	final static String password = "000999888111";
	
	public static void sendEmailMessage(String eMailFrom, String eMailTo, String subject, String text){
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));
		/*Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });*/
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(eMailFrom));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(eMailTo));
			message.setSubject(subject);
			message.setText(text);
 
			Transport.send(message);
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
		
		
		
	
	
	
	
	

}
