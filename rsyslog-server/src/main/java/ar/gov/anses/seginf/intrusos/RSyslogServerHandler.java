package ar.gov.anses.seginf.intrusos;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import ar.gov.anses.seginf.intrusos.convert.Rfc3164SyslogConverter;
import ar.gov.anses.seginf.intrusos.convert.SyslogMessage;
import ar.gov.anses.seginf.intrusos.persistence.Repository;

public class RSyslogServerHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = Logger
			.getLogger(RSyslogServerHandler.class.getName());

	private final AtomicLong transferredBytes = new AtomicLong();

	public RSyslogServerHandler() {
	}

	public long getTransferredBytes() {
		return transferredBytes.get();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx,
			MessageEvent messageEvent) {

		
		byte[] bytes = getMessage(messageEvent);

		SyslogMessage syslogMessage = this.createMessage(bytes);

		CEPEngine.getInstance().getWorkingMemoryEntryPoint().insert(syslogMessage);

		CEPEngine.getInstance().fireAllRules();

		Repository.getInstance().save(syslogMessage);

	}

	private byte[] getMessage(MessageEvent e) {
		return ((ChannelBuffer) e.getMessage()).array();
	}

	/**
	 * Convierte el conjunto de bytes que viene del syslog a algo entendible por
	 * humanos
	 * 
	 * @param bytes
	 * @return
	 */
	private SyslogMessage createMessage(byte[] bytes) {
		SyslogMessage syslogMessage = Rfc3164SyslogConverter
				.parseMessage(bytes);
		syslogMessage.setCreatedAt(new Date());
		return syslogMessage;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				e.getCause());
		e.getChannel().close();
	}
}