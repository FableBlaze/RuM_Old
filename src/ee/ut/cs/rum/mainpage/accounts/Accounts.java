package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;
import ee.ut.cs.rum.mainpage.TabBar;

@SuppressWarnings("serial")
public class Accounts extends GridLayout implements ComponentContainer, View {
	public static final String NAME = "accounts";
	
	private RumUI currentUI;
	private Button sideBarEnterButton;
	private Button tabBarEnterButton;
	private Button addAccountButton;
	private TabBar tabBar;
	private Panel contentPanel;
	private AccountsTable accountsTable;
	private CreateAccountAdmin createAccountAdmin;
	private String currentContent;
	
	
	public Accounts() {
		currentUI = ((RumUI) UI.getCurrent());
		
		this.setColumns(2);
		
		createAccountsTabBar();
		this.addComponent(tabBar, 0, 0);
		
		contentPanel = new Panel();
		contentPanel.setImmediate(true);
		this.addComponent(contentPanel, 1, 0);
		
		accountsTable = new AccountsTable(currentUI);
		createAccountAdmin = new CreateAccountAdmin();
		
		this.setSizeFull();
		this.setColumnExpandRatio(1, 1);
		
		sideBarEnterButton = createSideBarEnterButton("Accounts");
	}
	
	private void createAccountsTabBar() {
		tabBar = new TabBar();
		tabBar.setCaption("Accounts sub-sections:");
		tabBarEnterButton = createTabBarEnterButton("Accounts");
		tabBar.addButton(tabBarEnterButton);
		addAccountButton = createAddAccountButton();
		tabBar.addButton(addAccountButton);
	}
	
	private Button createAddAccountButton() {
		Button addAccButton = new Button("Add account");
		addAccButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	tabBar.setCurrentlyPressed(event.getButton());
		    	currentUI.getRumNavigator().navigateTo(Accounts.NAME+"/"+CreateAccountAdmin.NAME);
		    }
		});
		addAccButton.setImmediate(true);
		return addAccButton;
	}
	
	private Button createTabBarEnterButton(String label) {
		Button enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(Accounts.NAME);
		    	currentContent = null;
				contentPanel.setContent(accountsTable);
		    	tabBar.setCurrentlyPressed(event.getButton());
		    }
		});
		return enterButton;
	}
	
	private Button createSideBarEnterButton(String label) {
		Button enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(Accounts.NAME);
		    }
		});
		return enterButton;
	}
	
	public Button getSideBarEnterButton() {
		return sideBarEnterButton;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		sideBarEnterButton.setEnabled(false);
		currentUI.getHeader().setMainPanelButtonPressed();
		currentUI.getSideBar().setCurrentlyPressed(sideBarEnterButton);
		currentUI.getSideBar().setCurrentView(Accounts.NAME);
		String parameters = event.getParameters();
		if (parameters.isEmpty()) {
			if (currentContent == null) {
				contentPanel.setContent(accountsTable);
				tabBar.setCurrentlyPressed(tabBarEnterButton);
			}
		} else if (parameters.equals(CreateAccountAdmin.NAME)) {
			currentContent = event.getParameters();
			contentPanel.setContent(createAccountAdmin);
			tabBar.setCurrentlyPressed(addAccountButton);
		} else if (parameters.startsWith(AccountDetails.NAME)) {
			if (!tabBar.getCurrentTabs().contains(parameters)) {
				try {
					currentContent = parameters;
					AccountDetails accountDetails = new AccountDetails(event.getParameters());
					Button enterButton = new Button(event.getParameters());
					enterButton.setImmediate(true);
					enterButton.addClickListener(new Button.ClickListener() {
					    public void buttonClick(ClickEvent event) {
					    	currentUI.getRumNavigator().navigateTo(Accounts.NAME+"/"+parameters);
					    	contentPanel.setContent(accountDetails);
					    	tabBar.setCurrentlyPressed(enterButton);
					    }
					});
					tabBar.addTab(enterButton, tabBarEnterButton, parameters);
					currentUI.getRumNavigator().navigateTo(Accounts.NAME+"/"+parameters);
			    	contentPanel.setContent(accountDetails);
			    	tabBar.setCurrentlyPressed(enterButton);
				} catch (Exception e) {
					System.out.println(e);
					currentUI.getRumNavigator().navigateTo(Accounts.NAME);
				}
				
			}
		}
	}
}
