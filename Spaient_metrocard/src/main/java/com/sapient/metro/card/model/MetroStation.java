package com.sapient.metro.card.model;

public enum MetroStation {

	A1(0), A2(1), A3(2), A4(3), A5(4), A6(5), A7(6), A8(7), A9(8), A10(9);

	private int stationNumber;

	private MetroStation(final int stationNumber) {
		this.stationNumber = stationNumber;
	}

	public int getNoOfStations(final MetroStation station) {
		return (stationNumber - station.stationNumber);
	}
}
