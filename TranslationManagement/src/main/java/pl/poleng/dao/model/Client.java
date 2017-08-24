package pl.poleng.dao.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	@JsonView(DataTablesOutput.View.class)
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	@Size(min = 2, max = 30)
	@JsonView(DataTablesOutput.View.class)
	private String firstName;

	@NotEmpty
	@Column(nullable = false)
	@Size(min = 2, max = 30)
	@JsonView(DataTablesOutput.View.class)
	private String lastName;

	@NotEmpty
	@Column(nullable = false)
	@Email
	@JsonView(DataTablesOutput.View.class)
	private String email;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="client")
	private Collection<Order> translationOrders;
}
