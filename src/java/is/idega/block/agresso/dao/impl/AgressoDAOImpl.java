package is.idega.block.agresso.dao.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.builder.bean.AdvancedProperty;
import com.idega.core.persistence.Param;
import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.idegaweb.IWMainApplication;
import com.idega.util.ArrayUtil;
import com.idega.util.CoreConstants;
import com.idega.util.IWTimestamp;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.datastructures.map.MapUtil;

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
		AgressoFinanceEntry entry = addFinanceEntryParking(entryType, userSSN, amount, paymentDate, null, info, null, null, null, null, null, null, null, null, null, null, null);
		return entry == null || entry.getID() == null ? null : entry.getID();
	}

	@Override
	@Transactional(readOnly = false)
	public AgressoFinanceEntry addFinanceEntryParking(String entryType, String userSSN,
			Integer amount, Timestamp paymentDate, Date creationDate, String info,
			String registrationNumber, String permanentNumber, String carType,
			String owner, String ticketNumber, String ticketOfficer,
			String streetName, String streetNumber, String streetDescription,
			String meterNumber, String invoiceNumber
	) {
		try {
			List<AgressoFinanceEntry> entries = null;
			try {
				entries = getResultList(AgressoFinanceEntry.NAMED_QUERY_FIND_BY_TICKET_NUMBER, AgressoFinanceEntry.class, new Param("ticketNumber", ticketNumber));
			} catch (Exception e) {}
			if (!ListUtil.isEmpty(entries)) {
				AgressoFinanceEntry entry = entries.iterator().next();
				getLogger().info("Found existing entry " + entry + " for ticket number " + ticketNumber + ", not creating another one");
				return entry;
			}

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

			return entry.getID() == null ? null : entry;
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
	public List<AgressoFinanceEntryForParkingCard> getEntriesInAgressoForParkingCard(
			String userSSN,
			Integer amount,
			String registrationNumber,
			String permanentNumber,
			String owner,
			String parkingZone,
			String apartmentIdentifier
	) {
		try {
			return getResultListByInlineQuery(
					"select e from " + AgressoFinanceEntryForParkingCard.class.getName() + " e where e.entryUser = :userSSN and e.amount = :amount and " +
					"e.registrationNumber = :registrationNumber and e.permanentNumber = :permanentNumber and e.owner = :owner and e.parkingZone = " +
					":parkingZone and e.apartmentIdentifier = :apartmentIdentifier and e.validFrom is null and e.validTo >= :now",
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
	public int getDelayForParkingCardPayment() {
		return IWMainApplication.getDefaultIWMainApplication().getSettings().getInt("parking.card_payment_in", 3);
	}

	@Override
	@Transactional
	public Long addFinanceEntryParkingForParkingCard(
			String caseNumber,
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
	) {
		try {
			creationDate = creationDate == null ? IWTimestamp.getTimestampRightNow() : creationDate;
			IWTimestamp payFrom = paymentDate != null ?
					new IWTimestamp(paymentDate) :
					validFrom == null ? new IWTimestamp(creationDate) : new IWTimestamp(validFrom);

			if (splitPayment != null && splitPayment > 1) {
				Long firstEntryId = null;
				int delay = getDelayForParkingCardPayment();
				IWTimestamp lastMonth = null;
				for (int i = 0; i < splitPayment; i++) {
					Timestamp splitPaymentDate = null;

					if (i == 0) {
						IWTimestamp iwPaymentDate = null;
						if (paymentDate == null) {
							iwPaymentDate = new IWTimestamp(payFrom);
							if (paymentDate == null && delay > 0) {
								iwPaymentDate.addDays(delay);
							}
						} else {
							iwPaymentDate = new IWTimestamp(paymentDate);
						}
						lastMonth = iwPaymentDate;
						paymentDate = iwPaymentDate.getTimestamp();
						splitPaymentDate = paymentDate;
					} else {
						IWTimestamp iwNextMonth = new IWTimestamp(payFrom);
						int currentMonth = iwNextMonth.getMonth();
						iwNextMonth.setMonth((currentMonth + i));
						//	Checking if not jumped over a month, i.e. from January 30th to March 2 IF February has 28 days
						while (iwNextMonth.getMonth() - lastMonth.getMonth() > 1) {
							iwNextMonth.addDays(-1);
						}

						lastMonth = iwNextMonth;
						Timestamp nextMonth = iwNextMonth.getTimestamp();
						paymentDate = nextMonth;
						splitPaymentDate = nextMonth;
					}

					Long id = doCreateFinanceEntryParkingForParkingCard(
							caseNumber,
							entryType,
							userSSN,
							amount,
							paymentDate,
							creationDate,
							info,
							registrationNumber,
							permanentNumber,
							carType,
							owner,
							parkingCardNumber,
							invoiceNumber,
							parkingZone,
							validFrom,
							validTo,
							apartmentIdentifier,
							paymentStatus,
							splitPaymentDate,
							(i + 1)
					);

					firstEntryId = i == 0 || firstEntryId == null ? id : firstEntryId;
				}

				return firstEntryId;
			}

			return doCreateFinanceEntryParkingForParkingCard(
					caseNumber,
					entryType,
					userSSN,
					amount,
					paymentDate,
					creationDate,
					info,
					registrationNumber,
					permanentNumber,
					carType,
					owner,
					parkingCardNumber,
					invoiceNumber,
					parkingZone,
					validFrom,
					validTo,
					apartmentIdentifier,
					paymentStatus,
					null,
					1
			);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error creating entry for parking card in Agresso DB table. Type: " + entryType + ", for user: " + userSSN +
					", parking card number: " + parkingCardNumber, e);
		}

		return null;
	}

	@Transactional(readOnly = false)
	private Long doCreateFinanceEntryParkingForParkingCard(
			String caseNumber,
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
			Timestamp splitPaymentDate,
			Integer paymentNumber
	) {
		AgressoFinanceEntryForParkingCard entry = new AgressoFinanceEntryForParkingCard();
		entry.setAmount(amount);

		entry.setCreationDate(creationDate);

		entry.setCaseNumber(caseNumber);
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

		entry.setPaymentStatus(paymentStatus);
		entry.setSplitPaymentDate(splitPaymentDate);
		entry.setPaymentNumber(paymentNumber);

		getEntityManager().persist(entry);

		return entry.getId();
	}

	@Override
	public List<AgressoFinanceEntryForParkingCard> getByRegistrationNumber(String registrationNumber, Timestamp validTo, boolean validOnly) {
		if (StringUtil.isEmpty(registrationNumber)) {
			return null;
		}

		try {
			registrationNumber = registrationNumber.trim();
			if (StringUtil.isEmpty(registrationNumber)) {
				return null;
			}

			registrationNumber = registrationNumber.toUpperCase();

			List<Param> params = new ArrayList<>();
			params.add(new Param(AgressoFinanceEntryForParkingCard.PARAM_REGISTRATION_NUMBER, registrationNumber));
			if (validOnly) {
				return getResultList(
						AgressoFinanceEntryForParkingCard.NAMED_QUERY_FIND_VALID_BY_REGISTRATION_NUMBER,
						AgressoFinanceEntryForParkingCard.class,
						ArrayUtil.convertListToArray(params)
				);
			}

			if (validTo == null) {
				return getResultList(
						AgressoFinanceEntryForParkingCard.NAMED_QUERY_FIND_BY_REGISTRATION_NUMBER,
						AgressoFinanceEntryForParkingCard.class,
						ArrayUtil.convertListToArray(params)
				);
			}

			params.add(new Param(AgressoFinanceEntryForParkingCard.PARAM_VALID_TO, validTo));
			return getResultList(
					AgressoFinanceEntryForParkingCard.NAMED_QUERY_FIND_BY_REGISTRATION_NUMBER_AND_VALID_TO,
					AgressoFinanceEntryForParkingCard.class,
					ArrayUtil.convertListToArray(params)
			);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting finance entry/entries of parking card for vehicle " + registrationNumber);
		}

		return null;
	}

	@Override
	public List<AgressoFinanceEntryForParkingCard> getByRegistrationNumberAndParkingCardNumber(String registrationNumber, String parkingCardNumber) {
		if (StringUtil.isEmpty(registrationNumber) || StringUtil.isEmpty(parkingCardNumber)) {
			return null;
		}

		registrationNumber = registrationNumber.trim();
		registrationNumber = registrationNumber.toUpperCase();

		Map<String, List<AgressoFinanceEntryForParkingCard>> data = getByRegistrationNumbersAndParkingCardsNumbers(
				Arrays.asList(
						new AdvancedProperty(registrationNumber, parkingCardNumber)
				)
		);
		if (MapUtil.isEmpty(data)) {
			return null;
		}

		return data.get(registrationNumber);
	}

	@Override
	public Map<String, List<AgressoFinanceEntryForParkingCard>> getByRegistrationNumbersAndParkingCardsNumbers(List<AdvancedProperty> carRegistrationAndCardNumbers) {
		if (ListUtil.isEmpty(carRegistrationAndCardNumbers)) {
			return null;
		}

		try {
			Set<String> registrationNumbers = new HashSet<>();
			Set<String> parkingCardsNumbers = new HashSet<>();
			Map<String, AdvancedProperty> mapping = new HashMap<>();
			for (AdvancedProperty carRegistrationAndCardNumber: carRegistrationAndCardNumbers) {
				String registrationNumber = carRegistrationAndCardNumber.getId();
				String cardNumber = carRegistrationAndCardNumber.getValue();
				if (StringUtil.isEmpty(registrationNumber) || StringUtil.isEmpty(cardNumber)) {
					continue;
				}

				registrationNumber = registrationNumber.trim();
				registrationNumber = registrationNumber.toUpperCase();
				carRegistrationAndCardNumber.setId(registrationNumber);
				registrationNumbers.add(registrationNumber);
				parkingCardsNumbers.add(cardNumber);

				mapping.put(registrationNumber.concat(CoreConstants.AT).concat(cardNumber), carRegistrationAndCardNumber);
			}

			List<AgressoFinanceEntryForParkingCard> data = getResultList(
					AgressoFinanceEntryForParkingCard.NAMED_QUERY_FIND_BY_REGISTRATION_NUMBERS_AND_CARDS_NUMBERS,
					AgressoFinanceEntryForParkingCard.class,
					new Param(AgressoFinanceEntryForParkingCard.PARAM_REGISTRATION_NUMBER, registrationNumbers),
					new Param(AgressoFinanceEntryForParkingCard.PARAM_CARD_NUMBER, parkingCardsNumbers)
			);
			if (ListUtil.isEmpty(data)) {
				return null;
			}

			Map<String, List<AgressoFinanceEntryForParkingCard>> results = new LinkedHashMap<>();
			for (AgressoFinanceEntryForParkingCard entry: data) {
				String registrationNumber = entry.getRegistrationNumber();
				String cardNumber = entry.getParkingCardNumber();
				if (StringUtil.isEmpty(registrationNumber) || StringUtil.isEmpty(cardNumber)) {
					continue;
				}

				String key = registrationNumber.concat(CoreConstants.AT).concat(cardNumber);
				if (mapping.containsKey(key)) {
					List<AgressoFinanceEntryForParkingCard> entriesForCar = results.get(registrationNumber);
					if (entriesForCar == null) {
						entriesForCar = new ArrayList<>();
						results.put(registrationNumber, entriesForCar);
					}
					entriesForCar.add(entry);
				}
			}
			return results;
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error getting finance entry/entries of parking card for vehicle(s) and card(s) number(s) " + carRegistrationAndCardNumbers, e);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean setStatusForParkingCard(String registrationNumber, String parkingCardNumber, String status) {
		List<AgressoFinanceEntryForParkingCard> cards = null;
		try {
			cards = getByRegistrationNumberAndParkingCardNumber(registrationNumber, parkingCardNumber);
			if (ListUtil.isEmpty(cards)) {
				getLogger().warning("Did not find finance entry/entries of parking card for vehicle " + registrationNumber + " and card number " + parkingCardNumber);
				return false;
			}

			for (AgressoFinanceEntryForParkingCard card: cards) {
				card.setPaymentStatus(status);
				merge(card);
			}

			getLogger().info("Set status '" + status + "' for parking cards " + cards);
			return true;
		} catch (Exception e) {
			getLogger().log(
					Level.WARNING,
					"Error setting status '" + status + "' for parking cards " + cards + " for vehicle " + registrationNumber + " and card number " + parkingCardNumber,
					e
			);
		}

		return false;
	}

	@Override
	public AgressoFinanceEntry getParkingEntryByTicketNumber(String ticketNumber) {
		if (StringUtil.isEmpty(ticketNumber)) {
			return null;
		}

		try {
			List<AgressoFinanceEntry> entries = null;
			try {
				entries = getResultList(AgressoFinanceEntry.NAMED_QUERY_FIND_BY_TICKET_NUMBER, AgressoFinanceEntry.class, new Param("ticketNumber", ticketNumber));
			} catch (Exception e) {}

			if (ListUtil.isEmpty(entries)) {
				getLogger().warning("Failed to find existing entry for ticket number " + ticketNumber);
				return null;
			}

			if (entries.size()  > 1) {
				getLogger().warning("Found multiple entries for ticket number " + ticketNumber + ": " + entries);
			}
			return entries.iterator().next();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Error getting parking entry for financial system by ticket number " + ticketNumber, e);
		}

		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public AgressoFinanceEntry updateAgressoFinanceEntry(AgressoFinanceEntry entry) {
		if (entry == null) {
			getLogger().warning("AgressoFinanceEntry is not provided");
			return entry;
		}

		try {
			if (entry.getID() == null) {
				persist(entry);
			} else {
				merge(entry);
			}
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Could not create/update finance entry " + entry, e);
		}

		return entry.getID() == null ? null : entry;
	}

	@Override
	@Transactional(readOnly = false)
	public void setAsRead(Long entryId, boolean read, Date readDate) {
		if (entryId == null) {
			return;
		}

		AgressoFinanceEntry entry = null;
		try {
			entry = getSingleResult(AgressoFinanceEntry.NAMED_QUERY_FIND_BY_ID, AgressoFinanceEntry.class, new Param("id", entryId));
			if (entry == null) {
				return;
			}

			entry.setIsReadDate(readDate == null ? IWTimestamp.RightNow().getDate() : readDate);
			entry.setIsRead(read ? String.valueOf(1) : null);
			merge(entry);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "Error setting fin. entry " + entry + " as " + (read ? "read" : "not read"), e);
		}
	}

}