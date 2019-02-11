package com.sapient.metro.card.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sapient.metro.card.model.SmartCard;
import com.sapient.metro.card.model.SmartCardReport;

public class CardReportCache {
	private final Map<SmartCard, SmartCardReport> ongoingTravelReport = new ConcurrentHashMap<SmartCard, SmartCardReport>();
	private final Map<SmartCard, List<SmartCardReport>> completedTravels = new ConcurrentHashMap<>();

	public void addOngoingTravelDetals(final SmartCard card, final SmartCardReport smartCardReport) {
		ongoingTravelReport.put(card, smartCardReport);
	}

	public SmartCardReport getOngoingTravelDetail(final SmartCard card) {
		return ongoingTravelReport.remove(card);
	}

	public List<SmartCardReport> getCompletedTravels(final SmartCard card) {
		return completedTravels.getOrDefault(card, new ArrayList<SmartCardReport>(0));
	}

	public void addCompletedTravel(final SmartCard card, final SmartCardReport smartCardReport) {
		completedTravels.putIfAbsent(card, new ArrayList<SmartCardReport>(0));
		completedTravels.get(card).add(smartCardReport);
	}
}
