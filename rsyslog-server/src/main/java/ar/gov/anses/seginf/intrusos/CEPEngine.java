package ar.gov.anses.seginf.intrusos;

import org.drools.ChangeSet;
import org.drools.ClockType;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

import ar.gov.anses.seginf.intrusos.log.Logger;

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
	private String url = "file:///home/aparedes/workspace/redhat/cep-intrusos/rsyslog-server/src/main/java/ar/gov/anses/seginf/intrusos/change-set.xml";
	private KnowledgeAgent kagent;
	private KnowledgeBase base;
	private StatefulKnowledgeSession session;

	public CEPEngine() {

		this.kagent = this.createAgent(this.url);
	}

	// public void initEngine() {
	//
	//
	// // Creates a knowledge base
	// // this.kbase = kagent.getKnowledgeBase();
	// this.kbase = this.createKnowledgeBase(this.url);
	// Logger.debug("KnowledgeBase created >> " + this.kbase, this.getClass());
	//
	// for (KnowledgePackage kp : this.kbase.getKnowledgePackages()) {
	// Logger.debug(String.valueOf(kp.getRules().size()), this.getClass());
	// }
	//
	// // Creates a knowledge session
	// this.ksession = createKnowledgeSession(kbase);
	// Logger.debug("KnowledgeSession started >> " + this.ksession,
	// this.getClass());
	//
	// // Gets the stream entry point
	// this.ep = ksession.getWorkingMemoryEntryPoint("syslog");
	// Logger.debug("WorkingMemoryEntryPoint setted >> " + this.ep,
	// this.getClass());
	//
	// ResourceFactory.getResourceChangeNotifierService().start();
	//
	// ResourceFactory.getResourceChangeScannerService().start();
	//
	// System.out.println("CEP Levantado");
	// // Starts to fire rules in Drools Fusion
	// // ksession.fireUntilHalt();
	// }

	private KnowledgeAgent createAgent(String rulesFile) {
		KnowledgeAgent kagent = KnowledgeAgentFactory
				.newKnowledgeAgent("MyAgent");
		Logger.debug("KnowledgeAgent started", this.getClass());

		kagent.applyChangeSet(ResourceFactory.newUrlResource(rulesFile));
		Logger.debug("ChangeSet applied", this.getClass());

		ResourceFactory.getResourceChangeNotifierService().start();
		ResourceFactory.getResourceChangeScannerService().start();

		return kagent;
	}

	/**
	 * Creates a Drools KnowledgeBase and adds the given rules file into it
	 */
	private KnowledgeBase createKnowledgeBase(String rulesFile) {

		KnowledgeAgent kagent = KnowledgeAgentFactory
				.newKnowledgeAgent("MyAgent");
		Logger.debug("KnowledgeAgent started", this.getClass());

		kagent.applyChangeSet(ResourceFactory.newUrlResource(rulesFile));
		Logger.debug("ChangeSet applied", this.getClass());

		// Parses and compiles the rules file
		// KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
		// .newKnowledgeBuilder();
		// kbuilder.add(ResourceFactory.newUrlResource(rulesFile),
		// ResourceType.CHANGE_SET);

		// Verificacion de errores
		// KnowledgeBuilderErrors errors = kbuilder.getErrors();
		// if (errors.size() > 0) {
		// for (KnowledgeBuilderError error : errors) {
		// System.err.println(error);
		// }
		// throw new IllegalArgumentException("Could not parse knowledge.");
		// }

		// Configures the Stream mode
		// KnowledgeBaseConfiguration conf = KnowledgeBaseFactory
		// .newKnowledgeBaseConfiguration();
		// conf.setOption(EventProcessingOption.STREAM);

		// Creates the knowledge base and adds the rules
		// kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		// return kbase;
		return kagent.getKnowledgeBase();
	}

	/**
	 * Creates a Drools Stateful Knowledge Session
	 */
	private StatefulKnowledgeSession createKnowledgeSession(KnowledgeBase kbase) {
		KnowledgeSessionConfiguration ksconf = KnowledgeBaseFactory
				.newKnowledgeSessionConfiguration();
		ksconf.setOption(ClockTypeOption.get(ClockType.REALTIME_CLOCK.getId()));
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession(
				ksconf, null);

		for (WorkingMemoryEntryPoint wmep : ksession
				.getWorkingMemoryEntryPoints()) {
			Logger.debug("cant: " + wmep.getEntryPointId(), this.getClass());
		}

		return ksession;
	}

	public WorkingMemoryEntryPoint getWorkingMemoryEntryPoint() {
		KnowledgeBase base = this.getBase(this.getAgent());
		StatefulKnowledgeSession session = this.getSession(base);
		return session.getWorkingMemoryEntryPoint("syslog");
	}

	private KnowledgeBase getBase(KnowledgeAgent agent) {
		return kagent.getKnowledgeBase();
	}

	private KnowledgeAgent getAgent() {
		return this.kagent;
	}

	public StatefulKnowledgeSession getSession(KnowledgeBase base) {
		if (this.base == null || (base.hashCode() != this.base.hashCode())) {
			this.base = base;
			this.session = this.createKnowledgeSession(base);
			return this.session;
		} else
			return this.session;
	}

	public void reloadWorkingMemory() {

		System.out.println("CEP RELOADED");
	}

	public void fireAllRules() {
		this.getSession(this.base).fireAllRules();
	}

}
