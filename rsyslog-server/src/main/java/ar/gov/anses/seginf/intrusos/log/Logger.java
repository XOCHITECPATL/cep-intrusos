package ar.gov.anses.seginf.intrusos.log;

public class Logger {

	public static void debug(String message, Class clazz) {
		System.out.println("[DEBUG] {" + clazz.getSimpleName() + "} : "
				+ message);
	}

}
