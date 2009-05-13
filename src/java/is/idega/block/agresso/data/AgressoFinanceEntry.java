package is.idega.block.agresso.data;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.idega.user.data.User;

@Entity
@Table(name=AgressoFinanceEntry.ENTITY_NAME)
public class AgressoFinanceEntry implements Serializable {
	private static final long serialVersionUID = -747605984016128307L;
	public static final String ENTITY_NAME = "agresso_entry";
	public static final String COLUMN_ID = ENTITY_NAME + "id";
	public static final String COLUMN_CREATION_DATE = "creation_date";
	public static final String COLUMN_CREATED_BY = "created_by";
	public static final String COLUMN_ENTRY_TYPE = "entry_type";
	public static final String COLUMN_ENTRY_USER = "entry_user";
	public static final String COLUMN_ENTRY_PAYMENT_USER = "payment_user";
	public static final String COLUMN_PAYMENT_DATE = "payment_date";
	public static final String COLUMN_AMOUNT = "amount";
	public static final String COLUMN_READ = "read";
	public static final String COLUMN_READ_DATE = "read_date";
	public static final String COLUMN_DISPUTED = "disputed";
	public static final String COLUMN_INFO = "info";
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name=AgressoFinanceEntry.COLUMN_ID)
	private Long id;
	
	@Column(name=AgressoFinanceEntry.COLUMN_CREATION_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp creationDate;
	
	@Column(name=AgressoFinanceEntry.COLUMN_CREATED_BY)
	private User createdBy;
	
	@Column(name=AgressoFinanceEntry.COLUMN_ENTRY_TYPE) 
	private String entryType;
	
	@Column(name=AgressoFinanceEntry.COLUMN_ENTRY_USER)
	private String entryUser;
	
	@Column(name=AgressoFinanceEntry.COLUMN_ENTRY_PAYMENT_USER)
	private String entryPaymentUser;
	
	@Column(name=AgressoFinanceEntry.COLUMN_PAYMENT_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp paymentDate;
	
	@Column(name=AgressoFinanceEntry.COLUMN_AMOUNT)
	private Integer amount;
	
	@Column(name=AgressoFinanceEntry.COLUMN_READ)
	private boolean read;
	
	@Column(name=AgressoFinanceEntry.COLUMN_READ_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp readDate;
	
	@Column(name=AgressoFinanceEntry.COLUMN_DISPUTED)
	private boolean disputed;
	
	@Column(name=AgressoFinanceEntry.COLUMN_INFO)
	private String info;
	
	public Long getID() {
		return this.id;
	}
	
	public void setID(Long id) {
		this.id = id;
	}
	
	public Timestamp getCreationDate() {
		return this.creationDate;
	}
	
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public Timestamp getReadDate() {
		return readDate;
	}

	public void setReadDate(Timestamp readDate) {
		this.readDate = readDate;
	}

	public boolean isDisputed() {
		return disputed;
	}

	public void setDisputed(boolean disputed) {
		this.disputed = disputed;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}