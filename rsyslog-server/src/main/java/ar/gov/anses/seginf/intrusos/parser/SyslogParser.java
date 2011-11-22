package ar.gov.anses.seginf.intrusos.parser;

import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;

public class SyslogParser {

	public SyslogParser() {
	}
	
	public void parse(SyslogRawMessage syslogRawMessage){
		
		String content = syslogRawMessage.getLogMessage();
		
		SyslogContentParser parser = this.instantianteSyslogContentParser(content);
		parser.parser(syslogRawMessage);
		
	}

	private SyslogContentParser instantianteSyslogContentParser(String content) {
		return null;
	}

	
}
