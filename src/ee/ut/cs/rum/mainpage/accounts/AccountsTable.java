package ee.ut.cs.rum.mainpage.accounts;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

import ee.ut.cs.rum.RumUI;

@SuppressWarnings("serial")
public class AccountsTable extends Table {

	public AccountsTable(RumUI currentUI) {
		super(null, (currentUI.getAccounts()));
		this.setVisibleColumns(new Object[] {"id","name","email","role"});

		this.addGeneratedColumn("isDisabled", new ColumnGenerator() {
			@Override
			public Object generateCell(final Table source, final Object itemId, Object columnId) {
				CheckBox checkBox = new CheckBox();
				checkBox.setPropertyDataSource(source.getContainerDataSource().getItem(itemId).getItemProperty("isDisabled"));
				checkBox.setEnabled(false);
				return checkBox;
			}
		});
		
		this.addGeneratedColumn("modify", new ColumnGenerator() {
			@Override
			public Object generateCell(final Table source, final Object itemId, Object columnId) {
				Button button = new Button("Modify");
				button.addClickListener(new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						currentUI.getRumNavigator().navigateTo(Accounts.NAME+"/"+AccountDetails.NAME+"/"+itemId);
					}
				});

				return button;
			}
		});

		this.setImmediate(true);
		this.setSizeFull();
	}
}
