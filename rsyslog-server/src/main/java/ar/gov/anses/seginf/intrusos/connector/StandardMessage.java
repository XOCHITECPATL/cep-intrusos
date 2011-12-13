package ar.gov.anses.seginf.intrusos.connector;

import java.util.Date;

public interface StandardMessage {

	public abstract String toString();

	public abstract void setValid(boolean valid);

	public abstract boolean isValid();

	public abstract void setExecuted(boolean executed);

	public abstract boolean isExecuted();

	public abstract void setCreatedAt(Date createdAt);

	public abstract Date getCreatedAt();

	public abstract void setId(long id);

	public abstract long getId();

	public abstract void setHostname(String hostname);

	public abstract String getHostname();

	public abstract void setRemoteAddress(String remoteAddress);

	public abstract String getRemoteAddress();

	public abstract void setSeverity(String severity);

	public abstract String getSeverity();

	public abstract void setTimestamp(Date timestamp);

	public abstract Date getTimestamp();

	public abstract void setFacility(String facility);

	public abstract String getFacility();

	public abstract void setLocalAddress(String localAddress);

	public abstract String getLocalAddress();

	public abstract void setLogMessage(String logMessage);

	public abstract String getLogMessage();

}
