package com.sapient.metro.card.strategy;

import java.util.Calendar;
import java.util.Date;

public class FareCalculationFactory {

	private static final FareCalulation weekDayFare = new WeekdayFareCalculation();
	private static final FareCalulation weekEndFare = new WeekendFareCalculation();

	public static FareCalulation getFareCalulator(Date swipeOutTime) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(swipeOutTime);

		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if ((dayOfWeek == Calendar.SUNDAY) || (dayOfWeek == Calendar.SATURDAY)) {
			return weekEndFare;
		} else {
			return weekDayFare;
		}
	}
}
