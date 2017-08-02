package pl.poleng.dao.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Dariusz Pudlik
 *
 */
@Entity
@Table(name = "user_profiles")
public class UserProfile implements Serializable {

	private static final long serialVersionUID = 6318166731662252020L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 15, unique = true, nullable = false)
	private String type = UserProfileType.USER.getUserProfileType();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
		result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserProfile)) {
			return false;
		}
		UserProfile other = (UserProfile) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "UserProfile [id=" + this.id + ", type=" + this.type + "]";
	}
}
