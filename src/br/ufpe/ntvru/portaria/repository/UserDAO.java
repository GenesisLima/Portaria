package br.ufpe.ntvru.portaria.repository;

import java.util.List;

import br.ufpe.ntvru.portaria.dao.DAO;
import br.ufpe.ntvru.portaria.dao.ParameterizedTypeDAO;
import br.ufpe.ntvru.portaria.model.User;

public class UserDAO extends DAO<User>{

	
	
	public UserDAO() {
		super.daoU = new ParameterizedTypeDAO<User>();
	}
	
	@Override
	public List<User> list() {
		
		return manager.createQuery("select u from p_user u",User.class).getResultList();

	}

	@Override
	public void remove(User user) {
		manager.remove(user);
		
	}
	
	@Override
	public void remove(int id) {
		manager.remove(id);
		
	}

	@Override
	public User getById(int id) {
		return manager.createQuery("select u from p_user u where u.status='A' and u.id="+id,User.class).getSingleResult();

	}

	@Override
	public List<User> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getByNameLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
