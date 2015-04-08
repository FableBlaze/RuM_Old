package ee.ut.cs.rum;

import javax.servlet.annotation.WebServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ee.ut.cs.rum.mainpage.SideBar;
import ee.ut.cs.rum.utilities.HibernateUtil;
import ee.ut.cs.rum.utilities.pojos.Account;

@SuppressWarnings("serial")
@Theme("rum")
@Push
public class RumUI extends UI {

	@WebServlet(value = {"/*", "/VAADIN/*"}, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = RumUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	private MainLayout mainLayout;
	private Header header;
	private SideBar sideBar;
	private RumNavigator rumNavigator;
	private String currentUser; //TODO proper user handling
	
	@Override
	protected void init(VaadinRequest request) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//		session.beginTransaction();
//        Account a1 = new Account("Name", "Email", "Password", "Role");
//        long accountId = (Long) session.save(a1);
//        session.getTransaction().commit();
//       
//        Account account1 = (Account) session.get(Account.class, new Long("1"));       
//        System.out.println(account1);
//               
//        session.close();
		
		
		
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