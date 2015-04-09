package ee.ut.cs.rum.frontpage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import com.vaadin.ui.Notification;

import ee.ut.cs.rum.utilities.HibernateUtil;
import ee.ut.cs.rum.utilities.pojos.Account;

public class AccountDaoFrontpage {
	private SessionFactory sessionFactory;
	private Session session;
	
	public AccountDaoFrontpage() {
		sessionFactory = HibernateUtil.getSessionFactory();
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
			return true;
		} catch (ConstraintViolationException e) {
			System.out.println(e);
			Notification.show("Account name must be unique!", "Click to dismiss", Notification.Type.ERROR_MESSAGE);
		} catch (Exception e) {
			System.out.println(e);
			Notification.show("Unexpected error", "Click to dismiss", Notification.Type.ERROR_MESSAGE);
		}finally {
			if (session.isDirty()) {
				session.getTransaction().rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
		}
		return false;
	}
	
}
