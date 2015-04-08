package ee.ut.cs.rum.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.service.ServiceRegistry;

import ee.ut.cs.rum.utilities.pojos.Account;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    
    static {
    	try {
    		System.out.println("test");
    		Configuration configuration = new Configuration();
    		configuration.setProperty(Environment.DRIVER, "org.postgresql.Driver");
    		configuration.setProperty(Environment.URL, "jdbc:postgresql://127.0.0.1:5432/RuM");
    		configuration.setProperty(Environment.USER, "postgres");
    		configuration.setProperty(Environment.PASS, "postgres");
    		configuration.setProperty(Environment.DIALECT, PostgreSQL9Dialect.class.getName());
    		configuration.setProperty(Environment.SHOW_SQL, "true");
    		configuration.setProperty(Environment.HBM2DDL_AUTO, "update");
    		configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configuration.addAnnotatedClass(Account.class);
            //configuration.addAnnotatedClass(Type.class);
    		
    		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void closeSessionFactory() {
		if (sessionFactory != null)
			sessionFactory.close();
	}
}
