package com.psa.flightapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.psa.flightapp.entities.Reservation;
import com.psa.flightapp.entities.User;
import com.psa.flightapp.payload.ReservationDetailsDTO;
import com.psa.flightapp.repositories.PassengerRepository;
import com.psa.flightapp.repositories.ReservationRepository;
import com.psa.flightapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.psa.flightapp.entities.Flight;
import com.psa.flightapp.repositories.FlightRepository;

import javax.servlet.http.HttpSession;

@Controller
public class FlightController {

	@Autowired
	private FlightRepository flightRepo;
	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate") String dateOfDeparture, ModelMap modelMap) {
		List<Flight> flights = flightRepo.findFlights(from, to, dateOfDeparture);

		System.out.println(flights);
		modelMap.addAttribute("flights", flights);
		return "displayFlights";
	}

	@PostMapping("/saveFlight")
	public String saveFlight(Flight flight, Model model, HttpSession session) {

		flightRepo.save(flight);


		List<Flight> flights = flightRepo.findAll();


		model.addAttribute("flights", flights);


		return "login/flightDetails";
	}


	@GetMapping("/delete")

	public String deleteFlight(@RequestParam("flightId") long id, Model model) {

		flightRepo.deleteById(id);


		List<Flight> flights = flightRepo.findAll();


		model.addAttribute("flights", flights);

		return "login/flightDetails";


	}

	@PostMapping("/update")
	public String updateCheckInAndNumberOfBags(@RequestParam("id") long id, Reservation reservation1, Model model, HttpSession httpSession) {
		Object loggedInUser = httpSession.getAttribute("loggedInUser");
		if (loggedInUser == null || !(loggedInUser instanceof User)) {
			System.out.println("User not logged in");
			httpSession.invalidate();
			return "login/login";
		}

		User user = (User) loggedInUser;
		if (!"admin".equals(user.getRole())) {
			System.out.println("Non-admin user attempting to update");
			httpSession.invalidate();
			return "login/login"; // Redirect to an error page indicating permission denied
		}

		System.out.println("Admin user logged in, attempting update with ID: " + id);

		Optional<Reservation> reservationOptional = reservationRepository.findById(id);
		if (reservationOptional.isPresent()) {
			Reservation reservation2 = reservationOptional.get();
			reservation2.setCheckedIn(true);
			reservation2.setNumberOfBags(reservation1.getNumberOfBags());
			reservationRepository.save(reservation2);

			System.out.println("Reservation updated successfully");

			// Populate reservation details for display
			List<Reservation> reservations = reservationRepository.findAll();
			List<ReservationDetailsDTO> reservationDetailsDTOList = new ArrayList<>();

			// Mapping reservations to DTOs
			for (Reservation reservation : reservations) {
				ReservationDetailsDTO reservationDetailsDTO = new ReservationDetailsDTO();
				reservationDetailsDTO.setId(reservation.getId());
				reservationDetailsDTO.setFlightNumber(reservation.getFlight().getFlightNumber());
				reservationDetailsDTO.setOperatingAirlines(reservation.getFlight().getOperatingAirlines());
				reservationDetailsDTO.setDepartureCity(reservation.getFlight().getDepartureCity());
				reservationDetailsDTO.setArrivalCity(reservation.getFlight().getArrivalCity());
				reservationDetailsDTO.setDateOfDeparture(reservation.getFlight().getDateOfDepartureStr());
				reservationDetailsDTO.setEstimatedDepartureTime(reservation.getFlight().getEstimatedDepartureTimeStr());
				reservationDetailsDTO.setPassengerName(reservation.getPassenger().getFirstName() + " " + reservation.getPassenger().getLastName());
				reservationDetailsDTO.setEmail(reservation.getPassenger().getEmail());
				reservationDetailsDTO.setPhone(reservation.getPassenger().getPhone());
				reservationDetailsDTO.setCheckedIn(reservation.isCheckedIn());
				reservationDetailsDTO.setNumberOfBags(reservation.getNumberOfBags());
				reservationDetailsDTOList.add(reservationDetailsDTO);
			}

			// Add reservation details to model
			model.addAttribute("reservationDetailsList", reservationDetailsDTOList);
			return "login/Reservations";
		} else {
			System.out.println("Reservation not found for ID: " + id);
			return "login/login"; // Redirect to an error page indicating reservation not found
		}
	}

}





















