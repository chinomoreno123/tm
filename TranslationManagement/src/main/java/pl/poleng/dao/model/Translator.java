package pl.poleng.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "translators")
public class Translator {
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private String firstName;

	@NotEmpty
	@Column(nullable = false)
	private String lastName;

	@NotEmpty
	private String email;

	private String phone1;
	private String phone2;
	private String nip;
	private String bankName;
	private String bankAccountNumber;
	private String taxOffice;

	public String toString() {
		return "Translator [id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName
				+ ", email=" + this.email + ", phone1=" + this.phone1 + ", phone2=" + this.phone2 + ", nip=" + this.nip
				+ ", bankName=" + this.bankName + ", bankAccountNumber=" + this.bankAccountNumber + ", taxOffice="
				+ this.taxOffice + "]";
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.bankAccountNumber == null ? 0 : this.bankAccountNumber.hashCode());
		result = 31 * result + (this.bankName == null ? 0 : this.bankName.hashCode());
		result = 31 * result + (this.email == null ? 0 : this.email.hashCode());
		result = 31 * result + (this.firstName == null ? 0 : this.firstName.hashCode());
		result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
		result = 31 * result + (this.lastName == null ? 0 : this.lastName.hashCode());
		result = 31 * result + (this.nip == null ? 0 : this.nip.hashCode());
		result = 31 * result + (this.phone1 == null ? 0 : this.phone1.hashCode());
		result = 31 * result + (this.phone2 == null ? 0 : this.phone2.hashCode());
		result = 31 * result + (this.taxOffice == null ? 0 : this.taxOffice.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Translator other = (Translator) obj;
		if (this.bankAccountNumber == null) {
			if (other.bankAccountNumber != null) {
				return false;
			}
		} else if (!this.bankAccountNumber.equals(other.bankAccountNumber)) {
			return false;
		}
		if (this.bankName == null) {
			if (other.bankName != null) {
				return false;
			}
		} else if (!this.bankName.equals(other.bankName)) {
			return false;
		}
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.firstName == null) {
			if (other.firstName != null) {
				return false;
			}
		} else if (!this.firstName.equals(other.firstName)) {
			return false;
		}
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.lastName == null) {
			if (other.lastName != null) {
				return false;
			}
		} else if (!this.lastName.equals(other.lastName)) {
			return false;
		}
		if (this.nip == null) {
			if (other.nip != null) {
				return false;
			}
		} else if (!this.nip.equals(other.nip)) {
			return false;
		}
		if (this.phone1 == null) {
			if (other.phone1 != null) {
				return false;
			}
		} else if (!this.phone1.equals(other.phone1)) {
			return false;
		}
		if (this.phone2 == null) {
			if (other.phone2 != null) {
				return false;
			}
		} else if (!this.phone2.equals(other.phone2)) {
			return false;
		}
		if (this.taxOffice == null) {
			if (other.taxOffice != null) {
				return false;
			}
		} else if (!this.taxOffice.equals(other.taxOffice)) {
			return false;
		}
		return true;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getNip() {
		return this.nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNumber() {
		return this.bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getTaxOffice() {
		return this.taxOffice;
	}

	public void setTaxOffice(String taxOffice) {
		this.taxOffice = taxOffice;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
