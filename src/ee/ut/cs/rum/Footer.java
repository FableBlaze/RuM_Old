package ee.ut.cs.rum;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class Footer extends Panel {

	public Footer() {
		GridLayout layout = new GridLayout(1, 3);
		
		Label appName = new Label("RuM - Rule Mining platform");
		appName.setSizeUndefined();
		layout.addComponent(appName, 0, 0);
		layout.setComponentAlignment(appName, Alignment.TOP_RIGHT);
		
		Label appVersion = new Label("version 0.03");
		appVersion.setSizeUndefined();
		layout.addComponent(appVersion, 0, 1);
		layout.setComponentAlignment(appVersion, Alignment.TOP_RIGHT);
		
		Label appDate = new Label("25.03.2015");
		appDate.setSizeUndefined();
		layout.addComponent(appDate, 0, 2);
		layout.setComponentAlignment(appDate, Alignment.TOP_RIGHT);
		
		layout.setColumnExpandRatio(0, 1);
		layout.setWidth("100%");
		this.setContent(layout);
		
	}

}
