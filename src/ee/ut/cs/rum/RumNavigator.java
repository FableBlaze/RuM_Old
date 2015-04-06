package ee.ut.cs.rum;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

import ee.ut.cs.rum.frontpage.About;
import ee.ut.cs.rum.frontpage.CreateAccount;
import ee.ut.cs.rum.frontpage.Login;
import ee.ut.cs.rum.frontpage.RecoverAccount;
import ee.ut.cs.rum.mainpage.accounts.Accounts;
import ee.ut.cs.rum.mainpage.accounts.Groups;
import ee.ut.cs.rum.mainpage.administration.Log;
import ee.ut.cs.rum.mainpage.administration.PluginsAdmin;
import ee.ut.cs.rum.mainpage.administration.Settings;
import ee.ut.cs.rum.mainpage.administration.TasksAdmin;
import ee.ut.cs.rum.mainpage.workspace.FilesShared;
import ee.ut.cs.rum.mainpage.workspace.FilesUser;
import ee.ut.cs.rum.mainpage.workspace.PluginsUser;
import ee.ut.cs.rum.mainpage.workspace.TasksShared;
import ee.ut.cs.rum.mainpage.workspace.TasksUser;

@SuppressWarnings("serial")
public class RumNavigator extends Navigator {

	private About aboutPanel;
	private CreateAccount createAccountPanel;
	private Login loginPanel;
	private RecoverAccount recoverAccountPanel;
	
	private TasksUser tasksUserPanel;
	private FilesUser filesUserPanel;
	private PluginsUser pluginsUserPanel;
	private TasksShared tasksSharedPanel;
	private FilesShared filesSharedPanel;
	private Accounts accountsPanel;
	private Groups groupsPanel;
	private PluginsAdmin pluginsAdminPanel;
	private TasksAdmin tasksAdminPanel;
	private Settings settingsPanel;
	private Log logPanel;
	
	
	public RumNavigator(UI ui, SingleComponentContainer container) {
		super(ui, container);
		
		RumUI currentUI = ((RumUI) UI.getCurrent());
		
		aboutPanel = new About();
		this.addView(About.NAME, aboutPanel);
		
		createAccountPanel = new CreateAccount();
		this.addView(CreateAccount.NAME, createAccountPanel);
		
		loginPanel = new Login();
		this.addView(Login.NAME, loginPanel);
		
		recoverAccountPanel = new RecoverAccount();
		this.addView(RecoverAccount.NAME, recoverAccountPanel);
		
		//Sidebar views
		tasksUserPanel = new TasksUser();
		this.addView(TasksUser.NAME, tasksUserPanel);
		
		filesUserPanel = new FilesUser();
		this.addView(FilesUser.NAME, filesUserPanel);
		
		pluginsUserPanel = new PluginsUser();
		this.addView(PluginsUser.NAME, pluginsUserPanel);
		
		tasksSharedPanel = new TasksShared();
		this.addView(TasksShared.NAME, tasksSharedPanel);
		
		filesSharedPanel = new FilesShared();
		this.addView(FilesShared.NAME, filesSharedPanel);
		
		accountsPanel = new Accounts();
		this.addView(Accounts.NAME, accountsPanel);
		
		groupsPanel = new Groups();
		this.addView(Groups.NAME, groupsPanel);
		
		pluginsAdminPanel = new PluginsAdmin();
		this.addView(PluginsAdmin.NAME, pluginsAdminPanel);
		
		tasksAdminPanel = new TasksAdmin();
		this.addView(TasksAdmin.NAME, tasksAdminPanel);
		
		settingsPanel = new Settings();
		this.addView(Settings.NAME, settingsPanel);
		
		logPanel = new Log();
		this.addView(Log.NAME, logPanel);
		
		
		this.addViewChangeListener(new ViewChangeListener() {
			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				String viewName = event.getViewName();
				if (currentUI.getCurrentUser() == null && !(viewName == About.NAME || viewName == CreateAccount.NAME || viewName == Login.NAME || viewName == RecoverAccount.NAME)) {
					String fragmentAndParameters = viewName + "/" + event.getParameters();
					getUI().getNavigator().getDisplay().showView(new Login("Please log in to access: " + fragmentAndParameters, fragmentAndParameters));
					
					return false;
				} else {
					if (currentUI.getHeader().getCurrentlyPressed() != null) {
						currentUI.getHeader().getCurrentlyPressed().setEnabled(true);
					}
					if (currentUI.getSideBar().getCurrentlyPressed() != null) {
						currentUI.getSideBar().getCurrentlyPressed().setEnabled(true);
					}
					return true;
				}
			}
			
			@Override
			public void afterViewChange(ViewChangeEvent event) {
			}
		});
		
	}

	public About getAboutPanel() {
		return aboutPanel;
	}
	
	public CreateAccount getCreateAccountPanel() {
		return createAccountPanel;
	}

	public Login getLoginPanel() {
		return loginPanel;
	}

	public RecoverAccount getRecoverAccountPanel() {
		return recoverAccountPanel;
	}

	public TasksUser getTasksUserPanel() {
		return tasksUserPanel;
	}

	public FilesUser getFilesUserPanel() {
		return filesUserPanel;
	}

	public PluginsUser getPluginsUserPanel() {
		return pluginsUserPanel;
	}

	public TasksShared getTasksSharedPanel() {
		return tasksSharedPanel;
	}

	public FilesShared getFilesSharedPanel() {
		return filesSharedPanel;
	}

	public Accounts getAccountsPanel() {
		return accountsPanel;
	}

	public Groups getGroupsPanel() {
		return groupsPanel;
	}

	public PluginsAdmin getPluginsAdminPanel() {
		return pluginsAdminPanel;
	}

	public TasksAdmin getTasksAdminPanel() {
		return tasksAdminPanel;
	}

	public Settings getSettingsPanel() {
		return settingsPanel;
	}

	public Log getLogPanel() {
		return logPanel;
	}

}
