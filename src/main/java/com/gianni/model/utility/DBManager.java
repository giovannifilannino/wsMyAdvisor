package com.gianni.model.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBManager {

	
	private EntityManagerFactory emf;
	private static DBManager dbManager;
	
	private DBManager() {
		emf = Persistence.createEntityManagerFactory("MyAdvisorUnit");
	}
	
	public static DBManager getInstance() {
		if(null == dbManager) {
			dbManager = new DBManager();
		}
		
		return dbManager;
	}
	
	public EntityManager getEntityManager() {
		if(null != emf) {
			return emf.createEntityManager();
		}
		return null;
	}
	
	
	
	
}
