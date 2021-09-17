package is.idega.block.agresso.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.idega.builder.bean.AdvancedProperty;
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
			String apartmentIdentifier,
			String paymentStatus,
			Integer splitPayment
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

	public List<AgressoFinanceEntryForParkingCard> getByRegistrationNumber(String registrationNumber, boolean validOnly);

	public List<AgressoFinanceEntryForParkingCard> getByRegistrationNumberAndParkingCardNumber(String registrationNumber, String parkingCardNumber);

	public Map<String, List<AgressoFinanceEntryForParkingCard>> getByRegistrationNumbersAndParkingCardsNumbers(List<AdvancedProperty> carRegistrationAndCardNumbers);

	public boolean setStatusForParkingCard(String registrationNumber, String parkingCardNumber, String status);

	public <T extends Serializable> boolean deleteFinanceEntry(Long entryId, Class<T> entityClass);

	public int getDelayForParkingCardPayment();

}