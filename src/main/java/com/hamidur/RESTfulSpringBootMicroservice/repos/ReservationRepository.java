package com.hamidur.RESTfulSpringBootMicroservice.repos;

import com.hamidur.RESTfulSpringBootMicroservice.models.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    // native query - MySQL
    @Query(value = "select * from reservations where customer_id = :customer_id", nativeQuery = true)
    Set<Reservation> findAllRSVPByCustomerId(@Param("customer_id") Integer customerId);

}
