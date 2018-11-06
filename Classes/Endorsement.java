
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.joda.time.LocalDate;

@Entity
@Access(value = AccessType.PROPERTY)
public class Endorsement extends DomainEntity {

	private String		comment;
	private LocalDate	momment;

	private Endorser	endorser;


	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public LocalDate getMomment() {
		return this.momment;
	}

	public void setMomment(final LocalDate momment) {
		this.momment = momment;
	}

	@ManyToOne(optional = false)
	public Endorser getEndorser() {
		return this.endorser;
	}

	public void setEndorser(final Endorser endorser) {
		this.endorser = endorser;
	}

}
