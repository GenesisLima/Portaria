package br.ufpe.ntvru.portaria.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    private static final EntityManagerProvider INSTANCE = new EntityManagerProvider();

	

    private final EntityManagerFactory factory;

    private EntityManagerProvider() {
        this.factory = Persistence.createEntityManagerFactory("visitors");
    }

    public static EntityManagerProvider getInstance() {
        return INSTANCE;
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public EntityManager createManager() {
        return factory.createEntityManager();
    }
    
    	
}
