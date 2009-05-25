package is.idega.block.agresso.business;

import is.idega.block.agresso.dao.AgressoDAO;

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
		getAgressoDAO().addFinanceEntry("PARKING", user, amount,
				paymentDate.getTimestamp(), info);
	}

	protected AgressoDAO getAgressoDAO() {
		if (agressoDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		return agressoDAO;
	}

}