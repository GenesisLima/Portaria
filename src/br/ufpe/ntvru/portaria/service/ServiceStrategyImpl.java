package br.ufpe.ntvru.portaria.service;

import br.ufpe.ntvru.portaria.dao.DAO;

public class ServiceStrategyImpl<T> implements ServiceStrategy<T>{

	private DAO<T> dao;
	
	public ServiceStrategyImpl(DAO<T> dao) {
		this.dao = dao;
		
	}
	@Override
	public void add(T t) {
		dao.save(t);
		
	}

	@Override
	public void drop(T t) {
		dao.remove(t);
		
	}

	@Override
	public void getAll() {
		dao.list();
		
	}

	@Override
	public T getById(int id) {
	return dao.getById(id);
	}

}
