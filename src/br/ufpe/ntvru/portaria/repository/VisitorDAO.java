package br.ufpe.ntvru.portaria.repository;

import java.util.List;

import br.ufpe.ntvru.portaria.dao.DAO;
import br.ufpe.ntvru.portaria.dao.ParameterizedTypeDAO;
import br.ufpe.ntvru.portaria.model.User;
import br.ufpe.ntvru.portaria.model.Visitor;

public class VisitorDAO extends DAO<Visitor> {

	
	public VisitorDAO() {
	 super.daoU	= new ParameterizedTypeDAO<>();
	}
	
	@Override
	public List<Visitor> list() {
		return manager.createQuery("select v from p_visitor v where status='A'").getResultList();
	}

	@Override
	public void remove(Visitor visitor) {
		manager.createQuery("update p_visitor t set t.status='D' where t.id="+visitor.getId()).executeUpdate();
		
	}

	@Override
	public Visitor getById(int id) {
		return (Visitor) manager.createQuery("select a from p_visitor a where a.status='A' and a.id="+id).getSingleResult();
	}

}
