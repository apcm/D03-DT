
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
public class Phase extends DomainEntity {

	private String		title;
	private String		description;
	private LocalDate	startMoment;
	private LocalDate	endMoment;
	private Integer		number;


	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public LocalDate getStartMoment() {
		return this.startMoment;
	}
	public void setStartMoment(final LocalDate startMoment) {
		this.startMoment = startMoment;
	}
	public LocalDate getEndMoment() {
		return this.endMoment;
	}
	public void setEndMoment(final LocalDate endMoment) {
		this.endMoment = endMoment;
	}
	public Integer getNumber() {
		return this.number;
	}
	public void setNumber(final Integer number) {
		this.number = number;
	}


	//Relationships
	private FixUpTask	fixUpTask;


	@ManyToOne(optional = false)
	public FixUpTask getFixUpTask() {
		return this.fixUpTask;
	}
	public void setFixUpTask(final FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

}
