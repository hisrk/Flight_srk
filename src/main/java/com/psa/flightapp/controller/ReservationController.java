package com.psa.flightapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.psa.flightapp.dto.ReservationUpdateRequest;
import com.psa.flightapp.entities.Passenger;
import com.psa.flightapp.payload.ReservationDetailsDTO;
import com.psa.flightapp.repositories.PassengerRepository;
import com.psa.flightapp.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.psa.flightapp.dto.ReservationRequest;
import com.psa.flightapp.entities.Flight;
import com.psa.flightapp.entities.Reservation;
import com.psa.flightapp.repositories.FlightRepository;
import com.psa.flightapp.services.ReservationService;
import com.psa.flightapp.utilities.EmailUtil;
import com.psa.flightapp.utilities.PdfGenerator;

@Controller
public class ReservationController {
	

	private static  String filePath="D:\\flight_reservation system\\flight_reservation_app_6_542868_2024_03_04_22_38\\flight_reservation_app_6\\src\\newTickets\\booking";
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private ReservationService reservationService;
	@Autowired
	 private ReservationRepository reservationRepository;

	@Autowired
	private PassengerRepository passengerRepository;
	
	@RequestMapping("/showCompleteReservation")
	public String completeReservation(@RequestParam("flightId") Long flightId,ModelMap modelMap) {
		System.out.println(flightId);
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		modelMap.addAttribute("flights", flight);
		return "showCompleteReservation";
	}
	
	@RequestMapping("/confirmRegistration")
	public String confirmRegistration(ReservationRequest request,ModelMap modelMap) {
		Reservation reservation = reservationService.bookFlight(request);


		PdfGenerator pdf = new PdfGenerator();
		pdf.generatePDF(filePath+reservation.getId()+".pdf", reservation.getPassenger().getFirstName(), reservation.getPassenger().getEmail(), reservation.getPassenger().getPhone(), reservation.getFlight().getOperatingAirlines(), reservation.getFlight().getDateOfDepartureStr(), reservation.getFlight().getDepartureCity(), reservation.getFlight().getArrivalCity());
		modelMap.addAttribute("reservationId", reservation.getId());
		EmailUtil util = new EmailUtil();
		String attachment = filePath+reservation.getId()+".pdf";
		emailUtil.sendItinerary(request.getEmail(), attachment);
		return "finalConfirmation";
	}
	@RequestMapping("/reservation/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		Optional<Reservation> findById = reservationRepository.findById(id);
		Reservation reservation = findById.get();
		return reservation;
	}

	@RequestMapping("/reservation")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> findById = reservationRepository.findById(request.getId());
		Reservation reservation = findById.get();
		reservation.setCheckedIn(request.isCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		return reservationRepository.save(reservation);
	}
	@GetMapping("/showReservations")
	public String getAllBookingDetailsOfUsers(Model model) {
		List<Reservation> reservations = reservationRepository.findAll();

		List<ReservationDetailsDTO> reservationDetailsDTOList = new ArrayList<>();

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



		model.addAttribute("reservationDetailsList", reservationDetailsDTOList);
		return "login/Reservations";
	}

}
