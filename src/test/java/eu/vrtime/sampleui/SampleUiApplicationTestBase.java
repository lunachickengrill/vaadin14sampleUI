package eu.vrtime.sampleui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import eu.vrtime.sampleui.domain.Customer;
import eu.vrtime.sampleui.domain.CustomerProperty;
import eu.vrtime.sampleui.infrastructure.CustomerRepository;
import eu.vrtime.sampleui.infrastructure.ProductRepository;

@SpringBootTest
public abstract class SampleUiApplicationTestBase {
	
	public static final String CUST1_ID = "xxxx";
	public static final String CUST1_FN = "Du";
	public static final String CUST1_LN = "Wappler";
	public static final String CUST1_PROP = "active";
	public static final String CUST1_VALUE = "true";
	
	@BeforeEach
	public void createTestCustomer() {
		Customer cust = new Customer(CUST1_ID, CUST1_FN, CUST1_LN);
		cust.addProperty(new CustomerProperty(CUST1_PROP, CUST1_VALUE));
		customerRepo.saveAndFlush(cust);
	}
	
	@AfterEach
	public void deleteTestCustomer() {
		Customer dbCust = customerRepo.findByCustomerIdEqualsIgnoreCase(CUST1_ID);
		customerRepo.delete(dbCust);
		customerRepo.flush();
	}
	
	@Autowired
	public CustomerRepository customerRepo;
	
	@Autowired
	public ProductRepository productRepo;
	
	@Test
	void contextLoads() {
	}

}
