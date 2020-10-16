package eu.vrtime.sampleui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.vrtime.sampleui.domain.Customer;
import eu.vrtime.sampleui.domain.CustomerProperty;
import eu.vrtime.sampleui.domain.Product;
import eu.vrtime.sampleui.infrastructure.CustomerRepository;

public class DomainTest extends SampleUiApplicationTestBase {

	@Test
	public void testDummyCustomers_Success() {
		assertTrue(customerRepo.count() > 0, "No dummy data");
	}

	@Test
	public void testAddCustomerProperty_Success() {
		Customer dbCust = customerRepo.findByCustomerIdEqualsIgnoreCase(CUST1_ID);
		assertNotNull(dbCust, "customer not found");

		CustomerProperty prop = new CustomerProperty("registered", "false");
		dbCust.addProperty(prop);

		dbCust = customerRepo.saveAndFlush(dbCust);
		assertTrue(dbCust.getProperties().size() > 1);
	}

	@Test
	public void testRemoveCustomerProperty_Success() {
		Customer dbCust = customerRepo.findByCustomerIdEqualsIgnoreCase(CUST1_ID);
		assertNotNull(dbCust, "customer not found");

		dbCust.clearProperties();
		dbCust = customerRepo.saveAndFlush(dbCust);

		assertTrue(dbCust.getProperties().size() == 0);
	}

	@Test
	public void testAddProduct_Success() {
		Customer dbCust = customerRepo.findByCustomerIdEqualsIgnoreCase(CUST1_ID);
		dbCust.addProduct(new Product("4hAUT"));
		dbCust = customerRepo.saveAndFlush(dbCust);

		assertTrue(dbCust.getProducts().size() > 0, "no products");
	}

}
