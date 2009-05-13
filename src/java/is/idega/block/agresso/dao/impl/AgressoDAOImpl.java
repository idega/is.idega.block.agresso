package is.idega.block.agresso.dao.impl;

import is.idega.block.agresso.dao.AgressoDAO;
import is.idega.block.agresso.data.AgressoFinanceEntry;

import java.sql.Timestamp;

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
			Integer amount, Timestamp paymentDate, String info) {
		AgressoFinanceEntry entry = new AgressoFinanceEntry();
		entry.setAmount(amount);
		entry.setCreationDate(IWTimestamp.getTimestampRightNow());
		entry.setDisputed(false);
		entry.setRead(false);
		entry.setEntryUser(userSSN);
		entry.setEntryType(entryType);
		entry.setPaymentDate(paymentDate);
		entry.setInfo(info);
		
		getEntityManager().persist(entry);
	}

}
