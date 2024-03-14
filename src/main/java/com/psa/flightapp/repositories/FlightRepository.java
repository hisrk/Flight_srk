package com.psa.flightapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.psa.flightapp.entities.Flight;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	//@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDepartureStr")
	//@Query("from Flight where departureCity = :departureCity and arrivalCity = :arrivalCity and dateOfDeparture = :dateOfDepartureStr")


//	List<Flight> findFlights(@Param("departureCity") String from,@Param("arrivalCity") String to,@Param("dateOfDeparture") String dateOfDeparture);
	//@Query("FROM Flight WHERE departureCity = :departureCity AND arrivalCity = :arrivalCity AND dateOfDepartureStr = :dateOfDepartureStr")
	@Query("FROM Flight WHERE departureCity = :departureCity AND arrivalCity = :arrivalCity AND dateOfDepartureStr = :dateOfDepartureStr")
	List<Flight> findFlights(@Param("departureCity") String departureCity,
							 @Param("arrivalCity") String arrivalCity,
							 @Param("dateOfDepartureStr") String dateOfDepartureStr);



}
