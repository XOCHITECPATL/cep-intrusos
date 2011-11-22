package ar.gov.anses.seginf.intrusos.parser;

import ar.gov.anses.seginf.intrusos.SyslogEvent;
import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;

public abstract class SyslogContentParser {

	public abstract SyslogEvent parse(SyslogRawMessage syslogRawMessage);

	protected SyslogEvent buildSyslogEvent(SyslogRawMessage syslogRawMessage) {
		SyslogEvent event = new SyslogEvent();

		event.setFacility(syslogRawMessage.getFacility());
		event.setHostname(syslogRawMessage.getHostname());
		event.setLocalAddress(syslogRawMessage.getLocalAddress());
		event.setRemoteAddress(syslogRawMessage.getRemoteAddress());
		event.setSeverity(syslogRawMessage.getSeverity());

		return event;
	}

}