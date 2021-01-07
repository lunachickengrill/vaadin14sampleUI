package eu.vrtime.sampleui.config;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import eu.vrtime.sampleui.domain.Customer;
import eu.vrtime.sampleui.domain.CustomerProperty;
import eu.vrtime.sampleui.domain.Product;
import eu.vrtime.sampleui.infrastructure.CustomerRepository;
import eu.vrtime.sampleui.infrastructure.ProductRepository;

@Component
public class AutoSetupService {

	private CustomerRepository customerRepo;
	private ProductRepository productRepo;

	private List<Customer> dummyCustomers = Arrays.asList(new Customer("1111", "Ronnie", "Raygun"),
			new Customer("2222", "P.M.", "Satcher"), new Customer("3333", "Infidel", "Castro"),
			new Customer("4444", "Col. Malomar", "Khadaffy"), new Customer("5555", "Ayatollah", "Kookamamie"),
			new Customer("6666", "Mao", "the Pun"), new Customer("7777", "Jimi", "Farmer"),
			new Customer("8888", "Tricky", "Dick"), new Customer("9999", "Mikhail", "Gorabachef"),
			new Customer("1010", null, "Ghanji"));

	@Autowired
	public AutoSetupService(CustomerRepository customerRepo, ProductRepository productRepo) {
		this.customerRepo = customerRepo;
		this.productRepo = productRepo;
	}

	@PostConstruct
	private void setup() {

		dummyCustomers.stream().forEach(c -> c.addProperty(new CustomerProperty("active", "true")));
		dummyCustomers.stream().forEach(c -> c.addProduct(new Product("4hAUT")));
		customerRepo.saveAll(dummyCustomers);
		customerRepo.flush();

	}

}
