package br.ufpe.ntvru.portaria.service;

import java.util.List;

public interface ServiceStrategy<T> {

	public void add(T t);
	
	public void drop(T t);
	public void drop(int id);
	
	public List<T> getAll();
	
	public T getById(int id);
	
	public List<T> getByName(String name);
	
	public List<T> getByNameLike(String name);
	
}
