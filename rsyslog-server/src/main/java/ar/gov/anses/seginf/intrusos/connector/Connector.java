package ar.gov.anses.seginf.intrusos.connector;

import ar.gov.anses.seginf.intrusos.convert.SyslogMessage;

public interface Connector {

	public StandardMessage parseMessage(byte[] bytes);

	public String toString(SyslogMessage message);
	
	public StandardMessage parseMessageIfMine(byte[] bytes);

}