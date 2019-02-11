package com.sapient.metro.card.utils;

import org.junit.Test;

import com.sapient.metro.card.model.MetroStation;

import junit.framework.Assert;

public class UtilsTest {

	@Test
	public void testSuccess() {
		final double fare = Utils.getTravelFare(MetroStation.A1, MetroStation.A3, 5f);
		Assert.assertEquals(10.0, fare);
		Assert.assertEquals(0.0, Utils.getTravelFare(MetroStation.A1, MetroStation.A1, 5f));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailure() {
		Utils.getTravelFare(MetroStation.A3, MetroStation.A1, 5f);

	}

}
