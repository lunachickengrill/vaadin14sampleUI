package eu.vrtime.sampleui.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.NoTheme;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import eu.vrtime.sampleui.infrastructure.CustomerRepository;
import eu.vrtime.sampleui.ui.components.FormComponent;
import eu.vrtime.sampleui.ui.components.HeaderComponent;

@Route(value = "")
//@PreserveOnRefresh
@UIScope
@Theme(value = Lumo.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class SampleUI extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private HeaderComponent header;
	private FormComponent form;
	private CustomerRepository customerRepo;

	@Autowired
	public SampleUI(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@PostConstruct
	private void init() {
		header = new HeaderComponent();
		form = new FormComponent(customerRepo);

		add(header, form);
		setHeightFull();
		addClassName("background");
	}

}
