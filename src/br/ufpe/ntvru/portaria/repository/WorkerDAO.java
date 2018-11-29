package br.ufpe.ntvru.portaria.repository;

import java.util.List;

import br.ufpe.ntvru.portaria.dao.DAO;
import br.ufpe.ntvru.portaria.dao.ParameterizedTypeDAO;
import br.ufpe.ntvru.portaria.model.Visitor;
import br.ufpe.ntvru.portaria.model.Worker;

public class WorkerDAO extends DAO<Worker> {


	
	public WorkerDAO() {
		 super.daoU	= new ParameterizedTypeDAO<>();
		 super.daoU.reflect(this);	}
	
	@Override
	public List<Worker> list() {
		return manager.createQuery("select w from p_worker w where status='A'").getResultList();
	}

	@Override
	public void remove(int id) {
		 manager.getTransaction().begin();		
		manager.createQuery("update p_worker t set t.status='D' where t.id="+id).executeUpdate();
		 manager.getTransaction().commit();
	}

	@Override
	public Worker getById(int id) {
		return (Worker) manager.createQuery("select a from p_worker a where a.status='A' and a.id="+id,Worker.class).getSingleResult();
	}

	@Override
	public List<Worker> getByName(String nameParam) {
		
		return (List<Worker>) manager.createQuery("select a from p_worker a where a.status='A' and a.name='"+nameParam+"'",Worker.class).getResultList();

	}

	@Override
	public void remove(Worker t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Worker> getByNameLike(String name) {
		
		return  manager.createNamedQuery("Worker.findByNameAccountable").setParameter("name", "%"+name+"%").getResultList();
				
			//	createQuery("select a from p_worker a where a.status='A' and a.name like %'"+name+"%'",Worker.class).getResultList();
	}


}
