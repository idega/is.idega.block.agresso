package is.idega.block.agresso.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.core.persistence.Param;
import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.util.IWTimestamp;
import com.idega.util.ListUtil;

import is.idega.block.agresso.dao.AgressoDAO;
import is.idega.block.agresso.data.AgressoFinanceEntry;
import is.idega.block.agresso.data.AgressoFinanceEntryForParkingCard;

@Repository("agressoDAO")
@Transactional(readOnly = true)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AgressoDAOImpl extends GenericDaoImpl implements AgressoDAO {

	private static final Logger LOGGER = Logger.getLogger(AgressoDAOImpl.class.getName());

	@Override
	public Long addFinanceEntry(String entryType, String userSSN, Integer amount, Timestamp paymentDate, String info) {
		return addFinanceEntryParking(entryType, userSSN, amount, paymentDate, null, info, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Override
	@Transactional(readOnly = false)
	public Long addFinanceEntryParking(String entryType, String userSSN,
			Integer amount, Timestamp paymentDate, Date creationDate, String info,
			String registrationNumber, String permanentNumber, String carType,
			String owner, String ticketNumber, String ticketOfficer,
			String streetName, String streetNumber, String streetDescription,
			String meterNumber, String invoiceNumber) {

		try {
			AgressoFinanceEntry entry = new AgressoFinanceEntry();
			entry.setAmount(amount);

			if (creationDate == null) {
				entry.setCreationDate(IWTimestamp.getTimestampRightNow());
			} else {
				entry.setCreationDate(creationDate);
			}

			entry.setEntryUser(userSSN);
			entry.setEntryType(entryType);
			entry.setPaymentDate(paymentDate);
			entry.setInfo(info);

			entry.setRegistrationNumber(registrationNumber);
			entry.setPermanentNumber(permanentNumber);
			entry.setCarType(carType);
			entry.setOwner(owner);
			entry.setTicketNumber(ticketNumber);
			entry.setTicketOfficer(ticketOfficer);
			entry.setStreetName(streetName);
			entry.setStreetNumber(streetNumber);
			entry.setStreetDescription(streetDescription);
			entry.setMeterNumber(meterNumber);
			entry.setInvoiceNumber(invoiceNumber);

			getEntityManager().persist(entry);

			return entry.getID();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error creating entry in Agresso DB table. Type: " + entryType + ", for user: " + userSSN +", ticket number: " + ticketNumber, e);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public <T extends Serializable> boolean deleteFinanceEntry(Long entryId, Class<T> entityClass) {
		if (entryId == null) {
			LOGGER.warning("Entry ID is not defined!");
			return false;
		}

		try {
			T entry = find(entityClass, entryId);
			getEntityManager().remove(entry);
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error deleting entry from Agresso DB table. Entry ID: " + entryId + ", class name: " + entityClass.getName(), e);
		}

		return false;
	}

	@Override
	public AgressoFinanceEntryForParkingCard getEntryInAgressoForParkingCard(
			String userSSN,
			Integer amount,
			String registrationNumber,
			String permanentNumber,
			String owner,
			String parkingZone,
			String apartmentIdentifier
		) {
		try {
			List<AgressoFinanceEntryForParkingCard> entries = getResultListByInlineQuery(
					"select e from " + AgressoFinanceEntryForParkingCard.class.getName() + " e where e.entryUser = :userSSN and e.amount = :amount and e.registrationNumber = :registrationNumber" +
							" and e.permanentNumber = :permanentNumber and e.owner = :owner and e.parkingZone = :parkingZone and e.apartmentIdentifier = :apartmentIdentifier and e.validFrom is null and e.validTo >= :now",
					AgressoFinanceEntryForParkingCard.class,
					new Param("userSSN", userSSN),
					new Param("amount", amount),
					new Param("registrationNumber", registrationNumber),
					new Param("permanentNumber", permanentNumber),
					new Param("owner", owner),
					new Param("parkingZone", parkingZone),
					new Param("apartmentIdentifier", apartmentIdentifier),
					new Param("now", IWTimestamp.RightNow().getDate())
			);
			return ListUtil.isEmpty(entries) ? null : entries.iterator().next();
		} catch (Exception e) {
			getLogger().log(
					Level.WARNING,
					"Error getting financial entries of parking card for user " + userSSN + ", amount " + amount + ", registration number " +
					registrationNumber + ", owner " + owner + ", apartment identifier: " + apartmentIdentifier,
					e
			);
		}
		return null;
	}

	@Override
	@Transactional
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
	) {
		try {
			AgressoFinanceEntryForParkingCard entry = new AgressoFinanceEntryForParkingCard();
			entry.setAmount(amount);

			if (creationDate == null) {
				entry.setCreationDate(IWTimestamp.getTimestampRightNow());
			} else {
				entry.setCreationDate(creationDate);
			}

			entry.setEntryUser(userSSN);
			entry.setEntryType(entryType);
			entry.setPaymentDate(paymentDate);
			entry.setInfo(info);

			entry.setRegistrationNumber(registrationNumber);
			entry.setPermanentNumber(permanentNumber);
			entry.setCarType(carType);
			entry.setOwner(owner);
			entry.setParkingCardNumber(parkingCardNumber);
			entry.setInvoiceNumber(invoiceNumber);
			entry.setApartmentIdentifier(apartmentIdentifier);
			entry.setValidFrom(validFrom);
			entry.setValidTo(validTo);
			entry.setParkingZone(parkingZone);

			getEntityManager().persist(entry);

			return entry.getId();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error creating entry for parking card in Agresso DB table. Type: " + entryType + ", for user: " + userSSN +
					", parking card number: " + parkingCardNumber, e);
		}

		return null;
	}

}