package com.psa.flightapp.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Flight extends EntityClass{

	private String flightNumber;
	private String operatingAirlines;

	@Override
	public String toString() {
		return "Flight{" +
				"flightNumber='" + flightNumber + '\'' +
				", operatingAirlines='" + operatingAirlines + '\'' +
				", departureCity='" + departureCity + '\'' +
				", arrivalCity='" + arrivalCity + '\'' +
				", dateOfDepartureStr='" + dateOfDepartureStr + '\'' +
				", estimatedDepartureTimeStr='" + estimatedDepartureTimeStr + '\'' +
				'}';
	}

	private String departureCity;
	private String arrivalCity;
	private String dateOfDepartureStr;
	private String estimatedDepartureTimeStr;


	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOperatingAirlines() {
		return operatingAirlines;
	}

	public void setOperatingAirlines(String operatingAirlines) {
		this.operatingAirlines = operatingAirlines;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public String getDateOfDepartureStr() {
		return dateOfDepartureStr;
	}

	public void setDateOfDepartureStr(String dateOfDepartureStr) {
		this.dateOfDepartureStr = dateOfDepartureStr;
	}

	public String getEstimatedDepartureTimeStr() {
		return estimatedDepartureTimeStr;
	}

	public void setEstimatedDepartureTimeStr(String estimatedDepartureTimeStr) {
		this.estimatedDepartureTimeStr = estimatedDepartureTimeStr;
	}
}
