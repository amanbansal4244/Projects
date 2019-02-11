package com.sapient.metro.card.utils;

import com.sapient.metro.card.model.MetroStation;

public class Utils {

	public static double getTravelFare(final MetroStation source, final MetroStation destination,
			final double floatUnit) {
		if (destination.getNoOfStations(source) < 0) {
			throw new IllegalArgumentException("Illegal Source and Destinations");
		}
		return destination.getNoOfStations(source) * floatUnit;
	}
}
