package ar.gov.anses.seginf.intrusos.parser;

import org.junit.Test;

import ar.gov.anses.seginf.intrusos.persistence.Repository;

public class PersistenceTest {
	
	
	@Test
	public void testInstantiateDatabase(){
		
		Repository.getInstance();
		
		
	}

}
