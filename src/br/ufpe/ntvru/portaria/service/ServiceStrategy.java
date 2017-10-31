package br.ufpe.ntvru.portaria.service;

public interface ServiceStrategy<T> {

	public void add(T t);
	
	public void drop(T t);
	
	public void getAll();
	
	public T getById(int id);
	
}
