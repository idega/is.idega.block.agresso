package is.idega.block.agresso.business;

import is.idega.block.agresso.dao.AgressoDAO;
import is.idega.block.agresso.data.AgressoFinanceEntry;

import java.sql.Timestamp;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.util.IWTimestamp;
import com.idega.util.expression.ELUtil;

@Scope("singleton")
@Service("agressoFinanceBusiness")
public class AgressoFinanceBusiness {

	@Autowired
	private AgressoDAO agressoDAO;

	public void createParkingEntry(String user, Integer amount, String info,
			String registrationNumber, String permanentNumber, String carType,
			String owner, String ticketNumber, String ticketOfficer,
			String streetName, String streetNumber, String streetDescription,
			String meterNumber, String invoiceNumber) {
		IWTimestamp paymentDate = new IWTimestamp();
		paymentDate.addDays(14);
		getAgressoDAO().addFinanceEntryParking("PARKING", user, amount,
				paymentDate.getTimestamp(), info, registrationNumber, permanentNumber, carType, owner, ticketNumber, ticketOfficer, streetName,
				streetNumber, streetDescription, meterNumber, invoiceNumber);
	}

	protected AgressoDAO getAgressoDAO() {
		if (agressoDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		return agressoDAO;
	}
	
	/**
	 * 
	 * @param ticketNumber, required
	 * @param isProtested, required otherwise isProtested and protestDate will be nulled in the db
	 * @param rulingResult, if not supplied rulingResult and rulingDate will be nulled.
	 */
	public void updateTicketProtestInfo(String ticketNumber, boolean isProtested, String rulingResult){
		
		//note although highly unlikely ;) the same car could get the same ticketnumber...so here we would get an exception
		//we only look for entries that have not gotten a ruling yet.
		Query query = getAgressoDAO().createNamedQuery(AgressoFinanceEntry.NAMED_QUERY_FIND_BY_TICKET_NUMBER_NOT_RULED_ON);
		query.setParameter("ticketNumber", ticketNumber);
		AgressoFinanceEntry entry = (AgressoFinanceEntry) query.getSingleResult();
		Timestamp now = IWTimestamp.getTimestampRightNow();
		
		if(isProtested){
			String alreadyProtested = entry.getIsProtested();
			if(!"1".equals(alreadyProtested)){
				entry.setIsProtested("1");
				entry.setProtestedDate(now);
			}
		}
		else{
			entry.setIsProtested(null);
			entry.setProtestedDate(null);
		}
		
		if(rulingResult!=null){
			entry.setRulingResult(rulingResult);
			entry.setRulingResultDate(now);
		}
		else{
			entry.setRulingResult(null);
			entry.setRulingResultDate(null);
		}
		
		getAgressoDAO().persist(entry);
		
	}

}