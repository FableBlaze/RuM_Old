package ee.ut.cs.rum.frontpage;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
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
import ee.ut.cs.rum.domain.Account;

@SuppressWarnings("serial")
public class CreateAccount extends Panel implements View {
	public static final String NAME = "createAccount";

	private BeanItem<Account> accountItem;
	private FieldGroup binder;

	private RumUI currentUI; 
	private Button enterButton;

	private TextField accName;
	private TextField accEmail;
	private PasswordField accPassword;
	private PasswordField rePassword;

	public CreateAccount() {
		accountItem = new BeanItem<Account>(new Account());

		currentUI = ((RumUI) UI.getCurrent());

		GridLayout layout = new GridLayout(1, 6);

		Label title = new Label("Enter new account details");
		layout.addComponent(title, 0, 0);
		layout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);

		accName = new TextField("Account name");
		accName.setImmediate(true);
		accName.setValidationVisible(false);
		accName.setNullRepresentation("");
		accName.addValidator(new BeanValidator(Account.class, "name"));
		layout.addComponent(accName, 0, 1);
		layout.setComponentAlignment(accName, Alignment.MIDDLE_CENTER);

		accEmail = new TextField("Account email");
		accEmail.setImmediate(true);
		accEmail.setValidationVisible(false);
		accEmail.setNullRepresentation("");
		accEmail.addValidator(new BeanValidator(Account.class, "email"));
		layout.addComponent(accEmail, 0, 2);
		layout.setComponentAlignment(accEmail, Alignment.MIDDLE_CENTER);

		accPassword = new PasswordField("Account password");
		accPassword.setImmediate(true);
		accPassword.setValidationVisible(false);
		accPassword.setNullRepresentation("");
		accPassword.addValidator(new BeanValidator(Account.class, "password"));
		layout.addComponent(accPassword, 0, 3);
		layout.setComponentAlignment(accPassword, Alignment.MIDDLE_CENTER);

		rePassword = new PasswordField("Re-enter password");
		rePassword.setImmediate(true);
		rePassword.setValidationVisible(false);
		rePassword.setNullRepresentation("");
		rePassword.addValidator(new BeanValidator(Account.class, "password"));
		layout.addComponent(rePassword, 0, 4);
		layout.setComponentAlignment(rePassword, Alignment.MIDDLE_CENTER);

		accPassword.addValidator(new RePasswordValidator("Passwords must match", accPassword, rePassword));
		rePassword.addValidator(new RePasswordValidator("Passwords must match", accPassword, rePassword));


		Button newAccButton = createNewAccButton();
		layout.addComponent(newAccButton, 0, 5);
		layout.setComponentAlignment(newAccButton, Alignment.MIDDLE_CENTER);


		binder = new FieldGroup(accountItem);
		binder.bind(accName, "name");
		binder.bind(accEmail, "email");
		binder.bind(accPassword, "password");

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
				accName.setValidationVisible(true);
				accEmail.setValidationVisible(true);
				accPassword.setValidationVisible(true);
				rePassword.setValidationVisible(true);

				try {
					accountItem.getBean().setRole("ADMINISTRATOR");
					binder.commit();
					currentUI.getAccounts().addEntity(accountItem.getBean());
					accountItem = new BeanItem<Account>(new Account());
					accName.clear();
					accEmail.clear();
					accPassword.clear();
					rePassword.clear();
					Notification.show("Account ALMOST created!",  Notification.Type.HUMANIZED_MESSAGE);
				} catch (CommitException e) {
					Notification.show("Account details contain errors", "Click to dismiss", Notification.Type.ERROR_MESSAGE);
					e.printStackTrace();
					System.out.println(e);
				}
			}
		});
		return button;
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
