package ar.gov.anses.seginf.intrusos;

import org.drools.ClockType;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

import ar.gov.anses.seginf.intrusos.log.Logger;

public class CEPEngine {

	public static CEPEngine getInstance() {
		if (instance == null)
			instance = new CEPEngine();
		return instance;
	}

	// ****************************************************************
	// ***************************** Object ***************************
	// ****************************************************************

	private static CEPEngine instance;
	private String entryPointName;
	private String url = "ar/gov/anses/seginf/intrusos/change-set.xml";
	private KnowledgeAgent kagent;
	private KnowledgeBase base;
	private StatefulKnowledgeSession session;

	public CEPEngine() {
		this.setEntryPointName("syslog");
		this.kagent = this.createAgent(this.url);
	}

	/**
	 * Crea el agente que se va a encargar de monitorear si hay cambios en el
	 * archivo de reglas cada 60 segundos.
	 * 
	 * @param rulesFile
	 * @return
	 */
	private KnowledgeAgent createAgent(String rulesFile) {
		KnowledgeAgent kagent = KnowledgeAgentFactory
				.newKnowledgeAgent("MyAgent");
		Logger.debug("KnowledgeAgent started", this.getClass());
		kagent.applyChangeSet(ResourceFactory.newClassPathResource(rulesFile,
				getClass()));
		Logger.debug("ChangeSet applied", this.getClass());

		ResourceFactory.getResourceChangeNotifierService().start();
		ResourceFactory.getResourceChangeScannerService().start();

		return kagent;
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
		return session.getWorkingMemoryEntryPoint(this.getEntryPointName());
	}

	private KnowledgeBase getBase(KnowledgeAgent agent) {
		return kagent.getKnowledgeBase();
	}

	private KnowledgeAgent getAgent() {
		return this.kagent;
	}

	/**
	 * Devuelve la session existente si el archivo de reglas no fue modificado.
	 * Sino recarga el archivo y crea una nueva sesion.
	 * 
	 * @param base
	 * @return
	 */
	public StatefulKnowledgeSession getSession(KnowledgeBase base) {
		if (this.base == null || (base.hashCode() != this.base.hashCode())) {
			this.base = base;
			this.session = this.createKnowledgeSession(base);
			return this.session;
		} else
			return this.session;
	}

	public void fireAllRules() {
		this.getSession(this.base).fireAllRules();
	}

	public String getEntryPointName() {
		return entryPointName;
	}

	public void setEntryPointName(String entryPoint) {
		this.entryPointName = entryPoint;
	}

}
