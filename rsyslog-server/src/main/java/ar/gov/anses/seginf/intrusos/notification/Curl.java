package ar.gov.anses.seginf.intrusos.notification;

import java.io.IOException;

public class Curl {
	
	public void invoke(String url){
		
		try {
			Runtime.getRuntime().exec("curl "+ url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
