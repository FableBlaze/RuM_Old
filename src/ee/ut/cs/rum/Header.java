package ee.ut.cs.rum;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ee.ut.cs.rum.frontpage.Login;
import ee.ut.cs.rum.mainpage.workspace.TasksUser;

@SuppressWarnings("serial")
public class Header extends Panel {
	
	private Button loginButton;
	private Button logoutButton;
	private Button mainPanelButton;
	private Button createAccButton;
	private Button recoverViewButton;
	private Button aboutViewButton;
	
	private boolean loggedIn;
	private Button currentlyPressed;
	
	
	public Header() {
		RumUI currentUI = ((RumUI) UI.getCurrent());
		
		GridLayout mainLayout = new GridLayout(7, 1);
		VerticalLayout titleLayout = new VerticalLayout();
		
		Label title = new Label("RuM");
		titleLayout.addComponent(title);
		Label subTitle = new Label("Rule Mining Platform");
		titleLayout.addComponent(subTitle);
		mainLayout.addComponent(titleLayout, 0, 0);
		
		loginButton = currentUI.getRumNavigator().getLoginPanel().getEnterButton();
		mainLayout.addComponent(loginButton, 1, 0);
		mainLayout.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
		
		createAccButton = currentUI.getRumNavigator().getCreateAccountPanel().getEnterButton();
		mainLayout.addComponent(createAccButton, 4, 0);
		mainLayout.setComponentAlignment(createAccButton, Alignment.MIDDLE_CENTER);
		
		recoverViewButton = currentUI.getRumNavigator().getRecoverAccountPanel().getEnterButton();
		mainLayout.addComponent(recoverViewButton, 5, 0);
		mainLayout.setComponentAlignment(recoverViewButton, Alignment.MIDDLE_CENTER);
		
		aboutViewButton = currentUI.getRumNavigator().getAboutPanel().getEnterButton();
		mainLayout.addComponent(aboutViewButton, 6, 0);
		mainLayout.setComponentAlignment(aboutViewButton, Alignment.MIDDLE_CENTER);
		
		
		//Buttons not associated with specific view
		logoutButton = new Button("Logout");
		logoutButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(Login.NAME);
		    	currentUI.setCurrentUser(null);
		    }
		});
		logoutButton.setImmediate(true);
		logoutButton.setVisible(false);
		mainLayout.addComponent(logoutButton, 2, 0);
		mainLayout.setComponentAlignment(logoutButton, Alignment.MIDDLE_CENTER);
		
		mainPanelButton = new Button("Main Panel");
		mainPanelButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	if (loggedIn) {
		    		currentUI.getSideBar().setVisible(true);
		    		getUI().getNavigator().navigateTo(currentUI.getSideBar().getCurrentView());
				} else {
					getUI().getNavigator().navigateTo(TasksUser.NAME);
				}
		    	setCurrentlyPressed(mainPanelButton);
		    }
		});
		mainPanelButton.setImmediate(true);
		mainPanelButton.setVisible(false);
		mainLayout.addComponent(mainPanelButton, 3, 0);
		mainLayout.setComponentAlignment(mainPanelButton, Alignment.MIDDLE_CENTER);
		
		
		mainLayout.setWidth("100%");
		mainLayout.setColumnExpandRatio(0, 1);
		this.setContent(mainLayout);
	}
	
	public Button getCurrentlyPressed() {
		return currentlyPressed;
	}
	
	public void setCurrentlyPressed(Button currentlyPressed) {
		this.currentlyPressed = currentlyPressed;
	}

	public boolean isLoggedIn() {
		return this.loggedIn;
	}

	public void setMainPanelButtonPressed() {
		this.currentlyPressed = mainPanelButton;
		mainPanelButton.setEnabled(false);
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
		if (loggedIn) {
			this.loginButton.setVisible(false);
			this.logoutButton.setVisible(true);
			this.mainPanelButton.setVisible(true);
			setCurrentlyPressed(this.mainPanelButton);
			this.createAccButton.setVisible(false);
			this.recoverViewButton.setVisible(false);
		} else {
			this.loginButton.setVisible(true);
			this.logoutButton.setVisible(false);
			this.mainPanelButton.setVisible(false);
			this.createAccButton.setVisible(true);
			this.recoverViewButton.setVisible(true);
		}
	}
	

}
