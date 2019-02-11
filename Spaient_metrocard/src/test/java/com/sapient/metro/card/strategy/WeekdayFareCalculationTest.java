package com.sapient.metro.card.strategy;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class WeekdayFareCalculationTest {

	FareCalulation fixture;

	@Before
	public void setup() {
		fixture = new WeekdayFareCalculation();
	}

	@Test
	public void test() {
		Assert.assertEquals(7.0f, fixture.getFare());
	}

}
