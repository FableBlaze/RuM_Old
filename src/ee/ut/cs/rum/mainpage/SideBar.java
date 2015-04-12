package ee.ut.cs.rum.mainpage;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

import ee.ut.cs.rum.RumUI;
import ee.ut.cs.rum.mainpage.workspace.TasksUser;

@SuppressWarnings("serial")
public class SideBar extends Panel {
	
	private Button tasksUserButton;
	private Button filesUserButton;
	private Button pluginsUserButton;
	private Button tasksSharedButton;
	private Button filesSharedButton;
	
	private Button accountsButton;
	private Button groupsButton;
	
	private Button pluginsAdminButton;
	private Button tasksAdminButton;
	private Button logButton;
	private Button settingsButton;
	
	private String currentView;
	private Button currentlyPressed;
	
	public SideBar() {
		RumUI currentUI = ((RumUI) UI.getCurrent());
		
		GridLayout layout = new GridLayout(1, 14);
		
		Label workspaceLabel = new Label("Workspace:");
		layout.addComponent(workspaceLabel, 0, 0);
		layout.setComponentAlignment(workspaceLabel, Alignment.MIDDLE_LEFT);
		
		tasksUserButton = currentUI.getRumNavigator().getTasksUserPanel().getEnterButton();
		layout.addComponent(tasksUserButton, 0, 1);
		layout.setComponentAlignment(tasksUserButton, Alignment.MIDDLE_LEFT);
		
		filesUserButton = currentUI.getRumNavigator().getFilesUserPanel().getEnterButton();
		layout.addComponent(filesUserButton, 0, 2);
		layout.setComponentAlignment(filesUserButton, Alignment.MIDDLE_LEFT);
		
		pluginsUserButton = currentUI.getRumNavigator().getPluginsUserPanel().getEnterButton();
		layout.addComponent(pluginsUserButton, 0, 3);
		layout.setComponentAlignment(pluginsUserButton, Alignment.MIDDLE_LEFT);
		
		tasksSharedButton = currentUI.getRumNavigator().getTasksSharedPanel().getEnterButton();
		layout.addComponent(tasksSharedButton, 0, 4);
		layout.setComponentAlignment(tasksSharedButton, Alignment.MIDDLE_LEFT);
		
		filesSharedButton = currentUI.getRumNavigator().getFilesSharedPanel().getEnterButton();
		layout.addComponent(filesSharedButton, 0, 5);
		layout.setComponentAlignment(filesSharedButton, Alignment.MIDDLE_LEFT);
		
		
		Label managementLabel = new Label("Accounts Management:");
		layout.addComponent(managementLabel, 0, 6);
		layout.setComponentAlignment(managementLabel, Alignment.MIDDLE_LEFT);
		
		accountsButton = currentUI.getRumNavigator().getAccountsPanel().getSideBarEnterButton();
		layout.addComponent(accountsButton, 0, 7);
		layout.setComponentAlignment(accountsButton, Alignment.MIDDLE_LEFT);
		
		groupsButton = currentUI.getRumNavigator().getGroupsPanel().getEnterButton();
		layout.addComponent(groupsButton, 0, 8);
		layout.setComponentAlignment(groupsButton, Alignment.MIDDLE_LEFT);
		
		
		Label adminLabel = new Label("Administration:");
		layout.addComponent(adminLabel, 0, 9);
		layout.setComponentAlignment(adminLabel, Alignment.MIDDLE_LEFT);
		
		pluginsAdminButton = currentUI.getRumNavigator().getPluginsAdminPanel().getEnterButton();
		layout.addComponent(pluginsAdminButton, 0, 10);
		layout.setComponentAlignment(pluginsAdminButton, Alignment.MIDDLE_LEFT);
		
		tasksAdminButton = currentUI.getRumNavigator().getTasksAdminPanel().getEnterButton();
		layout.addComponent(tasksAdminButton, 0, 11);
		layout.setComponentAlignment(tasksAdminButton, Alignment.MIDDLE_LEFT);
		
		logButton = currentUI.getRumNavigator().getLogPanel().getEnterButton();
		layout.addComponent(logButton, 0, 12);
		layout.setComponentAlignment(logButton, Alignment.MIDDLE_LEFT);
		
		settingsButton = currentUI.getRumNavigator().getSettingsPanel().getEnterButton();
		layout.addComponent(settingsButton, 0, 13);
		layout.setComponentAlignment(settingsButton, Alignment.MIDDLE_LEFT);

		tasksUserButton.setEnabled(false);
		this.currentlyPressed = tasksUserButton;
		this.currentView = TasksUser.NAME;
		this.setContent(layout);
		this.setSizeUndefined();
		this.setVisible(false);
	}
	
	public Button getCurrentlyPressed() {
		return currentlyPressed;
	}
	
	public void setCurrentlyPressed(Button currentlyPressed) {
		this.currentlyPressed = currentlyPressed;
	}
	
	public String getCurrentView() {
		return currentView;
	}
	
	public void setCurrentView(String currentView) {
		this.currentView = currentView;
	}

	public void setLoggedInRole(String role) {
		if (role == null) {
			this.setVisible(false);
		} else if (role.equals("ADMINISTRATOR") || role.equals("USER")) {
			this.setVisible(true);
		}
	}
	
}
