package ar.gov.anses.seginf.intrusos.parser;

import org.junit.Test;

import ar.gov.anses.seginf.intrusos.notification.Curl;

public class NotificationTest {

	@Test
	public void testCurl(){
		
		Curl curl = new Curl();
		
			

		curl.invoke("http://192.168.90.1/anses/notify.php?via=sms&action=new&dst=005491160172120&msg=sgtpepper&cod=8669");
		
	}
	
}
