package com.redhat.seginf.ui.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManager {

	private static MailManager instance;
	private Session session;

	public static MailManager getInstance() {
		if (instance == null) {
			instance = new MailManager();
		}
		return instance;
	}

	public MailManager() {
		Properties props = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		// TLS si esta disponible
		props.setProperty("mail.smtp.starttls.enable", "true");
		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port", "587");
		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");
		// Nombre del usuario
		props.setProperty("mail.smtp.user", "adriel.paredes@gmail.com");

		// Get session
		session = Session.getDefaultInstance(props);

	}

	public void enviarMail(String mailDestino, String asuntoMail,
			String cuerpoMail) {

		// Define message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("adriel.paredes@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					mailDestino));
			message.setSubject(asuntoMail);
			message.setText(cuerpoMail);

			Transport transport = session.getTransport("smtp");
			transport.connect("adriel.paredes@gmail.com", "84212421!");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (MessagingException e) {
			// loggear algo
			System.out.println("ERROR AL INTENTAR DE ENVIAR MAIL");
		}

	}

}
