package ee.ut.cs.rum.frontpage;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
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
		accName.setImmediate(true);
		accName.setRequired(true);
		accName.setValidationVisible(false);
		accName.addValidator(new StringLengthValidator("Account name must contain at least 4 characters", 4, null, false));
		layout.addComponent(accName, 0, 1);
		layout.setComponentAlignment(accName, Alignment.MIDDLE_CENTER);
		
		accEmail = new TextField("Account email");
		accEmail.setImmediate(true);
		accEmail.setRequired(true);
		accEmail.setValidationVisible(false);
		accEmail.setRequiredError("Account email is required");
		accEmail.addValidator(new EmailValidator("Invalid e-mail adress!"));
		layout.addComponent(accEmail, 0, 2);
		layout.setComponentAlignment(accEmail, Alignment.MIDDLE_CENTER);
		
		accPassword = new PasswordField("Account password");
		accPassword.setImmediate(true);
		accPassword.setRequired(true);
		accPassword.setValidationVisible(false);
		accPassword.addValidator(new StringLengthValidator("Account password must contain at least 4 characters", 4, null, false));
		layout.addComponent(accPassword, 0, 3);
		layout.setComponentAlignment(accPassword, Alignment.MIDDLE_CENTER);
		
		rePassword = new PasswordField("Re-enter password");
		rePassword.setImmediate(true);
		rePassword.setRequired(true);
		rePassword.setValidationVisible(false);
		layout.addComponent(rePassword, 0, 4);
		layout.setComponentAlignment(rePassword, Alignment.MIDDLE_CENTER);
		
		accPassword.addValidator(new RePasswordValidator("Passwords must match", accPassword, rePassword));
		rePassword.addValidator(new RePasswordValidator("Passwords must match", accPassword, rePassword));
		
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
		    			accName.setValidationVisible(false);
		    			accEmail.clear();
		    			accEmail.setValidationVisible(false);
		    			accPassword.setValue("");
		    			accPassword.setValidationVisible(false);
		    			rePassword.setValue("");
		    			rePassword.setValidationVisible(false);
		    		}
		    		
				}
		    }
		});
		return button;
	}
	
	private boolean validateFields() {
		try {
			accName.validate();
			accEmail.validate();
			accPassword.validate();
			rePassword.validate();
			return true;
		} catch (InvalidValueException e) {
			accName.setValidationVisible(true);
			accEmail.setValidationVisible(true);
			accPassword.setValidationVisible(true);
			rePassword.setValidationVisible(true);
			Notification.show(e.getMessage(), "Click to dismiss", Notification.Type.ERROR_MESSAGE);
			return false;
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
