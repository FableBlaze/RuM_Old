package ee.ut.cs.rum.mainpage;

import java.util.ArrayList;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;


@SuppressWarnings("serial")
public class TabBar extends VerticalLayout {
	private Label title;
	private VerticalLayout buttonsSection;
	private VerticalLayout tabsSection;
	private Button currentlyPressed;

	private ArrayList<String> currentTabs;
	
	public TabBar() {
		
		currentTabs=new ArrayList<String>();
		
		title= new Label();
		this.addComponent(title);
		
		buttonsSection = new VerticalLayout ();
		this.addComponent(buttonsSection);
		
		Label label = new Label("Opened tabs:");
		this.addComponent(label);
		
		tabsSection = new VerticalLayout ();
		tabsSection.setImmediate(true);
		this.addComponent(tabsSection);
		
		this.setSizeUndefined();
	}
	
	public void setCaption(String caption) {
		this.title.setCaption(caption);
	}
	
	public void addButton(Button button) {
		buttonsSection.addComponent(button);
	}
	
	
	public void addTab(Button button, Button tabBarEnterButton, String tabId) {
		HorizontalLayout tabLayout = new HorizontalLayout();
		tabLayout.addComponent(button);
		button.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	setCurrentlyPressed(event.getButton());
		    }
		});
		tabLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
		
		Button closeTabButton = new Button("X");
		closeTabButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	tabsSection.removeComponent(event.getButton().getParent());
		    	currentTabs.remove(tabId);
		    	if (currentlyPressed == button) {
		    		tabBarEnterButton.click();
				}
		    }
		});
		tabLayout.addComponent(closeTabButton);
		tabsSection.addComponent(tabLayout);
		currentTabs.add(tabId);
	}
	
	public Button getCurrentlyPressed() {
		return currentlyPressed;
	}
	
	public void setCurrentlyPressed(Button currentlyPressed) {
		if (this.currentlyPressed != null) {
			this.currentlyPressed.setEnabled(true);
		}
		this.currentlyPressed = currentlyPressed;
		currentlyPressed.setEnabled(false);
	}
	
	public ArrayList<String> getCurrentTabs() {
		return currentTabs;
	}
}
