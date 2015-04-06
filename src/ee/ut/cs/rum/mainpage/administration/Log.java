package ee.ut.cs.rum.mainpage.administration;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;

@SuppressWarnings("serial")
public class Log extends Panel implements View {
	public static final String NAME = "log";
	
	private RumUI currentUI; 
	private Button enterButton;
	
	public Log() {
		currentUI = ((RumUI) UI.getCurrent());
		
		Label aboutText = new Label("See RuM logs... find problems. Export logs... find rules - It would be odd if a platform for rule mining could not be mined ;)");
		aboutText.setSizeUndefined();
		this.setContent(aboutText);
		
		createEnterButton("System log");
	}
	
	private void createEnterButton(String label) {
		enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(Log.NAME);
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
		currentUI.getSideBar().setCurrentView(Log.NAME);
	}
}
