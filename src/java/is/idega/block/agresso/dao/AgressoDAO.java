package is.idega.block.agresso.dao;

import java.sql.Timestamp;

import com.idega.core.persistence.GenericDao;

public interface AgressoDAO extends GenericDao {
	public void addFinanceEntry(String entryType, String userSSN, Integer amount, Timestamp paymentDate, String info);
}
