package com.supinfo.supcommerce.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Mailer {

	private final static Logger LOGGER = LoggerFactory.getLogger(Mailer.class);
	
	private final static String DEFAULT_SENDER_ADDRESS = "contact@supcommerce.com";
	private final static String DEFAULT_SMTP_SERVER = "localhost";
	
	private final static Mailer INSTANCE = new Mailer();
	
	public static Mailer getInstance() {
		return INSTANCE;
	}
	
	private InternetAddress defaultSender;
	
	private Mailer() {
		try {
			defaultSender = new InternetAddress(DEFAULT_SENDER_ADDRESS);
		} catch (AddressException e) {
			e.printStackTrace();
		}
	}

	public boolean sendMail(MailInfo mail, Address[] recipients) {
		boolean result = false;

		try {
			Properties props = new Properties();
		    props.put("mail.smtp.host", DEFAULT_SMTP_SERVER);
		    
			Session session = Session.getDefaultInstance(props);
			Transport.send(buildMessage(session, mail, recipients));
			result = true;
		} catch (AddressException e) {
			LOGGER.error("Mail not sent (Subject: " + mail.getSubject() + ".", e);
		} catch (MessagingException e) {
			LOGGER.error("Mail not sent (Subject: " + mail.getSubject() + ".", e);
		}

		return result;
	}

	private Message buildMessage(Session session, MailInfo mail, Address[] recipients) throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(defaultSender);
		message.setRecipients(Message.RecipientType.TO, recipients);
		message.setSubject(mail.getSubject());
		message.setContent(mail.getContent(), "text/html");
		return message;
	}

}
