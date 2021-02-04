package eu.vrtime.sampleui.infrastructure;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import eu.vrtime.sampleui.domain.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSpecification implements Specification<Customer> {

	private static final long serialVersionUID = 1L;

	private String customerId;
	private String firstName;
	private String lastName;

	public CustomerSpecification() {
		super();
	}

	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate predicate = criteriaBuilder.conjunction();

		if (customerId != null) {
			predicate.getExpressions().add(criteriaBuilder.like(criteriaBuilder.lower(root.get("customerId")),
					customerId.toLowerCase() + "%"));
		}

		if (firstName != null) {
			predicate.getExpressions().add(
					criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), firstName.toLowerCase() + "%"));
		}

		if (lastName != null) {
			predicate.getExpressions().add(
					criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), lastName.toLowerCase() + "%"));
		}

		return predicate;
	}

}
