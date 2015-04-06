package ee.ut.cs.rum.frontpage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

import ee.ut.cs.rum.RumUI;

@SuppressWarnings("serial")
public class About extends Panel implements View {
	public static final String NAME = "about";
	
	private RumUI currentUI; 
	private Button enterButton;
	
	public About() {
		currentUI = ((RumUI) UI.getCurrent());
		
		GridLayout layout = new GridLayout(1, 2);
		
		Label title = new Label("About RuM");
		title.setSizeUndefined();
		layout.addComponent(title, 0, 0);
		
		Label aboutText = new Label("Imagine a wonderful text about RuM...");
		aboutText.setSizeUndefined();
		layout.addComponent(aboutText, 0, 1);
		
		layout.setWidth("100%");
		layout.setColumnExpandRatio(0, 1);
		this.setContent(layout);
		
		createEnterButton("About RuM");
	}
	
	private void createEnterButton(String label) {
		enterButton = new Button(label);
		enterButton.setImmediate(true);
		enterButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentUI.getRumNavigator().navigateTo(About.NAME);
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
