package eu.vrtime.sampleui.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true, updatable = true)
	private String customerId;

	@Column(nullable = true, unique = false, updatable = true)
	private String firstName;

	@Column(nullable = false, unique = false, updatable = true)
	private String lastName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Product> products = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<CustomerProperty> properties = new HashSet<>();

	public Customer(final String customerId, final String firstName, final String lastName) {
		Assert.notNull(customerId, "CustomerId must not be null");
		Assert.notNull(lastName, "LastName must not be null");
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer() {

	}

	public void addProduct(Product product) {
		product.setCustomer(this);
		products.add(product);
	}

	public void removeProduct(Product product) {
		product.setCustomer(null);
		products.remove(product);
	}

	public void addProperty(CustomerProperty property) {
		properties.add(property);
	}

	public void removeProperty(CustomerProperty property) {
		properties.remove(property);
	}

	public void clearProperties() {
		properties.clear();
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", products=" + products + ", properties=" + properties + "]";
	}

}
