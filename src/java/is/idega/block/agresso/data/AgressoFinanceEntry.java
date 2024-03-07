package is.idega.block.agresso.data;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

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

import com.idega.util.StringUtil;

import is.idega.block.agresso.AgressoConstants;

@Entity
@Table(name=AgressoFinanceEntry.ENTITY_NAME)
@NamedQueries({
	@NamedQuery(
			name=AgressoFinanceEntry.NAMED_QUERY_FIND_BY_ID,
			query="select e from is.idega.block.agresso.data.AgressoFinanceEntry e where e.id = :id"
	),
	@NamedQuery(
			name=AgressoFinanceEntry.NAMED_QUERY_FIND_BY_TICKET_NUMBER,
			query="select e from is.idega.block.agresso.data.AgressoFinanceEntry e where e.ticketNumber = :ticketNumber"
	),
	@NamedQuery(
			name=AgressoFinanceEntry.NAMED_QUERY_FIND_BY_TICKET_NUMBER_NOT_RULED_ON,
			query="select e from is.idega.block.agresso.data.AgressoFinanceEntry e where e.ticketNumber = :ticketNumber and (e.rulingResult is null or e.rulingResult = 'protested')"
	),
	@NamedQuery(
			name = AgressoFinanceEntry.NAMED_QUERY_FIND_UNREAD,
			query = "select e from is.idega.block.agresso.data.AgressoFinanceEntry e where e.isRead is null or e.isRead = '0'"
	),
	@NamedQuery(
			name = AgressoFinanceEntry.NAMED_QUERY_FIND_APPROVED_APPEALS,
			query = "select e from is.idega.block.agresso.data.AgressoFinanceEntry e where e.rulingResultDate >= :from and e.rulingResult = '" +
			AgressoConstants.TICKET_APPEAL_APPROVED + "'"
	),
	@NamedQuery(
			name = AgressoFinanceEntry.NAMED_QUERY_FIND_DENIED_APPEALS,
			query = "select e from is.idega.block.agresso.data.AgressoFinanceEntry e where e.rulingResultDate >= :from and e.rulingResult = '" +
			AgressoConstants.TICKET_APPEAL_DENIED + "'"
	),
	@NamedQuery(
			name = AgressoFinanceEntry.NAMED_QUERY_FIND_RE_OPENED,
			query = "select e from is.idega.block.agresso.data.AgressoFinanceEntry e where e.reOpenDate >= :from"
	)
})
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
	public static final String COLUMN_POSTAL_CODE = "postal_code";
	public static final String COLUMN_METER_NUMBER = "meter_number";
	public static final String COLUMN_INVOICE_NUMBER = "invoice_number";

	public static final String COLUMN_PROTESTED = "is_protested";
	public static final String COLUMN_PROTESTED_DATE = "protested_date";

	public static final String COLUMN_RE_OPEN_DATE = "re_open_date";

	public static final String COLUMN_RULING = "ruling_result";
	public static final String COLUMN_RULING_RESULT_DATE = "ruling_result_date";
	public static final String COLUMN_RULLING_PREDEFINED_TEXT = "rulling_predefined_text";
	public static final String COLUMN_RULLING_EXPLANATION_TEXT = "rulling_explantation_text";

	public static final String	COLUMN_IS_PROCESSED = "is_processed",

								COLUMN_BANK_CODE = "bank_code",
								COLUMN_BANK_LEDGER = "bank_ledger",
								COLUMN_BANK_ACCOUNT_NUMBER = "bank_account_number",
								COLUMN_BANK_ACCOUNT_OWNER = "bank_account_owner",
								COLUMN_CARD_NUMBER = "card_number",
								COLUMN_AUTH_CODE = "auth_code";

	public static final String RULING_APPROVED = AgressoConstants.TICKET_APPEAL_APPROVED;
	public static final String RULING_DENIED = "denied";

	public static final String	NAMED_QUERY_FIND_BY_ID = "agressoFinanceEntry.findById",
								NAMED_QUERY_FIND_BY_TICKET_NUMBER_NOT_RULED_ON = "agressoFinanceEntry.findByTicketNumberNotRuled",
								NAMED_QUERY_FIND_BY_TICKET_NUMBER = "agressoFinanceEntry.findByTicketNumber",
								NAMED_QUERY_FIND_UNREAD = "agressoFinanceEntry.findUnread",
								NAMED_QUERY_FIND_APPROVED_APPEALS = "agressoFinanceEntry.findApprovedAppeals",
								NAMED_QUERY_FIND_DENIED_APPEALS = "agressoFinanceEntry.findDeniedAppeals",
								NAMED_QUERY_FIND_RE_OPENED = "agressoFinanceEntry.findReOpened";

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

	@Column(name=COLUMN_RULLING_PREDEFINED_TEXT,length=1000)
	private String rullingPredefinedText;

	@Column(name=COLUMN_RULLING_EXPLANATION_TEXT,length=1000)
	private String rullingExplanationText;

	@Column(name=AgressoFinanceEntry.COLUMN_IS_PROCESSED,length=1)
	private String isProcessed;

	@Column(name=AgressoFinanceEntry.COLUMN_RE_OPEN_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reOpenDate;

	@Column(name=AgressoFinanceEntry.COLUMN_BANK_CODE)
	private String bankCode;

	@Column(name=AgressoFinanceEntry.COLUMN_BANK_LEDGER)
	private String bankLedger;

	@Column(name=AgressoFinanceEntry.COLUMN_BANK_ACCOUNT_NUMBER)
	private String bankAccountNumber;

	@Column(name=AgressoFinanceEntry.COLUMN_BANK_ACCOUNT_OWNER)
	private String bankAccountOwner;

	@Column(name = AgressoFinanceEntry.COLUMN_POSTAL_CODE)
	private String postalCode;

	@Column(name=COLUMN_CARD_NUMBER)
	private String cardNumber;

	@Column(name=COLUMN_AUTH_CODE)
	private String authCode;

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
		this.info = getShortenedText(info);
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
		this.rulingResult = getShortenedText(rulingResult, 30);
	}

	public String getRullingPredefinedText() {
		return rullingPredefinedText;
	}

	public void setRullingPredefinedText(String rullingPredefinedText) {
		this.rullingPredefinedText = getShortenedText(rullingPredefinedText);
	}

	public String getRullingExplanationText() {
		return rullingExplanationText;
	}

	public void setRullingExplanationText(String rullingExplanationText) {
		this.rullingExplanationText = getShortenedText(rullingExplanationText);
	}

	private String getShortenedText(String text) {
		return getShortenedText(text, 1000);
	}

	private String getShortenedText(String text, int length) {
		if (!StringUtil.isEmpty(text) && text.length() > length) {
			Logger.getLogger(getClass().getName()).warning("Just shortened too long text '" + text + "' to " + length + " characters");
			text = text.substring(0, length - 1);
		}
		return text;
	}

	public Date getReOpenDate() {
		return reOpenDate;
	}

	public void setReOpenDate(Date reOpenDate) {
		this.reOpenDate = reOpenDate;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankLedger() {
		return bankLedger;
	}

	public void setBankLedger(String bankLedger) {
		this.bankLedger = bankLedger;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankAccountOwner() {
		return bankAccountOwner;
	}

	public void setBankAccountOwner(String bankAccountOwner) {
		this.bankAccountOwner = bankAccountOwner;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public String toString() {
		return "ID: " + getID() + ", ticket number: " + getTicketNumber() + ", amount: " + getAmount();
	}
}