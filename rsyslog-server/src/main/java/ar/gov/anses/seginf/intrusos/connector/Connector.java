package ar.gov.anses.seginf.intrusos.connector;

import ar.gov.anses.seginf.intrusos.convert.SyslogMessage;

public interface Connector {

	public SyslogMessage parseMessage(byte[] bytes);

	public String toString(SyslogMessage message);
	
	public SyslogMessage parseMessageIfMine(byte[] bytes);

}