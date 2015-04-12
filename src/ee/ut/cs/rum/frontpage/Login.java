package ee.ut.cs.rum.frontpage;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

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
public class Login extends Panel implements View {
	public static final String NAME = "";

	private RumUI currentUI; 
	private TextField loginUsername;
	private PasswordField loginPassword;
	private Button enterButton;
	
	public Login() {
		buildLoginPanel("Please log in", null);
	}
	
	public Login(String titleText, String fragmentAndParameters) {
		buildLoginPanel(titleText, fragmentAndParameters);
	}
	
	private void buildLoginPanel(String titleText, String fragmentAndParameters) {
		
		currentUI = ((RumUI) UI.getCurrent());
		
		GridLayout layout = new GridLayout(1, 4);
		
		Label title = new Label(titleText);
		layout.addComponent(title, 0, 0);
		layout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		
		loginUsername = new TextField("Username");
		loginUsername.setImmediate(true);
		loginUsername.setNullRepresentation("");
		layout.addComponent(loginUsername, 0, 1);
		layout.setComponentAlignment(loginUsername, Alignment.MIDDLE_CENTER);
		
		loginPassword = new PasswordField("Password");
		loginPassword.setImmediate(true);
		loginPassword.setNullRepresentation("");
		layout.addComponent(loginPassword, 0, 2);
		layout.setComponentAlignment(loginPassword, Alignment.MIDDLE_CENTER);
		
		Button loginButton = new Button("Login");
		loginButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	CriteriaBuilder criteriaBuilder = currentUI.getEntityManager().getCriteriaBuilder();
				CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
				Root<Account> root = query.from(Account.class);
				Path<String> name = root.get("name");
				Path<String> password = root.get("password");
				query.select((root)).where(
					    criteriaBuilder.and(
					        criteriaBuilder.equal(name, loginUsername.getValue()),
					        criteriaBuilder.equal(password, loginPassword.getValue())
					    )
					);
				
				try {
					Account queryResult = currentUI.getEntityManager().createQuery(query).getSingleResult();
					currentUI.setCurrentUser(queryResult);
					if (fragmentAndParameters == null) {
			    		getUI().getNavigator().navigateTo(currentUI.getSideBar().getCurrentView());
			    	} else {
			    		getUI().getNavigator().navigateTo(fragmentAndParameters);
					}
					loginUsername.clear();
					loginPassword.clear();
				} catch (NoResultException e) {
					Notification.show("Wrong username or password", "Click to dismiss", Notification.Type.ERROR_MESSAGE);
				}
				
		    }
		});
		layout.addComponent(loginButton, 0, 3);
		layout.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);
		
		layout.setWidth("100%");
		layout.setColumnExpandRatio(0, 1);
		this.setContent(layout);
		
		createEnterButton("Login");
	}
	
	private void createEnterButton(String label) {
		enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(Login.NAME);
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
