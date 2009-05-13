package is.idega.block.agresso.business;

import is.idega.block.agresso.dao.AgressoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.util.IWTimestamp;

@Scope("singleton")
@Service("agressoFinanceBusiness")
public class AgressoFinanceBusiness {
	
	@Autowired
	private AgressoDAO agressoDAO;
	
	public void createParkingEntry(String user, Integer amount, String info) {
		IWTimestamp paymentDate = new IWTimestamp();
		paymentDate.addDays(14);
		agressoDAO.addFinanceEntry("PARKING", user, amount, paymentDate.getTimestamp(), info);
	}
}
