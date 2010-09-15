package is.idega.block.agresso.dao;

import java.sql.Timestamp;
import java.util.Date;

import com.idega.core.persistence.GenericDao;

public interface AgressoDAO extends GenericDao {
	
	public Long addFinanceEntry(String entryType, String userSSN, Integer amount, Timestamp paymentDate, String info);
	
	public Long addFinanceEntryParking(String entryType, String userSSN,
			Integer amount, Timestamp paymentDate, Date creationDate, String info,
			String registrationNumber, String permanentNumber, String carType,
			String owner, String ticketNumber, String ticketOfficer,
			String streetName, String streetNumber, String streetDescription,
			String meterNumber, String invoiceNumber);
	
	public boolean deleteFinanceEntry(Long entryID);
}