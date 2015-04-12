package ee.ut.cs.rum;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.servlet.annotation.WebServlet;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.service.ServiceRegistry;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ee.ut.cs.rum.domain.Account;
import ee.ut.cs.rum.mainpage.SideBar;

@SuppressWarnings("serial")
@Theme("rum")
@Push
@PreserveOnRefresh
public class RumUI extends UI {

	@WebServlet(value = {"/*", "/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = RumUI.class)
	public static class Servlet extends VaadinServlet {
	}

	public static final String PERSISTENCE_UNIT = "RuM";

	private EntityManager entityManager;
	private JPAContainer<Account> accounts;

	private MainLayout mainLayout;
	private Header header;
	private SideBar sideBar;
	private RumNavigator rumNavigator;
	private Account currentUser; //TODO proper user handling

	@Override
	protected void init(VaadinRequest request) {
		//Did not get JPAContainerFactory to create tables so using a dumb workaround
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

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		entityManager = JPAContainerFactory.createEntityManagerForPersistenceUnit(RumUI.PERSISTENCE_UNIT);
		accounts = JPAContainerFactory.make(Account.class, entityManager);

		mainLayout = new MainLayout();
		rumNavigator = new RumNavigator(this, mainLayout.getContent());
		mainLayout.initHeader();
		mainLayout.initSideBar();
		header = mainLayout.getHeader();
		sideBar = mainLayout.getSideBar();

		this.setContent(mainLayout);
	}

	public Header getHeader() {
		return header;
	}

	public SideBar getSideBar() {
		return sideBar;
	}

	public RumNavigator getRumNavigator() {
		return rumNavigator;
	}

	public Account getCurrentUser() {
		return currentUser;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public JPAContainer<Account> getAccounts() {
		return accounts;
	}

	public void setCurrentUser(Account currentUser) {
		this.currentUser = currentUser;
		if (currentUser == null) {
			getSideBar().setLoggedInRole(null);
			getHeader().setLoggedIn(false);
		} else if (currentUser.getRole().equals("ADMINISTRATOR") || currentUser.getRole().equals("USER")) {
			getSideBar().setLoggedInRole(currentUser.getRole());
			getHeader().setLoggedIn(true);
		}
	}
}