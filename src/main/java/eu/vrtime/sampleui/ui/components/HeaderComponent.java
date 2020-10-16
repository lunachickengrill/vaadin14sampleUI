package eu.vrtime.sampleui.ui.components;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

//@SpringComponent causes this strange assertion error
public class HeaderComponent extends HorizontalLayout {

	private static final long serialVersionUID = 1L;
	
	public HeaderComponent() {

		Label heading = new Label("a so a leiwande app");
		add(heading);
		setClassName("header");

		setWidthFull();
		setHeight("132px");
		setClassName("header");
	}
	

	

}
