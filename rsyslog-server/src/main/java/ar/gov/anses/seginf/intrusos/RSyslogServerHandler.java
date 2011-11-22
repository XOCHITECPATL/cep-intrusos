package ar.gov.anses.seginf.intrusos;

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
import ar.gov.anses.seginf.intrusos.convert.SyslogRawMessage;
import ar.gov.anses.seginf.intrusos.parser.LinuxSyslogContentParser;
import ar.gov.anses.seginf.intrusos.parser.SyslogContentParser;
import ar.gov.anses.seginf.intrusos.routing.SyslogMessageRouter;

public class RSyslogServerHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = Logger
			.getLogger(RSyslogServerHandler.class.getName());

	private final AtomicLong transferredBytes = new AtomicLong();

	private WorkingMemoryEntryPoint cepEntryPoint;

	public RSyslogServerHandler(WorkingMemoryEntryPoint ep) {
		this.cepEntryPoint = ep;
	}

	public long getTransferredBytes() {
		return transferredBytes.get();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx,
			MessageEvent messageEvent) {

		byte[] bytes = getMessage(messageEvent);

		SyslogRawMessage syslogRawMessage = this.createRawMessage(bytes);

		SyslogContentParser parser = new SyslogMessageRouter()
				.route(syslogRawMessage);

		SyslogEvent event = parser.parse(syslogRawMessage);

		this.cepEntryPoint.insert(event);

		CEPEngine.getInstance().getSession().fireAllRules();

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
	private SyslogRawMessage createRawMessage(byte[] bytes) {
		return Rfc3164SyslogConverter.parseMessage(bytes);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		// Close the connection when an exception is raised.
		logger.log(Level.WARNING, "Unexpected exception from downstream.",
				e.getCause());
		e.getChannel().close();
	}
}