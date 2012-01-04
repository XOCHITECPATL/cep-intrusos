package ar.gov.anses.seginf.intrusos.parser;

import org.junit.Test;

import com.redhat.seginf.ui.mail.MailManager;

import ar.gov.anses.seginf.intrusos.notification.Curl;

public class NotificationTest {

	@Test
	public void testMail(){

		MailManager.getInstance().enviarMail("aparedes@preteco.com", "hola", "chau");
		
	}
	
}
