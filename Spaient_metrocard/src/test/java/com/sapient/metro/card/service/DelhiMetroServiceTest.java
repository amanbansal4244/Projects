package com.sapient.metro.card.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sapient.metro.card.exceptions.IllegalOperationException;
import com.sapient.metro.card.exceptions.InsufficientBalanceException;
import com.sapient.metro.card.model.MetroStation;
import com.sapient.metro.card.model.SmartCard;
import com.sapient.metro.card.model.SmartCardReport;
import com.sapient.metro.card.model.User;

import junit.framework.Assert;

public class DelhiMetroServiceTest {

	MetroService fixture;
	SmartCard card;

	@Before
	public void setUp() throws Exception {
		fixture = new DelhiMetroService();
		card = new SmartCard();
		card.setSmartCardId(1l);
		card.setSmartCardBalance(50);
		card.setSmartCardUser(new User());
	}

	@Test
	public void testSwipeInAndSwipeOutWeekday() throws InsufficientBalanceException, IllegalOperationException {
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 9);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);
		fixture.swipeOut(MetroStation.A3, weekDay.getTime(), card);
		final List<SmartCardReport> cardReprt = fixture.getCardReport(card);
		Assert.assertEquals(1, cardReprt.size());
		Assert.assertEquals(36.0, cardReprt.get(0).getCard().getSmartCardBalance());
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A1));
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A3));
		Assert.assertEquals(0, fixture.getTotalFootFall(MetroStation.A2));
	}

	@Test
	public void testSwipeInAndSwipeOutWeekendSaturday() throws InsufficientBalanceException, IllegalOperationException {
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 10);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);
		fixture.swipeOut(MetroStation.A3, weekDay.getTime(), card);
		final List<SmartCardReport> cardReprt = fixture.getCardReport(card);
		Assert.assertEquals(1, cardReprt.size());
		Assert.assertEquals(39.0, cardReprt.get(0).getCard().getSmartCardBalance());
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A1));
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A3));
		Assert.assertEquals(0, fixture.getTotalFootFall(MetroStation.A2));
	}

	@Test
	public void testSwipeInAndSwipeOutWeekendSunday() throws InsufficientBalanceException, IllegalOperationException {
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 11);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);
		fixture.swipeOut(MetroStation.A5, weekDay.getTime(), card);
		final List<SmartCardReport> cardReprt = fixture.getCardReport(card);
		Assert.assertEquals(1, cardReprt.size());
		Assert.assertEquals(28.0, cardReprt.get(0).getCard().getSmartCardBalance());
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A1));
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A5));
		Assert.assertEquals(0, fixture.getTotalFootFall(MetroStation.A2));
	}

	@Test(expected = IllegalOperationException.class)
	public void testSwipeOutAndSwipeIn() throws InsufficientBalanceException, IllegalOperationException {
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 9);
		fixture.swipeOut(MetroStation.A3, weekDay.getTime(), card);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);

	}

	@Test(expected = InsufficientBalanceException.class)
	public void insufficientBalaAtSwipeIn() throws InsufficientBalanceException {
		card.setSmartCardBalance(5.4);
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 11);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);

	}

	@Test(expected = InsufficientBalanceException.class)
	public void insufficientBalaAtSwipeOut() throws InsufficientBalanceException, IllegalOperationException {

		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 9);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);
		fixture.swipeOut(MetroStation.A10, weekDay.getTime(), card);

	}

	@Test
	public void insufficientBalaAtSwipeIn_Boundary() throws InsufficientBalanceException {
		card.setSmartCardBalance(5.5);
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 11);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);

	}

	@Test
	public void insufficientBalaAtSwipeIn_Boundary_Plus_1() throws InsufficientBalanceException {
		card.setSmartCardBalance(5.6);
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 11);
		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);

	}

	@Test
	public void testMultipleSwipeInAndSwipeOutWeekday() throws InsufficientBalanceException, IllegalOperationException {
		final Calendar weekDay = new GregorianCalendar(2016, Calendar.SEPTEMBER, 9);

		fixture.swipeIn(MetroStation.A1, weekDay.getTime(), card);
		fixture.swipeOut(MetroStation.A3, weekDay.getTime(), card);

		fixture.swipeIn(MetroStation.A3, weekDay.getTime(), card);
		fixture.swipeOut(MetroStation.A5, weekDay.getTime(), card);

		fixture.swipeIn(MetroStation.A7, weekDay.getTime(), card);
		fixture.swipeOut(MetroStation.A8, weekDay.getTime(), card);

		final List<SmartCardReport> cardReprt = fixture.getCardReport(card);
		Assert.assertEquals(3, cardReprt.size());

		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A1));
		Assert.assertEquals(2, fixture.getTotalFootFall(MetroStation.A3));
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A5));
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A7));
		Assert.assertEquals(1, fixture.getTotalFootFall(MetroStation.A8));
	}

}
