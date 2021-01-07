package eu.vrtime.sampleui.ui.components;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep.LabelsPosition;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

import eu.vrtime.sampleui.domain.Customer;
import eu.vrtime.sampleui.infrastructure.CustomerRepository;
import eu.vrtime.sampleui.infrastructure.CustomerSpecification;

public class FormComponent extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private TextField customerId = new TextField();
	private TextField firstName = new TextField();
	private TextField lastName = new TextField();
	private Button search;
	private Button clear;
	private Button create;
	private FormLayout layout = new FormLayout();
	private HorizontalLayout actions = new HorizontalLayout();
	private Binder<CustomerSpecification> binder;
	private Grid<Customer> grid;
	private CustomerSpecification spec = new CustomerSpecification();
	private CreateCustomerDialog newCustomerWindow = new CreateCustomerDialog();

	private CustomerRepository customerRepo;

	public FormComponent(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;

		layout.addClassName("form-default-noborder-flat");
		layout.setWidth("700px");

		layout.addFormItem(customerId, "Customer ID");
		layout.addFormItem(firstName, "First Name");
		layout.addFormItem(lastName, "Last Name");

		search = createSearchButton();
		create = createNewCustomerBtn();
		clear = createClearButton();
		grid = createGrid();
		binder = createDataBinder(spec);

		actions.add(search, clear, create);
		add(layout, actions, grid);
	}

	private Binder<CustomerSpecification> createDataBinder(CustomerSpecification spec) {
		Binder<CustomerSpecification> binder = new Binder<CustomerSpecification>();
		binder.forField(customerId).bind(CustomerSpecification::getCustomerId, CustomerSpecification::setCustomerId);
		binder.forField(firstName).bind(CustomerSpecification::getFirstName, CustomerSpecification::setFirstName);
		binder.forField(lastName).bind(CustomerSpecification::getLastName, CustomerSpecification::setLastName);
		binder.setBean(spec);
		return binder;
	}

	private Grid<Customer> createGrid() {
		Grid<Customer> grid = new Grid<Customer>();
		grid.addColumn(Customer::getCustomerId).setHeader("CustomerId");
		grid.addColumn(Customer::getFirstName).setHeader("First Name");
		grid.addColumn(Customer::getLastName).setHeader("Last Name");
		grid.addItemDoubleClickListener(event -> {
			Notification.show(event.getItem().getCustomerId(), 3000, Notification.Position.BOTTOM_END);
		});
		
//		grid.addItemDoubleClickListener(event -> {createDialog((Customer)event.getItem()).open();});
		grid.setWidth("700px");
		grid.addClassName("customer-table");
		return grid;
	}
	
	private Dialog createDialog(final Customer customer) {
		Dialog dg = new Dialog();
		
		dg.add(new Text(customer.getCustomerId()),new Text(customer.getLastName()), new Text(customer.getLastName()));
		dg.setModal(false);
		dg.setDraggable(true);
		dg.setResizable(true);
		dg.add(new Button("Close", e -> dg.close()));
		
		return dg;
	}

	private Button createSearchButton() {
		Button btn = new Button("Search", VaadinIcon.ENTER_ARROW.create());
		btn.addClassName("button-default");
		btn.addClickListener(event -> {
			if (!binder.getBean().getCustomerId().isEmpty()) {
				List<Customer> dbCustomers = customerRepo.findAll(spec);
				if (dbCustomers.size() > 0) {
					grid.setItems(dbCustomers);
				}
			}
		});
		return btn;
	}

	private Button createClearButton() {
		Button btn = new Button("Clear", VaadinIcon.ARROW_BACKWARD.create());
		btn.addClassName("button-default");
		btn.addClickListener(event -> {
			binder.readBean(null);
		});

		return btn;
	}

	private Button createNewCustomerBtn() {
		Button btn = new Button("New Customer", VaadinIcon.STAR_HALF_RIGHT_O.create());
		btn.addClassName("button-default");
		btn.addClickListener(event -> {
			newCustomerWindow.open();
		});
		return btn;
	}

}
