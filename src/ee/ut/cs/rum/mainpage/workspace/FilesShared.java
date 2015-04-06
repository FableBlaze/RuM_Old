package ee.ut.cs.rum.mainpage.workspace;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;

@SuppressWarnings("serial")
public class FilesShared extends Panel implements View {
	public static final String NAME = "filesShared";
	
	private RumUI currentUI; 
	private Button enterButton;
	
	public FilesShared() {
		currentUI = ((RumUI) UI.getCurrent());
		
		Label aboutText = new Label("See files of your friends... like Facebook, but for process mining");
		aboutText.setSizeUndefined();
		this.setContent(aboutText);
		
		createEnterButton("Shared files");
	}
	
	private void createEnterButton(String label) {
		enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(FilesShared.NAME);
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
		currentUI.getSideBar().setCurrentView(FilesShared.NAME);
	}
}
