package br.ufpe.ntvru.portaria.testdrive;

import br.ufpe.ntvru.portaria.model.User;
import br.ufpe.ntvru.portaria.model.Visitor;
import br.ufpe.ntvru.portaria.repository.UserDAO;
import br.ufpe.ntvru.portaria.repository.VisitorDAO;
import br.ufpe.ntvru.portaria.service.ServiceStrategy;
import br.ufpe.ntvru.portaria.service.ServiceStrategyImpl;

public class TestDrive {

	public static void main(String[] args) {
		
		User u = new User();
		//u.setId(1);
		u.setUsername("test");
		u.setPassword("test");
//		EntityManager em = EntityManagerProvider.getInstance().createManager();
//		em.getTransaction().begin();
//		em.persist(u);
//		em.getTransaction().commit();
//		EntityManagerProvider.getInstance().getFactory().close();
		//EntityManagerProvider.getInstance().createManager().persist(u);
		
		//EntityManagerProvider.getInstance().getFactory().close();

		//ServiceStrategy<User> ssu = new ServiceStrategyImpl<User>(new UserDAO());
		
 		//ssu.add(u);
		
		//UserDAO udao = new UserDAO();
		//udao.save(u);
		//System.out.println(udao.getById(1).getUsername());
		
		ServiceStrategy<Visitor> visitorsStrategy = new ServiceStrategyImpl<Visitor>(new VisitorDAO());
		Visitor visitor = new Visitor();
		visitor.setName("adsadasd");
		visitor.setCpf("45678977");
		visitor.setAccountable("TEST");
		visitor.setAdditionalInfo("adsadasdasdsadasdsadadadasdasdasdasdasdsa");
		visitor.setProduct("Pisada");
		visitorsStrategy.add(visitor);
	}

}
