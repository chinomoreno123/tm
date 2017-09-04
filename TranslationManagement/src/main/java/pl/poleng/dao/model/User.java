package pl.poleng.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * 
 * @author Dariusz Pudlik
 *
 */
@Entity
@Table(name = "users")
//@NamedEntityGraph(name = User.USER_PROFILES_GRAPH, attributeNodes = @NamedAttributeNode("userProfiles"))
public class User implements Serializable {
	
	//public static final String USER_PROFILES_GRAPH = "User.userProfiles";

	private static final long serialVersionUID = 4214792490951575932L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataTablesOutput.View.class)
	private Long id;

	@NotEmpty
	@Column(unique = true, nullable = false)
	@Size(min=2, max=30)
	@JsonView(DataTablesOutput.View.class)
	private String username;

	@NotEmpty
	@Column(nullable = false)	
	private String password;
	
	@Transient
	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	@Column(nullable = false)
	@Size(min=2, max=30)
	@JsonView(DataTablesOutput.View.class)
	private String firstName;

	@NotEmpty
	@Column(nullable = false)
	@Size(min=2, max=30)
	@JsonView(DataTablesOutput.View.class)
	private String lastName;

	@NotEmpty
	@Column(nullable = false)
	@Email
	@JsonView(DataTablesOutput.View.class)
	private String email;
	
	@JsonView(DataTablesOutput.View.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date created;

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)	
	@JoinTable(name = "users_user_profiles", joinColumns = {
			@JoinColumn(name = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_profile_id") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<UserProfile> getUserProfiles() {
		return this.userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", created=" + created + ", userProfiles=" + userProfiles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userProfiles == null) ? 0 : userProfiles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userProfiles == null) {
			if (other.userProfiles != null)
				return false;
		} else if (!userProfiles.equals(other.userProfiles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
