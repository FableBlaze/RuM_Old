package ee.ut.cs.rum.frontpage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;

@SuppressWarnings("serial")
public class CreateAccount extends Panel implements View {
	public static final String NAME = "createAccount";

	private RumUI currentUI; 
	private Button enterButton;
	
	public CreateAccount() {
		currentUI = ((RumUI) UI.getCurrent());
		
		GridLayout layout = new GridLayout(1, 6);
		
		Label title = new Label("Enter new account details");
		layout.addComponent(title, 0, 0);
		layout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		
		TextField accName = new TextField("Account name");
		layout.addComponent(accName, 0, 1);
		layout.setComponentAlignment(accName, Alignment.MIDDLE_CENTER);
		
		TextField accEmail = new TextField("Account email");
		layout.addComponent(accEmail, 0, 2);
		layout.setComponentAlignment(accEmail, Alignment.MIDDLE_CENTER);
		
		PasswordField accPassword = new PasswordField("Account password");
		layout.addComponent(accPassword, 0, 3);
		layout.setComponentAlignment(accPassword, Alignment.MIDDLE_CENTER);
		
		PasswordField rePassword = new PasswordField("Re-enter password");
		layout.addComponent(rePassword, 0, 4);
		layout.setComponentAlignment(rePassword, Alignment.MIDDLE_CENTER);
		
		Button createButton = new Button("Create account");
		layout.addComponent(createButton, 0, 5);
		layout.setComponentAlignment(createButton, Alignment.MIDDLE_CENTER);
		
		layout.setWidth("100%");
		layout.setColumnExpandRatio(0, 1);
		this.setContent(layout);
		
		createEnterButton("Create new Account");
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
