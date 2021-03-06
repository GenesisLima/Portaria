package br.ufpe.ntvru.portaria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import br.ufpe.ntvru.portaria.dao.jpa.EntityManagerProvider;
import br.ufpe.ntvru.portaria.model.User;

public abstract class DAO<T> {

	protected ParameterizedTypeDAO<T> daoU ;
	
	
	protected EntityManager manager  = EntityManagerProvider.getInstance().createManager();
	protected EntityManagerFactory factory = EntityManagerProvider.getInstance().getFactory();
	
	 public void save(T t) {
		 manager.getTransaction().begin();
		 manager.merge(t);
		 manager.getTransaction().commit();
		// manager.close();
		// factory.close();
	 }
		
	 
	 
	 
	public abstract List<T> list();
//	{
//			System.out.println("Clazz"+daoU.getClassName().toString());
//			return (List<T>) manager.createQuery("select u from "+daoU.getClassName().toString()+" u ",daoU.getClass()).getResultList();
//
//		}
	 
	 public abstract void remove(T t);
	 public abstract void remove(int id);
	 
	 public abstract T getById(int id);
	 
	 public abstract List<T> getByName(String name);
	 
	 public abstract List<T> getByNameLike(String name);
	
}
