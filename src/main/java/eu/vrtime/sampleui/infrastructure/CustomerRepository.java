package eu.vrtime.sampleui.infrastructure;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import eu.vrtime.sampleui.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	public List<Customer> findByCustomerIdStartingWithIgnoreCaseOrderByCustomerIdDesc(final String customerId);

	public List<Customer> findByFirstNameIgnoreCaseOrderByCustomerIdDesc(final String firstName);

	public List<Customer> findByLastNameIgnoreCaseOrderByCustomerIdDesc(final String lastName);

	public Customer findByCustomerIdEqualsIgnoreCase(final String customerId);

	public List<Customer> findAll(Specification<Customer> spec);

}
