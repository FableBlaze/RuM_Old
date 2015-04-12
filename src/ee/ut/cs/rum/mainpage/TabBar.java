package ee.ut.cs.rum.mainpage;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TabBar extends VerticalLayout {
	private Label label;
	private VerticalLayout mainLayout;
	private VerticalLayout buttonLayout;
	private VerticalLayout tabLayout;
	private Button currentlyPressed;
	
	public TabBar() {
		
		label= new Label();
		this.addComponent(label);
		
		buttonLayout = new VerticalLayout ();
		this.addComponent(buttonLayout);
		
		tabLayout = new VerticalLayout ();
		this.addComponent(tabLayout);
		
		this.setSizeUndefined();
	}
	
	public void setCaption(String caption) {
		this.label.setCaption(caption);
	}
	
	public void addButton(Button button) {
		buttonLayout.addComponent(button);
	}
	
	public void addTab(Button button) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(button);
		layout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
		Button closeTabButton = new Button("X");
		layout.addComponent(closeTabButton);
	}
	
	public Button getCurrentlyPressed() {
		return currentlyPressed;
	}
	
	public void setCurrentlyPressed(Button currentlyPressed) {
		this.currentlyPressed = currentlyPressed;
	}
}
