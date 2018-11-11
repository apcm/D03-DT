
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	private Date				moment;
	private String				priority;
	private String				tag;
	private String				body;
	private String				subject;
	private boolean				flagSpam;

	private Actor				sender;
	private Collection<Actor>	recipient;


	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@Pattern(regexp = "^HIGH|NEUTRAL|LOW$")
	public String getPriority() {
		return this.priority;
	}
	public void setPriority(final String priority) {
		this.priority = priority;
	}

	@NotBlank
	public String getTag() {
		return this.tag;
	}
	public void setTag(final String tag) {
		this.tag = tag;
	}

	@NotBlank
	public String getBody() {
		return this.body;
	}
	public void setBody(final String body) {
		this.body = body;
	}

	@NotBlank
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(final String subject) {
		this.subject = subject;
	}
	public boolean getFlagSpam() {
		return this.flagSpam;
	}
	public void setFlagSpam(final boolean flagSpam) {
		this.flagSpam = flagSpam;
	}
	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}
	public void setSender(final Actor sender) {
		this.sender = sender;
	}
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Actor> getRecipient() {
		return this.recipient;
	}
	public void setRecipient(final Collection<Actor> recipient) {
		this.recipient = recipient;
	}

}
