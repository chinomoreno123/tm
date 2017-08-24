package pl.poleng.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -2117042694434892256L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private Long id;	

	private String firstName;
	private String secondName;
	private String lastName;
	private String email;
	private String taxResidency;
	private String telphone;
	private String mobile;
	private String fax;
	private String birthPlace;
	private Date birthDate;
	private String pesel;
	private String motherName;
	private String fatherName;
	private String identityCardId;
	
	@Embedded
	private Address registeredAddress;
	
	@Embedded
	@AttributeOverrides({ @javax.persistence.AttributeOverride(name="street", column=@Column(name="ca_street")), @javax.persistence.AttributeOverride(name="streetNo", column=@Column(name="ca_streetNo")), @javax.persistence.AttributeOverride(name="apartmentNo", column=@Column(name="ca_apartmentNo")), @javax.persistence.AttributeOverride(name="postcode", column=@Column(name="ca_postcode")), @javax.persistence.AttributeOverride(name="country", column=@Column(name="ca_country")), @javax.persistence.AttributeOverride(name="description", column=@Column(name="ca_description")), @javax.persistence.AttributeOverride(name="town", column=@Column(name="ca_town"))})	  
	private Address contactAddress;
	
	private String bankAccount;
	private String bankName;
	private String nip;
	// private Set<Employment> employers;
	// private String inlandRevenue;

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (birthPlace == null) {
			if (other.birthPlace != null)
				return false;
		} else if (!birthPlace.equals(other.birthPlace))
			return false;	
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fatherName == null) {
			if (other.fatherName != null)
				return false;
		} else if (!fatherName.equals(other.fatherName))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (identityCardId == null) {
			if (other.identityCardId != null)
				return false;
		} else if (!identityCardId.equals(other.identityCardId))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (motherName == null) {
			if (other.motherName != null)
				return false;
		} else if (!motherName.equals(other.motherName))
			return false;
		if (nip == null) {
			if (other.nip != null)
				return false;
		} else if (!nip.equals(other.nip))
			return false;
		if (pesel == null) {
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		if (registeredAddress == null) {
			if (other.registeredAddress != null)
				return false;
		} else if (!registeredAddress.equals(other.registeredAddress))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (taxResidency == null) {
			if (other.taxResidency != null)
				return false;
		} else if (!taxResidency.equals(other.taxResidency))
			return false;
		if (telphone == null) {
			if (other.telphone != null)
				return false;
		} else if (!telphone.equals(other.telphone))
			return false;
		return true;
	}
	
	public Address getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(Address contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTaxResidency() {
		return taxResidency;
	}

	public void setTaxResidency(String taxResidency) {
		this.taxResidency = taxResidency;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getIdentityCardId() {
		return identityCardId;
	}

	public void setIdentityCardId(String identityCardId) {
		this.identityCardId = identityCardId;
	}

	public Address getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(Address registeredAddress) {
		this.registeredAddress = registeredAddress;
	}


	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
