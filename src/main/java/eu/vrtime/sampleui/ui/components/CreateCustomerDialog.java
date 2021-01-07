package eu.vrtime.sampleui.ui.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import antlr.debug.Event;
import eu.vrtime.sampleui.domain.Customer;
import eu.vrtime.sampleui.domain.CustomerProperty;
import eu.vrtime.sampleui.infrastructure.CustomerRepository;

public class CreateCustomerDialog extends Dialog {

	private static final long serialVersionUID = 1L;
	private Binder<Customer> binder = new Binder<Customer>();
	private TextField customerId = new TextField();
	private TextField firstName = new TextField();
	private TextField lastName = new TextField();
	private FormLayout formLayout = new FormLayout();
	private Grid<CustomerProperty> propertyGrid;

	private CustomerRepository customerRepo;

	public CreateCustomerDialog() {
		setHeight("600px");
		setWidth("450px");
		

		addFormItems();
		add(new Label("Create a new Customer"));
		formLayout.addClassName("background-dialog");
		formLayout.addClassName("form-default-noborder-flat");
		formLayout.addClassName("form-centered-content");
		add(formLayout);

		propertyGrid = createPropertiesGrid();
		add(propertyGrid);
		add(createSaveButton());
		add(createNewPropertyButton());

	}
	
	private Button createSaveButton() {
		Button btn = new Button("Save");
		btn.addClassName("button-small");
		btn.addClickListener(event -> {Notification.show("debug save",3000, Notification.Position.BOTTOM_END);});
		return btn;
	}
	
	private Button createNewPropertyButton() {
		Button btn = new Button("Add Property");
		btn.addClassName("button-small");
		btn.addClickListener(event -> {Notification.show("debug create", 3000, Notification.Position.BOTTOM_END);});
		return btn;
	}

	private void addFormItems() {
		formLayout.addFormItem(customerId, "Customer ID");
		formLayout.addFormItem(firstName, "First Name");
		formLayout.addFormItem(lastName, "Last Name");

	}

	private Grid<CustomerProperty> createPropertiesGrid() {
		Grid<CustomerProperty> grid = new Grid<>();
		grid.addColumn(CustomerProperty::getName).setHeader("Name").setWidth("250px");
		grid.addColumn(CustomerProperty::getValue).setHeader("Value").setWidth("100px");
		grid.addItemDoubleClickListener(event -> {
			Notification.show(event.getItem().toString(), 3000, Notification.Position.MIDDLE);
		});
		grid.setWidth("360px");
		grid.setHeight("120px");
		grid.setVerticalScrollingEnabled(true);
		grid.addClassName("properties-table-center");
		return grid;
	}

}
