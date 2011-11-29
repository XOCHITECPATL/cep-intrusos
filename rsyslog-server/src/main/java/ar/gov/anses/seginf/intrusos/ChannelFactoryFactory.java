package ar.gov.anses.seginf.intrusos;

import java.util.concurrent.Executors;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class ChannelFactoryFactory {

	public static ChannelFactory createTCPChannelFactory() {
		System.out.println("TCP CONNECTION");
		NioServerSocketChannelFactory f = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());
		return f;
	}

	public static ChannelFactory createUDPChannelFactory() {
		System.out.println("UDP CONNECTION");
		NioDatagramChannelFactory f = new NioDatagramChannelFactory(
				Executors.newCachedThreadPool());
		return f;
	}

}
