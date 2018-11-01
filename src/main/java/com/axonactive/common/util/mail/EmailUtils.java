/**
 * 
 */
package com.axonactive.common.util.mail;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * @author nvmuon
 *
 */
public class EmailUtils {

	private EmailUtils() {

	}

	/**
	 * Utility method to send simple HTML email using SMTP protocol
	 * @param smtpHost the smtp host
	 * @param account contains username and password to do authentication to the smtp server
	 * @param recipients list of recipient addresses
	 * @param subject subject of the mail
	 * @param body the HTML content of the mail
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendMail(String smtpHost, EmailAccount account, String[] recipients, String subject, String body) 
			throws MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "587");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.connectiontimeout", "2000");
		props.put("mail.smtp.timeout", "2000");

		Session session = Session.getInstance(props, new Authenticator() {
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account.getUsername(), account.getPassword());
			}
		});

		Message message = createMessage(session, account.getEmailAddress(), recipients, subject, body);
		Transport.send(message);
	}

	/**
	 * Create an email with HTML format 
	 * @param session
	 * @param from the email address of the sender
	 * @param recipients list of recipient addresses
	 * @param subject subject of the mail
	 * @param body the HTML content of the mail
	 * @return the MineMessage
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static Message createMessage(Session session, String from, String[] recipients, String subject, String body)
			throws MessagingException {

		Message message = new MimeMessage(session);
		message.addHeader("Content-type", "text/HTML; charset=UTF-8");
		message.addHeader("format", "flowed");
		message.addHeader("Content-Transfer-Encoding", "8bit");

		message.setFrom(new InternetAddress(from));

		Address[] addresses = new Address[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addresses[i] = new InternetAddress(recipients[i]);
		}
		message.addRecipients(Message.RecipientType.TO, addresses);

		message.setSubject(subject);
		message.setContent(body, "text/html; charset=utf-8");

		return message;
	}

	/**
	 * Build mail body from a template and map of values using Velocity
	 * @param mailTemplate the path to the mail template
	 * @param values the parameterized values for the template
	 * @return the body of the mail
	 */
	public static String buildBody(String mailTemplate, Map<String, Object> values) {

		VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
		ve.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		ve.init();

		VelocityContext context = new VelocityContext();
		values.forEach((k, v) -> context.put(k, v));
		StringWriter sw = new StringWriter();
		Template t = ve.getTemplate(mailTemplate, "UTF-8");
		t.merge(context, sw);
		return sw.toString();
	}
}
