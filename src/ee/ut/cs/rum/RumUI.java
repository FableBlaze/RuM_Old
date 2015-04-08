package ee.ut.cs.rum;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import ee.ut.cs.rum.mainpage.SideBar;

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