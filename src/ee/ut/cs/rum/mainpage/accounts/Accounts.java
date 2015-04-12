package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;

@SuppressWarnings("serial")
public class Accounts extends Panel implements View {
	public static final String NAME = "accounts";
	
	private RumUI currentUI; 
	private Button enterButton;
	
	public Accounts() {
		currentUI = ((RumUI) UI.getCurrent());
		
		Table accTable = new Table(null, currentUI.getAccounts());
		accTable.setVisibleColumns(new Object[] {"id","name","email","role"});
		accTable.setSelectable(true);
		accTable.setImmediate(true);
		accTable.setSizeFull();
		this.setContent(accTable);
		
		createEnterButton("Accounts");
	}
	
	private void createEnterButton(String label) {
		enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(Accounts.NAME);
		    }
		});
	}
	
	public Button getEnterButton() {
		return enterButton;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		enterButton.setEnabled(false);
		currentUI.getHeader().setMainPanelButtonPressed();
		currentUI.getSideBar().setCurrentlyPressed(enterButton);
		currentUI.getSideBar().setCurrentView(Accounts.NAME);
	}
}
