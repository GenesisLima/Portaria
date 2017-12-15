package br.ufpe.ntvru.portaria.repository;

import java.util.List;

import br.ufpe.ntvru.portaria.dao.DAO;
import br.ufpe.ntvru.portaria.dao.ParameterizedTypeDAO;
import br.ufpe.ntvru.portaria.model.Visitor;

public class VisitorDAO extends DAO<Visitor> {

	
	public VisitorDAO() {
	 super.daoU	= new ParameterizedTypeDAO<>();
	 super.daoU.reflect(this);

	}
	
	@Override
	public List<Visitor> list() {
		return manager.createQuery("select v from p_visitor v where status='A'").getResultList();
	}

	@Override
	public void remove(int id) {
		 manager.getTransaction().begin();		
		manager.createQuery("update p_visitor t set t.status='D' where t.id="+id).executeUpdate();
		 manager.getTransaction().commit();
	}

	@Override
	public Visitor getById(int id) {
		return (Visitor) manager.createQuery("select a from p_visitor a where a.status='A' and a.id="+id,Visitor.class).getSingleResult();
	}

	@Override
	public List<Visitor> getByName(String nameParam) {
		
		return (List<Visitor>) manager.createQuery("select a from p_visitor a where a.status='A' and a.name='"+nameParam+"'",Visitor.class).getResultList();

	}

	@Override
	public void remove(Visitor t) {
		// TODO Auto-generated method stub
		
	}

}
