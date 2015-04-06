package ee.ut.cs.rum;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;

import ee.ut.cs.rum.mainpage.SideBar;

@SuppressWarnings("serial")
public class MainLayout extends GridLayout implements ComponentContainer {
	
	private GridLayout contentLayout;
	private SideBar sideBar;
	private Panel content;
	private Header header;
	private Footer footer;
	
	public MainLayout() {
		this.setRows(3);
		
		contentLayout = new GridLayout(2, 1);
		content = new Panel();
		contentLayout.addComponent(content, 1, 0);
		contentLayout.setSizeFull();
		contentLayout.setColumnExpandRatio(1, 1);
		contentLayout.setImmediate(true);
		
		this.addComponent(contentLayout, 0, 1);
		
		footer = new Footer();
		this.addComponent(footer, 0, 2);
		
		this.setWidth("100%");
	}
	
	public void initSideBar() {
		sideBar = new SideBar();
		contentLayout.addComponent(sideBar, 0, 0);
	}
	
	public void initHeader() {
		header = new Header();
		this.addComponent(header, 0, 0);
	}
	
	public Panel getContent() {
		return content;
	}
	
	public Header getHeader() {
		return header;
	}
	
	public SideBar getSideBar() {
		return sideBar;
	}
	
}
