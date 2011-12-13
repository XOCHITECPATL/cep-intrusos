package ar.gov.anses.seginf.intrusos.convert;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.gov.anses.seginf.intrusos.connector.StandardMessage;

@Entity
public class SyslogMessage implements StandardMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String facility;
	private String severity;
	private String remoteAddress;
	private String localAddress;
	private String hostname;
	private String logMessage;
	private Date timestamp;
	private Date createdAt;

	private boolean executed = false;
	private boolean valid = true;

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isExecuted() {
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

	@Override
	public String toString() {
		return "SyslogMessage{<facility>" + facility.toString() + "</facility>"
				+ ", severity=" + severity + ", remoteAddress='"
				+ remoteAddress + "'" + ", localAddress='" + localAddress + "'"
				+ ", hostname='" + hostname + "'" + ", messageTime="
				+ timestamp + "<content>" + this.logMessage.trim()
				+ "</content>" + '}';
	}
}
