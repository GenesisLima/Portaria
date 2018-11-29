package br.ufpe.ntvru.portaria.repository;

import java.util.List;

import br.ufpe.ntvru.portaria.dao.DAO;
import br.ufpe.ntvru.portaria.dao.ParameterizedTypeDAO;
import br.ufpe.ntvru.portaria.model.Department;

public class DepartmentDAO extends DAO<Department>{

	
	public DepartmentDAO() {
		 super.daoU	= new ParameterizedTypeDAO<>();
		 super.daoU.reflect(this);

		}
	
	@Override
	public List<Department> list() {		
		return manager.createQuery("select d from p_department d where d.status = 'A'").getResultList();
	}

	@Override
	public void remove(Department t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int id) {
		    manager.getTransaction().begin();
	        manager.createQuery("update p_department d set d.status='D' where d.id="+id).executeUpdate();
	        manager.getTransaction().commit();
	        }

	@Override
	public Department getById(int id) {		
		return manager.createQuery("select d from p_department d where d.status ='A' and d.id ="+id, Department.class).getSingleResult();
	}

	@Override
	public List<Department> getByName(String name) {
		
		return manager.createQuery("select d from p_department d where d.status='A' and d.name="+name, Department.class).getResultList();
	}

	@Override
	public List<Department> getByNameLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
