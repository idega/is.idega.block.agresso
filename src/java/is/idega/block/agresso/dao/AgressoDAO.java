package is.idega.block.agresso.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.idega.core.persistence.GenericDao;

import is.idega.block.agresso.data.AgressoFinanceEntryForParkingCard;

public interface AgressoDAO extends GenericDao {

	public Long addFinanceEntry(String entryType, String userSSN, Integer amount, Timestamp paymentDate, String info);

	public Long addFinanceEntryParking(String entryType, String userSSN,
			Integer amount, Timestamp paymentDate, Date creationDate, String info,
			String registrationNumber, String permanentNumber, String carType,
			String owner, String ticketNumber, String ticketOfficer,
			String streetName, String streetNumber, String streetDescription,
			String meterNumber, String invoiceNumber);

	public Long addFinanceEntryParkingForParkingCard(
			String entryType,
			String userSSN,
			Integer amount,
			Timestamp paymentDate,
			Date creationDate,
			String info,
			String registrationNumber,
			String permanentNumber,
			String carType,
			String owner,
			String parkingCardNumber,
			String invoiceNumber,
			String parkingZone,
			Date validFrom,
			Date validTo,
			String apartmentIdentifier
	);

	public AgressoFinanceEntryForParkingCard getEntryInAgressoForParkingCard(
			String userSSN,
			Integer amount,
			String registrationNumber,
			String permanentNumber,
			String owner,
			String parkingZone,
			String apartmentIdentifier
	);

	public <T extends Serializable> boolean deleteFinanceEntry(Long entryId, Class<T> entityClass);

}