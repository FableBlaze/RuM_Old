package ee.ut.cs.rum.mainpage;

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
	
	public TabBar() {
		
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
	
	public void addTab(Button button) {
		HorizontalLayout tabLayout = new HorizontalLayout();
		tabLayout.addComponent(button);
		button.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	currentlyPressed.setEnabled(true);
		    	event.getButton().setEnabled(false);
		    	currentlyPressed = event.getButton();
		    }
		});
		tabLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
		
		Button closeTabButton = new Button("X");
		closeTabButton.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	tabsSection.removeComponent(event.getButton().getParent());
		    }
		});
		tabLayout.addComponent(closeTabButton);
		tabsSection.addComponent(tabLayout);
	}
	
	public Button getCurrentlyPressed() {
		return currentlyPressed;
	}
	
	public void setCurrentlyPressed(Button currentlyPressed) {
		this.currentlyPressed = currentlyPressed;
	}
}
