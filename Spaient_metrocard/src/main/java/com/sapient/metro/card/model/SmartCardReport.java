package com.sapient.metro.card.model;

import java.util.Date;

public class SmartCardReport {

	private SmartCard card;
	private MetroStation sourceStation;
	private MetroStation destinationStation;
	private int noOfStations;
	private Date swipeInTime;
	private Date swipeOutTime;
	private double travelFare;

	public double getTravelFare() {
		return travelFare;
	}

	public void setTravelFare(final double travelFare) {
		this.travelFare = travelFare;
	}

	public Date getSwipeInTime() {
		return swipeInTime;
	}

	public void setSwipeInTime(final Date swipeInTime) {
		this.swipeInTime = swipeInTime;
	}

	public Date getSwipeOutTime() {
		return swipeOutTime;
	}

	public void setSwipeOutTime(final Date swipeOutTime) {
		this.swipeOutTime = swipeOutTime;
	}

	public SmartCard getCard() {
		return card;
	}

	public void setCard(final SmartCard card) {
		this.card = card;
	}

	public MetroStation getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(final MetroStation sourceStation) {
		this.sourceStation = sourceStation;
	}

	public MetroStation getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(final MetroStation destinationStation) {
		this.destinationStation = destinationStation;
	}

	public int getNoOfStations() {
		return noOfStations;
	}

	public void setNoOfStations(final int noOfStations) {
		this.noOfStations = noOfStations;
	}

	@Override
	public String toString() {
		return "SmartCardReport [card=" + card + ", sourceStation=" + sourceStation + ", destinationStation="
				+ destinationStation + ", noOfStations=" + noOfStations + ", swipeInTime=" + swipeInTime
				+ ", swipeOutTime=" + swipeOutTime + ", travelFare=" + travelFare + "]";
	}

}
