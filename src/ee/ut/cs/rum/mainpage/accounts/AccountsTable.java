package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

import ee.ut.cs.rum.RumUI;

@SuppressWarnings("serial")
public class AccountsTable extends Table {
	
	public AccountsTable(RumUI currentUI) {
		super(null, (currentUI.getAccounts()));
		
		this.setVisibleColumns(new Object[] {"id","name","email","role"});
		this.setSelectable(true);
		this.setImmediate(true);
		this.setSizeFull();
	}
}
