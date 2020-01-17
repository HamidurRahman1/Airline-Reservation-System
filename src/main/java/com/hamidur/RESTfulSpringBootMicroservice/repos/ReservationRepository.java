package com.hamidur.RESTfulSpringBootMicroservice.repos;

import com.hamidur.RESTfulSpringBootMicroservice.models.Reservation;
import com.hamidur.RESTfulSpringBootMicroservice.models.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    // native query - MySQL
    @Query(value = "select * from reservations where customer_id = :customer_id", nativeQuery = true)
    Set<Reservation> findAllRSVPByCustomerId(@Param("customer_id") Integer customerId);

    // // Hibernate query from method name
    Set<Reservation> findReservationsByStatus(Status status);

    // native query - MySQL - inner join
    @Query(value = "select * from reservations r inner join flights f on f.flight_id in " +
            "(select flight_id from flights where airplane_id in " +
            "(select ap.airplane_id from airplanes ap inner join airlines al " +
            "on al.airline_id = (select airline_id  from airlines where lower(airline_name) = lower(:airline)) " +
            "and al.airline_id = ap.airline_id )) and r.flight_id = f.flight_id and lower(r.status) = lower(:status)", nativeQuery = true)
    Set<Reservation> findActiveReservationsByAirline(@Param("airline")String airline, @Param("status") String status);
}
