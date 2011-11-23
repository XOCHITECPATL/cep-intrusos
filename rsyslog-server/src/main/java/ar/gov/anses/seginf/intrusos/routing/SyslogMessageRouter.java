package ar.gov.anses.seginf.intrusos.routing;

import ar.gov.anses.seginf.intrusos.convert.SyslogFacility;
import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;
import ar.gov.anses.seginf.intrusos.parser.LinuxSyslogContentParser;
import ar.gov.anses.seginf.intrusos.parser.SyslogContentParser;
import ar.gov.anses.seginf.intrusos.parser.WindowSyslogContentParser;

/**
 * 
 * Esta objeto debe decidir a que parser se le va a entregar el mensaje de
 * Syslog que recibe el sistema
 * 
 * @author aparedes
 * 
 */
public class SyslogMessageRouter {

	public SyslogMessageRouter() {

	}

	public SyslogContentParser route(SyslogRawMessage message) {

		if (message.getFacility().equals(SyslogFacility.LOCAL3))
			return new WindowSyslogContentParser();
		else
			return new LinuxSyslogContentParser();
	}

}
