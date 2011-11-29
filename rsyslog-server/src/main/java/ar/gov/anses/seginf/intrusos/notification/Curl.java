package ar.gov.anses.seginf.intrusos.notification;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Curl {

	public void invoke(String completeUrl) {
		try {

			HttpURLConnection con = (HttpURLConnection) new URL(completeUrl)
					.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.connect();

			//esta cosa horrible que esta aca es para que funcione porque necesita leer del GET;
			int c;
			while(( c = con.getInputStream().read()) != -1 ) System.out.print(c);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
