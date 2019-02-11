package com.sapientAssignment.feeCalculator.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TransactionAttributesUtility {
	/**
	 * Create private constructor
	 */
	private TransactionAttributesUtility() {
	}

	private volatile static TransactionAttributesUtility INSTANCE = null;;

	/**
	 * Create a static method to get instance.
	 */
	public static TransactionAttributesUtility getInstance() {
		if(INSTANCE == null){
			synchronized (TransactionAttributesUtility.class) {
				if(INSTANCE == null){
					INSTANCE = new TransactionAttributesUtility();
				}
			}
		}
		return INSTANCE;
	}

	public Date getDate(String date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate = null;
		try {
			parsedDate = sdf.parse(date);
			return parsedDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
}
