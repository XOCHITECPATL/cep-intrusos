package ar.gov.anses.seginf.intrusos;

import java.io.File;

import org.drools.ClockType;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

public class CEPEngine {

	private static CEPEngine instance;

	public static CEPEngine getInstance() {
		if (instance == null)
			instance = new CEPEngine();
		return instance;
	}

	private WorkingMemoryEntryPoint ep;
	private StatefulKnowledgeSession ksession;
	private KnowledgeBase kbase;
	private long lastModified;
	private String url = "sudo.drl";
	private String fileURL = "/home/aparedes/workspace/redhat/cep-intrusos/rsyslog-server/src/main/java/ar/gov/anses/seginf/intrusos/sudo.drl";

	public void initEngine() {
		// Creates a knowledge base
		this.kbase = createKnowledgeBase(this.url);

		this.setLastModified(new File(this.fileURL).lastModified());
		System.out.println("TIME LAS MODIFIED: " + this.getLastModified());

		// Creates a knowledge session
		this.ksession = createKnowledgeSession(kbase);

		// Gets the stream entry point
		this.ep = ksession.getWorkingMemoryEntryPoint("syslog");

		System.out.println("CEP Levantado");
		// Starts to fire rules in Drools Fusion
		// ksession.fireUntilHalt();
	}

	/**
	 * Creates a Drools KnowledgeBase and adds the given rules file into it
	 */
	private KnowledgeBase createKnowledgeBase(String rulesFile) {
		// Parses and compiles the rules file
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(rulesFile,
				this.getClass()), ResourceType.DRL);

		// Verificacion de errores
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}

		// Configures the Stream mode
		KnowledgeBaseConfiguration conf = KnowledgeBaseFactory
				.newKnowledgeBaseConfiguration();
		conf.setOption(EventProcessingOption.STREAM);

		// Creates the knowledge base and adds the rules
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(conf);
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	/**
	 * Creates a Drools Stateful Knowledge Session
	 */
	private StatefulKnowledgeSession createKnowledgeSession(
			final KnowledgeBase kbase) {
		final KnowledgeSessionConfiguration ksconf = KnowledgeBaseFactory
				.newKnowledgeSessionConfiguration();
		ksconf.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		final StatefulKnowledgeSession ksession = kbase
				.newStatefulKnowledgeSession(ksconf, null);
		return ksession;
	}

	public WorkingMemoryEntryPoint getWorkingMemoryEntryPoint() {
		return this.ep;
	}

	public StatefulKnowledgeSession getSession() {
		return this.ksession;
	}

	public void reloadWorkingMemory() {
		this.ksession = this.createKnowledgeSession(this.getBase());
		this.ep = ksession.getWorkingMemoryEntryPoint("syslog");
		this.setLastModified(new File(this.fileURL).lastModified());
		System.out.println("CEP RELOADED");
	}

	private KnowledgeBase getBase() {
		return this.kbase;
	}

	private long getLastModified() {
		return this.lastModified;
	}

	private void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public boolean wasModificated() {
		System.out.println(" WAS MODIFICATED  :"
				+ (this.getLastModified() != new File(this.fileURL)
						.lastModified()));
		return this.getLastModified() != new File(this.fileURL).lastModified();
	}

}
