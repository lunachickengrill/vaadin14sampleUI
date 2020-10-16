package eu.vrtime.sampleui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import eu.vrtime.sampleui.domain.Customer;
import eu.vrtime.sampleui.infrastructure.CustomerSpecification;

public class RepositoryTest extends SampleUiApplicationTestBase {
	
	@Test
	public void testSpecificationQuery_Success() {
		CustomerSpecification spec = new CustomerSpecification();
		spec.setCustomerId(CUST1_ID);
		List<Customer> dbCusts = customerRepo.findAll(spec);
		assertTrue(dbCusts.size()>0,"nothing found");
	}

}
