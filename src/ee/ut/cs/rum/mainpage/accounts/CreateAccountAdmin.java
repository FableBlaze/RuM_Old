package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;
import ee.ut.cs.rum.domain.Account;
import ee.ut.cs.rum.frontpage.RePasswordValidator;

@SuppressWarnings("serial")
public class CreateAccountAdmin extends GridLayout {
	public static final String NAME = "createAccountAdmin";
	
	private BeanItem<Account> accountItem;
	private FieldGroup binder;

	private RumUI currentUI;

	private TextField accName;
	private TextField accEmail;
	private PasswordField accPassword;
	private PasswordField rePassword;
	private TextField accRole;
	
	public CreateAccountAdmin() {
		super(1,7);
		
		accountItem = new BeanItem<Account>(new Account());
		currentUI = ((RumUI) UI.getCurrent());
		
		Label title = new Label("Enter new account details");
		this.addComponent(title, 0, 0);
		this.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

		accName = new TextField("Account name");
		accName.setImmediate(true);
		accName.setValidationVisible(false);
		accName.setNullRepresentation("");
		accName.addValidator(new BeanValidator(Account.class, "name"));
		this.addComponent(accName, 0, 1);
		this.setComponentAlignment(accName, Alignment.MIDDLE_CENTER);

		accEmail = new TextField("Account email");
		accEmail.setImmediate(true);
		accEmail.setValidationVisible(false);
		accEmail.setNullRepresentation("");
		accEmail.addValidator(new BeanValidator(Account.class, "email"));
		this.addComponent(accEmail, 0, 2);
		this.setComponentAlignment(accEmail, Alignment.MIDDLE_CENTER);

		accPassword = new PasswordField("Account password");
		accPassword.setImmediate(true);
		accPassword.setValidationVisible(false);
		accPassword.setNullRepresentation("");
		accPassword.addValidator(new BeanValidator(Account.class, "password"));
		this.addComponent(accPassword, 0, 3);
		this.setComponentAlignment(accPassword, Alignment.MIDDLE_CENTER);

		rePassword = new PasswordField("Re-enter password");
		rePassword.setImmediate(true);
		rePassword.setValidationVisible(false);
		rePassword.setNullRepresentation("");
		rePassword.addValidator(new BeanValidator(Account.class, "password"));
		this.addComponent(rePassword, 0, 4);
		this.setComponentAlignment(rePassword, Alignment.MIDDLE_CENTER);

		accRole = new TextField("Account Role");
		accRole.setImmediate(true);
		accRole.setValidationVisible(false);
		accRole.setNullRepresentation("");
		accRole.addValidator(new BeanValidator(Account.class, "role"));
		this.addComponent(accRole, 0, 5);
		this.setComponentAlignment(accRole, Alignment.MIDDLE_CENTER);
		
		Button newAccButton = createNewAccButton();
		this.addComponent(newAccButton, 0, 6);
		this.setComponentAlignment(newAccButton, Alignment.MIDDLE_CENTER);
		
		accPassword.addValidator(new RePasswordValidator("Passwords must match", accPassword, rePassword));
		rePassword.addValidator(new RePasswordValidator("Passwords must match", accPassword, rePassword));

		binder = new FieldGroup(accountItem);
		binder.bind(accName, "name");
		binder.bind(accEmail, "email");
		binder.bind(accPassword, "password");
		binder.bind(accRole, "role");

		this.setWidth("100%");
		this.setColumnExpandRatio(0, 1);
		
	}
	
	private Button createNewAccButton() {
		Button button = new Button("Create account");
		button.setImmediate(true);
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				accName.setValidationVisible(true);
				accEmail.setValidationVisible(true);
				accPassword.setValidationVisible(true);
				rePassword.setValidationVisible(true);
				accRole.setValidationVisible(true);

				try {
					accountItem.getBean().setIsDisabled(false);
					binder.commit();
					currentUI.getAccounts().addEntity(accountItem.getBean());
					accountItem = new BeanItem<Account>(new Account());
					binder.clear();
					rePassword.clear();
					binder = new FieldGroup(accountItem);
					binder.bind(accName, "name");
					binder.bind(accEmail, "email");
					binder.bind(accPassword, "password");
					binder.bind(accRole, "role");
					accName.setValidationVisible(false);
					accEmail.setValidationVisible(false);
					accPassword.setValidationVisible(false);
					rePassword.setValidationVisible(false);
					accRole.setValidationVisible(false);
					Notification.show("Account created!",  Notification.Type.HUMANIZED_MESSAGE);
				} catch (CommitException e) {
					Notification.show("Account details contain errors", "Click to dismiss", Notification.Type.ERROR_MESSAGE);
					e.printStackTrace();
					System.out.println(e);
				}
			}
		});
		return button;
	}
}
