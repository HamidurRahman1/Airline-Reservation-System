package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.errors.InvalidRequestException;
import com.hamidur.RESTfulSpringBootMicroservice.models.Customer;
import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;
import com.hamidur.RESTfulSpringBootMicroservice.models.Reservation;
import com.hamidur.RESTfulSpringBootMicroservice.models.Status;
import com.hamidur.RESTfulSpringBootMicroservice.repos.ReservationRepository;
import com.hamidur.RESTfulSpringBootMicroservice.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService
{
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final FlightService flightService;

    @Autowired
    public ReservationService(final ReservationRepository reservationRepository,
                              final CustomerService customerService, final FlightService flightService)
    {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
        this.flightService = flightService;
    }

    public Set<Reservation> getAllRSVPsByCustomerId(Integer customerId) throws IllegalArgumentException
    {
        if(Util.validateNumber(customerId))
        {
            Iterable<Reservation> iterable = reservationRepository.findAllRSVPByCustomerId(customerId);
            return iterableToReservationSet(iterable);
        }
        return null;
    }

    public boolean addRSVPByCustomerId(Map<String, Object> json) throws InvalidRequestException, NoSuchElementException
    {
        int customerId = -1;
        int flightId = -1;

        if(Util.verifyRSVPByCustomerId(json))
        {
            customerId = (Integer) json.get(Util.CUSTOMER_ID_JKEY);
            Customer customer = customerService.getCustomerById(customerId);
            if(customer == null) throw new NoSuchElementException("Customer does not exists with id="+customerId);

            flightId = (Integer)json.get(Util.FLIGHT_ID_JKEY);
            Flight flight = flightService.getFlightById(flightId);
            if(flight == null) throw new NoSuchElementException("Flight does not exists with id="+flightId);

            reservationRepository.insertRSVPByCustomerId(Util.toDBDateTime(LocalDateTime.now()), Status.ACTIVE.toString(), customerId, flightId);

            flight.getCustomers().add(customer);

            Flight updated = flightService.saveFlight(flight);
            return updated.getFlightId().equals(flight.getFlightId());
        }
        return false;
    }

    public boolean cancelRSVPByCustomerId(Integer rsvpId) throws IllegalArgumentException
    {
        if(Util.validateNumber(rsvpId))
        {
            Optional<Reservation> optionalReservation = reservationRepository.findById(rsvpId);
            if (!optionalReservation.isPresent())
                throw new IllegalArgumentException("No reservation exists with id=" + rsvpId);

            Reservation reservation = optionalReservation.get();

            reservation.setStatus(Status.CANCELLED);
            reservation.getFlight().getCustomers().remove(reservation.getCustomer());

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public Set<Reservation> getAllCancelledRSVPs()
    {
        Iterable<Reservation> iterable = reservationRepository.findReservationsByStatus(Status.CANCELLED);
        return iterableToReservationSet(iterable);
    }

    public Set<Reservation> getAllActiveRSVPsByAirline(String airline) throws IllegalArgumentException
    {
        if(Util.validateAirlineName(airline))
        {
            Iterable<Reservation> iterable = reservationRepository.findActiveReservationsByAirline(airline, Status.ACTIVE.toString());
            return iterableToReservationSet(iterable);
        }
        return null;
    }

    public Set<Reservation> getAllCancelledRSVPsByAirline(String airline) throws IllegalArgumentException
    {
        if(Util.validateAirlineName(airline))
        {
            Iterable<Reservation> iterable = reservationRepository.findActiveReservationsByAirline(airline, Status.CANCELLED.toString());
            return iterableToReservationSet(iterable);
        }
        return null;
    }

    private Set<Reservation> iterableToReservationSet(Iterable<Reservation> iterable)
    {
        if(iterable != null)
        {
            Set<Reservation> reservations = new LinkedHashSet<>();
            iterable.forEach(reservation -> reservations.add(reservation));
            return reservations;
        }
        return null;
    }
}
