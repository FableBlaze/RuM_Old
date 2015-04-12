package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class CreateAccountAdmin extends GridLayout {
	public static final String NAME = "createAccountAdmin";
	
	public CreateAccountAdmin() {
		super(1,7);
		Label title = new Label("Enter new account details");
		this.addComponent(title, 0, 0);
		this.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
	}
}
