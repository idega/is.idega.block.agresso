package is.idega.block.agresso.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name=AgressoFinanceEntry.ENTITY_NAME)
@NamedQuery(name=AgressoFinanceEntry.NAMED_QUERY_FIND_BY_TICKET_NUMBER_NOT_RULED_ON, query="select e from AgressoFinanceEntry e where e.ticketNumber=:ticketNumber and rulingResult is null")
public class AgressoFinanceEntry implements Serializable {
	private static final long serialVersionUID = -747605984016128307L;
	public static final String ENTITY_NAME = "agresso_entry";
	public static final String COLUMN_ID = ENTITY_NAME + "id";
	public static final String COLUMN_CREATION_DATE = "creation_date";
	public static final String COLUMN_ENTRY_TYPE = "entry_type";
	public static final String COLUMN_ENTRY_USER = "entry_user";
	public static final String COLUMN_ENTRY_PAYMENT_USER = "payment_user";
	public static final String COLUMN_PAYMENT_DATE = "payment_date";
	public static final String COLUMN_AMOUNT = "amount";
	public static final String COLUMN_READ = "is_read";
	public static final String COLUMN_READ_DATE = "read_date";
	public static final String COLUMN_INFO = "info";
	public static final String COLUMN_AGRESSO_ID = "agresso_id";
	
	//parking project
	public static final String COLUMN_CAR_REGISTRATION_NUMBER = "registration_number";
	public static final String COLUMN_CAR_PERMANENT_NUMBER = "permanent_number";
	public static final String COLUMN_CAR_TYPE = "car_type";
	public static final String COLUMN_CAR_OWNER = "owner";
	public static final String COLUMN_TICKET_NUMBER = "ticket_number";
	public static final String COLUMN_TICKET_OFFICER = "ticket_officer";
	public static final String COLUMN_STREET_NAME = "street_name";
	public static final String COLUMN_STREET_NUMBER = "street_number";
	public static final String COLUMN_STREET_DESCRIPTION = "street_description";
	public static final String COLUMN_METER_NUMBER = "meter_number";
	public static final String COLUMN_INVOICE_NUMBER = "invoice_number";
	
	public static final String COLUMN_PROTESTED = "is_protested";
	public static final String COLUMN_PROTESTED_DATE = "protested_date";
	
	public static final String COLUMN_RULING = "ruling_result";
	public static final String COLUMN_RULING_RESULT_DATE = "ruling_result_date";
	
	public static final String COLUMN_IS_PROCESSED = "is_processed";

	public static final String RULING_APPROVED = "approved";
	public static final String RULING_DENIED = "denied";
	
	
	public static final String NAMED_QUERY_FIND_BY_TICKET_NUMBER_NOT_RULED_ON = "agressoFinanceEntry.findByTicketNumber";

	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name=AgressoFinanceEntry.COLUMN_ID)
	private Long id;
	
	@Column(name=AgressoFinanceEntry.COLUMN_CREATION_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name=AgressoFinanceEntry.COLUMN_ENTRY_TYPE) 
	private String entryType;
	
	@Column(name=AgressoFinanceEntry.COLUMN_ENTRY_USER)
	private String entryUser;
	
	@Column(name=AgressoFinanceEntry.COLUMN_ENTRY_PAYMENT_USER)
	private String entryPaymentUser;
	
	@Column(name=AgressoFinanceEntry.COLUMN_PAYMENT_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;
	
	@Column(name=AgressoFinanceEntry.COLUMN_AMOUNT)
	private Integer amount;
	
	@Column(name=AgressoFinanceEntry.COLUMN_READ,length=1)
	private String isRead;
	
	@Column(name=AgressoFinanceEntry.COLUMN_READ_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date isReadDate;

	@Column(name=AgressoFinanceEntry.COLUMN_INFO,length=1000)
	private String info;

	@Column(name=AgressoFinanceEntry.COLUMN_AGRESSO_ID)
	private Long agressoID;
	
	@Column(name=AgressoFinanceEntry.COLUMN_CAR_REGISTRATION_NUMBER)
	private String registrationNumber;

	@Column(name=AgressoFinanceEntry.COLUMN_CAR_PERMANENT_NUMBER)
	private String permanentNumber;

	@Column(name=AgressoFinanceEntry.COLUMN_CAR_TYPE)
	private String carType;

	@Column(name=AgressoFinanceEntry.COLUMN_CAR_OWNER)
	private String owner;

	@Column(name=AgressoFinanceEntry.COLUMN_TICKET_NUMBER)
	private String ticketNumber;

	@Column(name=AgressoFinanceEntry.COLUMN_TICKET_OFFICER)
	private String ticketOfficer;

	@Column(name=AgressoFinanceEntry.COLUMN_STREET_NAME)
	private String streetName;

	@Column(name=AgressoFinanceEntry.COLUMN_STREET_NUMBER)
	private String streetNumber;

	@Column(name=AgressoFinanceEntry.COLUMN_STREET_DESCRIPTION)
	private String streetDescription;

	@Column(name=AgressoFinanceEntry.COLUMN_METER_NUMBER)
	private String meterNumber;

	@Column(name=AgressoFinanceEntry.COLUMN_INVOICE_NUMBER)
	private String invoiceNumber;
	
	@Column(name=AgressoFinanceEntry.COLUMN_PROTESTED_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date protestedDate;
	
	@Column(name=AgressoFinanceEntry.COLUMN_PROTESTED,length=1)
	private String isProtested;
	
	@Column(name=AgressoFinanceEntry.COLUMN_RULING_RESULT_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date rulingResultDate;
	
	@Column(name=AgressoFinanceEntry.COLUMN_RULING,length=30)
	private String rulingResult;
	
	@Column(name=AgressoFinanceEntry.COLUMN_IS_PROCESSED,length=1)
	private String isProcessed;

	
	public Long getID() {
		return this.id;
	}
	
	public void setID(Long id) {
		this.id = id;
	}
	
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getEntryPaymentUser() {
		return entryPaymentUser;
	}

	public void setEntryPaymentUser(String entryPaymentUser) {
		this.entryPaymentUser = entryPaymentUser;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
	public String getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(String isProcessed) {
		this.isProcessed = isProcessed;
	}

	public Date getIsReadDate() {
		return isReadDate;
	}

	public void setIsReadDate(Date isReadDate) {
		this.isReadDate = isReadDate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Long getAgressoID() {
		return agressoID;
	}

	public void setAgressoID(Long agressoID) {
		this.agressoID = agressoID;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getPermanentNumber() {
		return permanentNumber;
	}

	public void setPermanentNumber(String permanentNumber) {
		this.permanentNumber = permanentNumber;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTicketOfficer() {
		return ticketOfficer;
	}

	public void setTicketOfficer(String ticketOfficer) {
		this.ticketOfficer = ticketOfficer;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetDescription() {
		return streetDescription;
	}

	public void setStreetDescription(String streetDescription) {
		this.streetDescription = streetDescription;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	
	public Date getProtestedDate() {
		return protestedDate;
	}

	
	public void setProtestedDate(Date protestedDate) {
		this.protestedDate = protestedDate;
	}

	
	public String getIsProtested() {
		return isProtested;
	}

	
	public void setIsProtested(String isProtested) {
		this.isProtested = isProtested;
	}

	
	public Date getRulingResultDate() {
		return rulingResultDate;
	}

	
	public void setRulingResultDate(Date rulingResultDate) {
		this.rulingResultDate = rulingResultDate;
	}

	
	public String getRulingResult() {
		return rulingResult;
	}

	
	public void setRulingResult(String rulingResult) {
		this.rulingResult = rulingResult;
	}
}