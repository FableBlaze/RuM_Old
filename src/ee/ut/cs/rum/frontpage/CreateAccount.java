package ee.ut.cs.rum.frontpage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;
import ee.ut.cs.rum.utilities.pojos.Account;

@SuppressWarnings("serial")
public class CreateAccount extends Panel implements View {
	public static final String NAME = "createAccount";
	
	private AccountDaoFrontpage accountDao;

	private RumUI currentUI; 
	private Button enterButton;
	
	private TextField accName;
	private TextField accEmail;
	private PasswordField accPassword;
	private PasswordField rePassword;
	
	public CreateAccount() {
		accountDao = new AccountDaoFrontpage();
		currentUI = ((RumUI) UI.getCurrent());
		
		GridLayout layout = new GridLayout(1, 6);
		
		Label title = new Label("Enter new account details");
		layout.addComponent(title, 0, 0);
		layout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		
		accName = new TextField("Account name");
		accName.setRequired(true);
		accName.setImmediate(true);
		accName.setRequiredError("Account name must be filled in!");
		layout.addComponent(accName, 0, 1);
		layout.setComponentAlignment(accName, Alignment.MIDDLE_CENTER);
		
		accEmail = new TextField("Account email");
		accEmail.setRequired(true);
		accEmail.setImmediate(true);
		accEmail.setRequiredError("Account email must be filled in!");
		layout.addComponent(accEmail, 0, 2);
		layout.setComponentAlignment(accEmail, Alignment.MIDDLE_CENTER);
		
		accPassword = new PasswordField("Account password");
		accPassword.setRequired(true);
		accPassword.setImmediate(true);
		accPassword.setRequiredError("Account password must be filled in!");
		layout.addComponent(accPassword, 0, 3);
		layout.setComponentAlignment(accPassword, Alignment.MIDDLE_CENTER);
		
		rePassword = new PasswordField("Re-enter password");
		rePassword.setRequired(true);
		rePassword.setImmediate(true);
		rePassword.setRequiredError("Account password must be filled in!");
		layout.addComponent(rePassword, 0, 4);
		layout.setComponentAlignment(rePassword, Alignment.MIDDLE_CENTER);
		
		Button newAccButton = createNewAccButton();
		layout.addComponent(newAccButton, 0, 5);
		layout.setComponentAlignment(newAccButton, Alignment.MIDDLE_CENTER);
		
		layout.setWidth("100%");
		layout.setColumnExpandRatio(0, 1);
		this.setContent(layout);
		
		createEnterButton("Create new Account");
	}
	
	private Button createNewAccButton() {
		Button button = new Button("Create account");
		button.setImmediate(true);
		button.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	if (validateFields()) {
		    		if (accountDao.createAccount(accName.getValue(), accEmail.getValue(), accPassword.getValue())) {
		    			Notification.show("Account created!",  Notification.Type.HUMANIZED_MESSAGE);
		    			accName.clear();
		    			accEmail.clear();
		    			accPassword.setValue("");
		    			rePassword.setValue("");
		    		}
		    		
				}
		    }
		});
		return button;
	}
	
	private boolean validateFields() {
		if (!accName.isValid() || !accEmail.isValid() || !accPassword.isValid() || !rePassword.isValid()) {
			Notification.show("All fileds are required!", "Click to dismiss", Notification.Type.ERROR_MESSAGE);
			return false;
		} else if (!accPassword.getValue().equals(rePassword.getValue())) {
			Notification.show("Passwords do not match!", "Click to dismiss",  Notification.Type.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	private void createEnterButton(String label) {
		enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(CreateAccount.NAME);
		    }
		});
	}
	
	public Button getEnterButton() {
		return enterButton;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		enterButton.setEnabled(false);
		currentUI.getHeader().setCurrentlyPressed(enterButton);
		
	}
	
}
