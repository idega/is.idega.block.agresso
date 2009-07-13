package is.idega.block.agresso.dao.impl;

import is.idega.block.agresso.dao.AgressoDAO;
import is.idega.block.agresso.data.AgressoFinanceEntry;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.util.IWTimestamp;

@Scope("singleton")
@Repository("agressoDAO")
@Transactional(readOnly = true)
public class AgressoDAOImpl extends GenericDaoImpl implements AgressoDAO {

	@Transactional(readOnly = false)
	public void addFinanceEntry(String entryType, String userSSN,
			Integer amount, Date paymentDate, String info) {
		AgressoFinanceEntry entry = new AgressoFinanceEntry();
		entry.setAmount(amount);
		entry.setCreationDate(IWTimestamp.getTimestampRightNow());
		
		entry.setEntryUser(userSSN);
		entry.setEntryType(entryType);
		entry.setPaymentDate(paymentDate);
		entry.setInfo(info);

		getEntityManager().persist(entry);
	}

	@Transactional(readOnly = false)
	public void addFinanceEntryParking(String entryType, String userSSN,
			Integer amount, Date paymentDate, String info,
			String registrationNumber, String permanentNumber, String carType,
			String owner, String ticketNumber, String ticketOfficer,
			String streetName, String streetNumber, String streetDescription,
			String meterNumber, String invoiceNumber) {
		AgressoFinanceEntry entry = new AgressoFinanceEntry();
		entry.setAmount(amount);
		entry.setCreationDate(IWTimestamp.getTimestampRightNow());
	
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
	}

}
