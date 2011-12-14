package ar.gov.anses.seginf.intrusos;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Properties;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.FixedReceiveBufferSizePredictorFactory;

public class RSyslogServer {

	public static void main(String[] args) throws Exception {

		loadProperties();

		ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(
				ChannelFactoryFactory.createUDPChannelFactory());

		// Set up the pipeline factory.
		bootstrap.setPipelineFactory(createChannelPipeline());

		bootstrap.setOption("receiveBufferSizePredictorFactory",
				new FixedReceiveBufferSizePredictorFactory(1024));

		// Bind and start to accept incoming conn<ections.
		bootstrap.bind(new InetSocketAddress(1514));

		System.out
				.println("Escuchando conexiones UDP en el puerto 1514, dale petitt!");
	}

	private static void loadProperties() {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/main/resources/config.properties"));
			System.out.println("Configuration Loaded");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Configuration could not be loaded");
		}

	}

	private static ChannelPipelineFactory createChannelPipeline() {
		return new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {

				return Channels.pipeline(new RSyslogServerHandler());
			}
		};
	}
}
