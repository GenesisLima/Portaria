package br.ufpe.ntvru.portaria.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeDAO<T> {

	private String className;
	private T type;
	
	public <T> void reflect(T t){
		Type type = t.getClass().getGenericSuperclass();
	    //System.out.println(type); 
	    ParameterizedType pt = (ParameterizedType) type;
	    this.className = ((Class)pt.getActualTypeArguments()[0]).getSimpleName();
	    System.out.println(((Class)pt.getActualTypeArguments()[0]).getSimpleName());
	    System.out.println(pt.getTypeName());
	    System.out.println(pt.getRawType());
		
		
	}

	public String getClassName() {
		return className;
	}
}
