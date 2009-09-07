package is.idega.block.agresso.dao;

import java.sql.Timestamp;

import com.idega.core.persistence.GenericDao;

public interface AgressoDAO extends GenericDao {
	public void addFinanceEntry(String entryType, String userSSN, Integer amount, Timestamp paymentDate, String info);
	public void addFinanceEntryParking(String entryType, String userSSN,
			Integer amount, Timestamp paymentDate, String info,
			String registrationNumber, String permanentNumber, String carType,
			String owner, String ticketNumber, String ticketOfficer,
			String streetName, String streetNumber, String streetDescription,
			String meterNumber, String invoiceNumber);
}
