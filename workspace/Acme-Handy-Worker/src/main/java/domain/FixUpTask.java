
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class FixUpTask extends DomainEntity {

	private String	ticker;
	private Date	moment;
	private String	description;
	private String	adress;
	private Money	maximumPrice;
	private Date	startDate;
	private Date	endDate;


	@Column(unique = true)
	//@Pattern(regexp="^([0]\d|[1][0-9])(0[0-9]|1[0-2])(0\d|[12]\d|3[01])-[A-Z0-9_]{6}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return this.moment;
	}
	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	@NotBlank
	public String getAdress() {
		return this.adress;
	}
	public void setAdress(final String adress) {
		this.adress = adress;
	}
	/*
	 * @AttributeOverrides({
	 * 
	 * @AttributeOverride(name="amount",
	 * column=@Column(name="maximumAmount")),
	 * 
	 * @AttributeOverride(name="currency",
	 * column=@Column(name="maximumCurrency"))
	 * })
	 */
	public Money getMaximumPrice() {
		return this.maximumPrice;
	}
	public void setMaximumPrice(final Money maximumPrice) {
		this.maximumPrice = maximumPrice;
	}
	@NotBlank
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	@NotBlank
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return this.endDate;
	}
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}


	//Relationships
	private Collection<Application>	application;
	private Category				category;
	private Collection<Warranty>	warranty;


	@OneToMany
	//(mappedBy="loquesea")
	public Collection<Application> getApplication() {
		return this.application;
	}
	public void setApplication(final Collection<Application> application) {
		this.application = application;
	}
	@ManyToOne(optional = false)
	public Category getCategory() {
		return this.category;
	}
	public void setCategory(final Category category) {
		this.category = category;
	}
	@OneToMany
	public Collection<Warranty> getWarranty() {
		return this.warranty;
	}
	public void setWarranty(final Collection<Warranty> warranty) {
		this.warranty = warranty;
	}

}
