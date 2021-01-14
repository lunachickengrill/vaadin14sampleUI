package eu.vrtime.sampleui.ui;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.UIScope;
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
	private VaadinSession session;

	@Autowired
	public SampleUI(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
		session = VaadinSession.getCurrent();
		session.setAttribute("user", new String("tschwaiger"));
		session.setAttribute("date", new Date());
		
	}

	@PostConstruct
	private void init() {
		header = new HeaderComponent();
		form = new FormComponent(customerRepo);

		add(header, form);
		setHeightFull();
		addClassName("background");
		System.out.println(session.getAttribute("user"));
		System.out.println(session.getAttribute("date"));
	}

}
