package br.ufpe.ntvru.portaria.service;

public abstract class Service {

	protected ServiceStrategy serviceStragety;
	
	public Service(ServiceStrategy serviceStrategy) {
	this.serviceStragety = serviceStragety;
			
	}
	
	
    public  <T> void add(T t) {
    	serviceStragety.add(t);
    	
    }
	
	public  <T> void drop(T t) {
		serviceStragety.drop(t);
	}
	
	public  void getAll() {
		serviceStragety.getAll();
		
	}
	
	public  <T> T getById(int id) {
       return (T) serviceStragety.getById(id);
		
	}
	
	
	
	
	
}
