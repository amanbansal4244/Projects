package com.sapient.metro.card.strategy;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class WeekendFareCalculationTest {

	FareCalulation fixture;

	@Before
	public void setup() {
		fixture = new WeekendFareCalculation();
	}

	@Test
	public void test() {
		Assert.assertEquals(5.5f, fixture.getFare());
	}

}
