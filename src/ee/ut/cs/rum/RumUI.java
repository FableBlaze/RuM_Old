package ee.ut.cs.rum;

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

	private JPAContainer<Account> accounts;

	private MainLayout mainLayout;
	private Header header;
	private SideBar sideBar;
	private RumNavigator rumNavigator;
	private String currentUser; //TODO proper user handling

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

		accounts = JPAContainerFactory.make(Account.class, RumUI.PERSISTENCE_UNIT);

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

	public String getCurrentUser() {
		return currentUser;
	}

	public JPAContainer<Account> getAccounts() {
		return accounts;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
		if (currentUser == "ADMINISTRATOR" || currentUser == "USER") {
			getSideBar().setLoggedInRole(currentUser);
			getHeader().setLoggedIn(true);
		} else {
			getSideBar().setLoggedInRole(null);
			getHeader().setLoggedIn(false);
		}
	}
}