package pl.poleng.dao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DataTablesOutput.View.class)
	private Long id;

	@JsonView(DataTablesOutput.View.class)
	private String title;
	// private Collection<StateOrderHistory> stateHistory;
	@ManyToOne(fetch = FetchType.EAGER)	
	@JsonView(DataTablesOutput.View.class)
	private Client client;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonView(DataTablesOutput.View.class)
	private Translator translator;
	// private Client proposedClient;
	
	@JsonView(DataTablesOutput.View.class)
	private int declaredDocumentCount;
	//private int declaredTotalCharacterCount;
	// private Payment payment;
	//private Set<Document> documents;
	//private TranslationDirection translationDirection;
	//private TranslationSpecialization translationSpecialization;
	// private TranslationOrderCert cerified;
	//private TranslationOrderType type;
	//private TranslationOrderMode mode;
	//private OperationType proofReading;
	//private OperationType correction;
	//private TranslationOrderState state;
	//private String stateDescription;
	//private boolean secretOrder;
	// private TransportMethod sourceDocumentsRecieveMethod;
	// private TransportMethod sourceDocumentsReturnMethod;
	// private TransportMethod translatedDocumentsReturnMethod;
	// private Collection<AssignmentProofReading> assignmentsProofReading;
	// private OnOrderClientData orderClientData;
	//private Date plannedSourceDocumentsRecieve;
	private Date plannedFinish;
	// private Evaluation evaluation;
	// private Set<HelperFile> helperFiles;
	// private OrderOrigin orderOrigin;
	//private byte[] confirmationReport;
	//private Boolean sendConfirmation;
	//private Boolean evaluationAfterCompletition;
	//private Boolean onlyProofReading;
	//private Integer additionalCopies;
	//private Integer loyaltyPoints;
	//private Boolean internal;
	// private Language clientLang;
	//private Date returnDate;
	//private User coordinator;
	// private Partner partner;

}
