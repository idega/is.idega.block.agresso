package is.idega.block.agresso.dao.impl;

import is.idega.block.agresso.dao.AgressoDAO;
import is.idega.block.agresso.data.AgressoFinanceEntry;
import is.idega.block.agresso.data.AgressoFinanceEntryForParkingCard;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.util.IWTimestamp;

@Repository("agressoDAO")
@Transactional(readOnly = true)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class AgressoDAOImpl extends GenericDaoImpl implements AgressoDAO {

	private static final Logger LOGGER = Logger.getLogger(AgressoDAOImpl.class.getName());
	
	public Long addFinanceEntry(String entryType, String userSSN, Integer amount, Timestamp paymentDate, String info) {
		return addFinanceEntryParking(entryType, userSSN, amount, paymentDate, null, info, null, null, null, null, null, null, null, null, null, null, null);
	}

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