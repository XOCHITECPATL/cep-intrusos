package ar.gov.anses.seginf.intrusos.convert;

import java.util.Date;

import ar.gov.anses.seginf.intrusos.connector.StandardMessage;

public class NullSyslogMessage implements StandardMessage {

	public void setValid(boolean valid) {
		// TODO Auto-generated method stub

	}

	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setExecuted(boolean executed) {
		// TODO Auto-generated method stub

	}

	public boolean isExecuted() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setCreatedAt(Date createdAt) {
		// TODO Auto-generated method stub

	}

	public Date getCreatedAt() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setId(long id) {
		// TODO Auto-generated method stub

	}

	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setHostname(String hostname) {
		// TODO Auto-generated method stub

	}

	public String getHostname() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setRemoteAddress(String remoteAddress) {
		// TODO Auto-generated method stub

	}

	public String getRemoteAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSeverity(String severity) {
		// TODO Auto-generated method stub

	}

	public String getSeverity() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTimestamp(Date timestamp) {
		// TODO Auto-generated method stub

	}

	public Date getTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFacility(String facility) {
		// TODO Auto-generated method stub

	}

	public String getFacility() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLocalAddress(String localAddress) {
		// TODO Auto-generated method stub

	}

	public String getLocalAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogMessage(String logMessage) {
		// TODO Auto-generated method stub

	}

	public String getLogMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
