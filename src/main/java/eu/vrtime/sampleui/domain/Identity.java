package eu.vrtime.sampleui.domain;

public interface Identity<T> {

	public boolean sameIdentityAs(T other);

}
