package com.sapient.metro.card.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.sapient.metro.card.cache.CardReportCache;
import com.sapient.metro.card.constant.Constants;
import com.sapient.metro.card.exceptions.IllegalOperationException;
import com.sapient.metro.card.exceptions.InsufficientBalanceException;
import com.sapient.metro.card.model.MetroStation;
import com.sapient.metro.card.model.SmartCard;
import com.sapient.metro.card.model.SmartCardReport;
import com.sapient.metro.card.strategy.FareCalculationFactory;
import com.sapient.metro.card.strategy.FareCalulation;
import com.sapient.metro.card.utils.Utils;

public class DelhiMetroService implements MetroService {

	private final Map<MetroStation, AtomicLong> footFallCount = new ConcurrentHashMap<>();
	private final CardReportCache travelReportCache = new CardReportCache();

	@Override
	public List<SmartCardReport> getCardReport(final SmartCard card) {

		travelReportCache.getCompletedTravels(card).forEach(travelReport -> {

			System.out.println(String.format(Constants.OUTPUT_STRING, travelReport.getCard().getSmartCardId(),
					travelReport.getSourceStation(), travelReport.getDestinationStation(), travelReport.getTravelFare(),
					travelReport.getCard().getSmartCardBalance()));

		});
		return travelReportCache.getCompletedTravels(card);
	}

	@Override
	public void swipeIn(final MetroStation swipeInStation, final Date swipeInTime, final SmartCard card)
			throws InsufficientBalanceException {
		if (card.getSmartCardBalance() < 5.5) {
			throw new InsufficientBalanceException("Minimum Balance of 5.5 is needed to Travel");
		}
		footFallCount.putIfAbsent(swipeInStation, new AtomicLong());
		footFallCount.get(swipeInStation).incrementAndGet();

		final SmartCardReport cardTransaction = new SmartCardReport();
		cardTransaction.setSourceStation(swipeInStation);
		cardTransaction.setCard(card);
		cardTransaction.setSwipeInTime(swipeInTime);
		travelReportCache.addOngoingTravelDetals(card, cardTransaction);
	}

	@Override
	public void swipeOut(final MetroStation swipeOutStation, final Date swipeOutTime, final SmartCard card)
			throws InsufficientBalanceException, IllegalOperationException {
		final SmartCardReport cardReport = travelReportCache.getOngoingTravelDetail(card);
		if (cardReport == null) {
			throw new IllegalOperationException("No Swipe In Record found for Traveller");
		}
		footFallCount.putIfAbsent(swipeOutStation, new AtomicLong(0));
		footFallCount.get(swipeOutStation).incrementAndGet();

		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(swipeOutTime);
		final FareCalulation calculationStrategy = FareCalculationFactory.getFareCalulator(swipeOutTime);

		cardReport.setSwipeOutTime(swipeOutTime);
		cardReport.setDestinationStation(swipeOutStation);

		final double travelCost = Utils.getTravelFare(cardReport.getSourceStation(), swipeOutStation,
				calculationStrategy.getFare());
		if (cardReport.getCard().getSmartCardBalance() < travelCost) {
			throw new InsufficientBalanceException("Not Enough Balance in Card");
		}
		cardReport.getCard().setSmartCardBalance(cardReport.getCard().getSmartCardBalance() - travelCost);
		cardReport.setTravelFare(travelCost);

		travelReportCache.addCompletedTravel(card, cardReport);
	}

	@Override
	public long getTotalFootFall(final MetroStation station) {

		return footFallCount.getOrDefault(station, new AtomicLong(0)).get();

	}

}
