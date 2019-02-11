package com.sapient.metro.card.service;

import java.util.Date;
import java.util.List;

import com.sapient.metro.card.exceptions.IllegalOperationException;
import com.sapient.metro.card.exceptions.InsufficientBalanceException;
import com.sapient.metro.card.model.MetroStation;
import com.sapient.metro.card.model.SmartCard;
import com.sapient.metro.card.model.SmartCardReport;

public interface MetroService {

	public long getTotalFootFall(MetroStation station);

	public List<SmartCardReport> getCardReport(SmartCard card);

	public void swipeIn(MetroStation swipeInStation, Date swipeTime, SmartCard card)
			throws InsufficientBalanceException;

	public void swipeOut(MetroStation swipeOutStation, Date swipeTime, SmartCard card)
			throws InsufficientBalanceException, IllegalOperationException;

}
