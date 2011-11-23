package ar.gov.anses.seginf.intrusos;

import java.util.Date;

import ar.gov.anses.seginf.intrusos.convert.SyslogFacility;
import ar.gov.anses.seginf.intrusos.convert.SyslogSeverity;

public class SyslogEvent {

	private SyslogFacility facility;
	private SyslogSeverity severity;
	private String remoteAddress;
	private String localAddress;
	private String hostname;
	private String logMessage;
	private Date createdAt;
	private String subsystem;
	private String user;
	private boolean executed = false;
	private boolean valid = true;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getSubsystem() {
		return this.returnEmptyIfNull(this.subsystem);
	}

	public void setSubsystem(String subsystem) {
		this.subsystem = subsystem;
	}

	public String getUser() {
		return this.returnEmptyIfNull(user);
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFacility() {
		return String.valueOf(facility);
	}

	public void setFacility(SyslogFacility facility) {
		this.facility = facility;
	}

	public String getSeverity() {
		return String.valueOf(severity);
	}

	public void setSeverity(SyslogSeverity severity) {
		this.severity = severity;
	}

	public String getRemoteAddress() {
		return this.returnEmptyIfNull(remoteAddress);
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getLocalAddress() {
		return this.returnEmptyIfNull(localAddress);
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getHostname() {
		return this.returnEmptyIfNull(hostname);
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getLogMessage() {
		return this.returnEmptyIfNull(logMessage);
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String toString() {
		return this.user;
	}

	public boolean wasExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	private String returnEmptyIfNull(String value) {
		return String.valueOf(value);
	}

}
