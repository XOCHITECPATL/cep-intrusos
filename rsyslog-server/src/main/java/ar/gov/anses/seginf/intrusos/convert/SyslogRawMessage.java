package ar.gov.anses.seginf.intrusos.convert;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SyslogRawMessage {
	
	@Id
	private long id;
	private String facility;
	private String severity;
	private String remoteAddress;
	private String localAddress;
	private String hostname;
	private String logMessage;

	private Date timestamp;

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

//	@Override
//	public String toString() {
//		return "SyslogMessage{<facility>" + facility.toString()+"</facility>" 
//				+ ", severity=" + severity + ", remoteAddress='"
//				+ remoteAddress + "'" + ", localAddress='" + localAddress + "'" + ", hostname='" + hostname + "'" + ", messageTime=" + timestamp
//				+ "<content>"+this.logMessage.trim()+"</content>"  
//				+ '}';
//	}
}
