package eu.vrtime.sampleui.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "testview")
@UIScope
public class TestAppView extends AppLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestAppView() {
		setPrimarySection(AppLayout.Section.DRAWER);
		Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
        img.setHeight("44px");
        addToNavbar(new DrawerToggle(), img);
        Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
		
//		Image img = new Image("https://i.imgur.com/GPpnszs.png", "Vaadin Logo");
//        Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
//        img.setHeight("44px");
//        addToNavbar(img, tabs);
	}

}
