package is.idega.block.agresso.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = AgressoFinanceEntryForParkingCard.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name=AgressoFinanceEntryForParkingCard.NAMED_QUERY_FIND_BY_ID, query="select e from is.idega.block.agresso.data.AgressoFinanceEntryForParkingCard e where e.id = :id")
})
public class AgressoFinanceEntryForParkingCard implements Serializable {

	private static final long serialVersionUID = 2990021991159098091L;

	static final String TABLE_NAME = "agresso_entry_parking_card";
	
	public static final String NAMED_QUERY_FIND_BY_ID = "parkingCardAgressoEntry.findById";
	
	private static final String COLUMN_ID = "id",
								
								COLUMN_CREATION_DATE = "creation_date",
								COLUMN_ENTRY_TYPE = "entry_type",
								COLUMN_ENTRY_USER = "entry_user",
								COLUMN_ENTRY_PAYMENT_USER = "payment_user",
								COLUMN_PAYMENT_DATE = "payment_date",
								COLUMN_AGRESSO_ID = "agresso_id",
								COLUMN_AMOUNT = "amount",
								COLUMN_READ = "is_read",
								COLUMN_READ_DATE = "read_date",
								COLUMN_INFO = "info",
								
								COLUMN_CAR_REGISTRATION_NUMBER = "registration_number",
								COLUMN_CAR_PERMANENT_NUMBER = "permanent_number",
								COLUMN_CAR_TYPE = "car_type",
								COLUMN_CAR_OWNER = "owner",
								COLUMN_PARKING_CARD_NUMBER = "parking_card_number",
								COLUMN_INVOICE_NUMBER = "invoice_number",
								COLUMN_PARKING_ZONE = "parking_zone",
								COLUMN_VALID_FROM = "valid_from",
								COLUMN_VALID_TO = "valid_to",
								COLUMN_APARTMENT_IDENTIFIER = "apartment_identifier";
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name=COLUMN_ID)
	private Long id;
	
	@Column(name=COLUMN_CREATION_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(name=COLUMN_ENTRY_TYPE) 
	private String entryType;
	
	@Column(name=COLUMN_ENTRY_USER)
	private String entryUser;
	
	@Column(name=COLUMN_ENTRY_PAYMENT_USER)
	private String entryPaymentUser;
	
	@Column(name=COLUMN_PAYMENT_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;
	
	@Column(name=COLUMN_AMOUNT)
	private Integer amount;
	
	@Column(name=COLUMN_READ,length=1)
	private String isRead;
	
	@Column(name=COLUMN_READ_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date isReadDate;

	@Column(name=COLUMN_INFO,length=1000)
	private String info;

	@Column(name=COLUMN_AGRESSO_ID)
	private Long agressoID;
	
	@Column(name=COLUMN_CAR_REGISTRATION_NUMBER)
	private String registrationNumber;

	@Column(name=COLUMN_CAR_PERMANENT_NUMBER)
	private String permanentNumber;

	@Column(name=COLUMN_CAR_TYPE)
	private String carType;

	@Column(name=COLUMN_CAR_OWNER)
	private String owner;

	@Column(name=COLUMN_PARKING_CARD_NUMBER)
	private String parkingCardNumber;

	@Column(name=COLUMN_INVOICE_NUMBER)
	private String invoiceNumber;
	
	@Column(name=COLUMN_PARKING_ZONE)
	private String parkingZone;
	
	@Column(name=COLUMN_VALID_FROM)
	private Date validFrom;
	
	@Column(name=COLUMN_VALID_TO)
	private Date validTo;
	
	@Column(name=COLUMN_APARTMENT_IDENTIFIER)
	private String apartmentIdentifier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
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

	public String getParkingCardNumber() {
		return parkingCardNumber;
	}

	public void setParkingCardNumber(String parkingCardNumber) {
		this.parkingCardNumber = parkingCardNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getParkingZone() {
		return parkingZone;
	}

	public void setParkingZone(String parkingZone) {
		this.parkingZone = parkingZone;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public String getApartmentIdentifier() {
		return apartmentIdentifier;
	}

	public void setApartmentIdentifier(String apartmentIdentifier) {
		this.apartmentIdentifier = apartmentIdentifier;
	}

	public String toString() {
		return "Agresso entry for parking card. ID: " + getId() + ", car number: " + getRegistrationNumber() + ", parking card number: " +
				getParkingCardNumber();
	}
}