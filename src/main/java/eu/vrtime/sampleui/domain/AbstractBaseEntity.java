package eu.vrtime.sampleui.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractBaseEntity implements Serializable{

	private static final long serialVersionUID = 1202730850613725533L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long oid;

	@Column(name = "create_date", nullable = false, updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createDate;

	@Column(name = "last_modified", nullable = false, updatable = true)
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModified;

	protected Long getOId() {
		return this.oid;
	}

}
