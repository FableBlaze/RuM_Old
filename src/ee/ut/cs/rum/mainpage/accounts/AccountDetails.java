package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import ee.ut.cs.rum.RumUI;
import ee.ut.cs.rum.domain.Account;

@SuppressWarnings("serial")
public class AccountDetails extends GridLayout {
	public static final String NAME = "accountDetails";
	
	EntityItem<Account> accountItem;
	private FieldGroup binder;

	private RumUI currentUI;

	private TextField accName;
	private TextField accEmail;
	private TextField accRole;
	private CheckBox isDisabled;
	
	public AccountDetails(String id) {
		super(1,5);
		
		currentUI = ((RumUI) UI.getCurrent());
		accountItem = currentUI.getAccount(id.substring(id.lastIndexOf("/")+1));
		
		Label title = new Label("View of teh account details: "+ id);
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

		accRole = new TextField("Account Role");
		accRole.setImmediate(true);
		accRole.setValidationVisible(false);
		accRole.setNullRepresentation("");
		accRole.addValidator(new BeanValidator(Account.class, "role"));
		this.addComponent(accRole, 0, 3);
		this.setComponentAlignment(accRole, Alignment.MIDDLE_CENTER);
		
		isDisabled = new CheckBox("Disabled");
		isDisabled.setImmediate(true);
		isDisabled.setValidationVisible(false);
		this.addComponent(isDisabled, 0, 4);
		this.setComponentAlignment(isDisabled, Alignment.MIDDLE_CENTER);

		binder = new FieldGroup(accountItem);
		binder.bind(accName, "name");
		binder.bind(accEmail, "email");
		binder.bind(accRole, "role");
		binder.bind(isDisabled, "isDisabled");
		binder.setBuffered(false);
		

		this.setWidth("100%");
		this.setColumnExpandRatio(0, 1);
		
		
	}
}
