package ar.gov.anses.seginf.intrusos.parser;

import ar.gov.anses.seginf.intrusos.SyslogEvent;
import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;

public abstract class SyslogContentParser {

	public abstract SyslogEvent parser(SyslogRawMessage syslogRawMessage);

	protected SyslogEvent buildSyslogEvent(SyslogRawMessage syslogRawMessage) {
		return null;
	}

}