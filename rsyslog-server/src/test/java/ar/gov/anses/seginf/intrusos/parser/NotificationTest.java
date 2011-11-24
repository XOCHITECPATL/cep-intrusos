package ar.gov.anses.seginf.intrusos.parser;

import org.junit.Test;

import ar.gov.anses.seginf.intrusos.notification.Curl;

public class NotificationTest {

	@Test
	public void testCurl(){
		
		Curl curl = new Curl();
		
		curl.invoke("http://10.192.54.85/anses/notify.php?via=sms&action=new&dst=005491160172120&msg=ladetuhermana&cod=6754");
		
	}
	
}
