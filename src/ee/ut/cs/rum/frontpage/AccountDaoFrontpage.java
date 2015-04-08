package ee.ut.cs.rum.frontpage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ee.ut.cs.rum.utilities.HibernateUtil;
import ee.ut.cs.rum.utilities.pojos.Account;

public class AccountDaoFrontpage {
	private SessionFactory sessionFactory;
	private Session session;
	
	public AccountDaoFrontpage() {
		sessionFactory = HibernateUtil.getSessionFactory();
		System.out.println("dao");
	}
	
	public Boolean createAccount(String accName, String accEmail, String accPassword) {
		Account acc = new Account();
		acc.setName(accName);
		acc.setEmail(accEmail);
		acc.setPassword(accPassword);
		acc.setRole("USER");
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(acc);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
		return true;
	}
	
}
