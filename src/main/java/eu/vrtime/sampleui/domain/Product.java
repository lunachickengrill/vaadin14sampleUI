package eu.vrtime.sampleui.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = false, updatable = true)
	private String productId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public Product(final String productId) {
		Assert.notNull(productId, "productId must not be null");
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", customer=" + customer + "]";
	}

	Product() {
	}

}
