
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
public class ProfessionalRecord extends DomainEntity {

	private String		companyName;
	private String		role;
	private String		attachmentURL;
	private String		comment;
	private LocalDate	startDate;
	private LocalDate	endDate;


	@NotBlank
	public String getCompanyName() {
		return this.companyName;
	}
	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}
	@NotBlank
	public String getRole() {
		return this.role;
	}
	public void setRole(final String role) {
		this.role = role;
	}
	@URL
	public String getAttachmentURL() {
		return this.attachmentURL;
	}
	public void setAttachmentURL(final String attachmentURL) {
		this.attachmentURL = attachmentURL;
	}
	@NotBlank
	public String getComment() {
		return this.comment;
	}
	public void setComment(final String comment) {
		this.comment = comment;
	}
	public LocalDate getStartDate() {
		return this.startDate;
	}
	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return this.endDate;
	}
	public void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
	}
}
