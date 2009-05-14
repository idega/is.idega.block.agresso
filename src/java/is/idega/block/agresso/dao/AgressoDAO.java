package is.idega.block.agresso.dao;

import java.util.Date;

import com.idega.core.persistence.GenericDao;

public interface AgressoDAO extends GenericDao {
	public void addFinanceEntry(String entryType, String userSSN, Integer amount, Date paymentDate, String info);
}
