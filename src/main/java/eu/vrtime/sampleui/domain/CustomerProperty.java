package eu.vrtime.sampleui.domain;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.util.Assert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class CustomerProperty implements Value<CustomerProperty> {

	private String name;
	private String value;

	public CustomerProperty(final String name, final String value) {
		Assert.notNull(name, "name must not be null");
		Assert.notNull(value, "value must not be null");
		this.name=name;
		this.value=value;
	}

	@Override
	public boolean sameValueAs(CustomerProperty other) {
		return other != null && (name.equals(other.name) && value.equals(other.value));
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).append(value).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		final CustomerProperty other = (CustomerProperty) obj;

		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "CustomerProperty [name=" + name + ", value=" + value + "]";
	}

	CustomerProperty() {
	}

}
