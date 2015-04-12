package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

public class AccountDetails extends GridLayout {
	public static final String NAME = "accountDetails";
	
	public AccountDetails(String id) {
		super(1,7);
		Label title = new Label("View of teh account details: "+ id);
		this.addComponent(title, 0, 0);
		this.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
	}
}
