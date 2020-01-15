package com.hamidur.RESTfulSpringBootMicroservice.repos;

import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;
import com.hamidur.RESTfulSpringBootMicroservice.models.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Integer>
{
    @Query(value = "select * from flights f inner join sources s" +
                    " on s.source_id = f.source_id and s.date_time >= :date_time", nativeQuery = true)
    Set<Flight> findByCurrentDateTime(@Param("date_time") LocalDateTime dateTime);

    @Query(value = "select * from flights f inner join sources s" +
                    " on s.source_id = f.source_id and s.date_time >= :date_time", nativeQuery = true)
    Set<Flight> findByDate(@Param("date_time") LocalDateTime dateTime);

    @Query(value = "select f from Flight f where f.fare <= :fare and f.fare >= 0")
    Set<Flight> findByFare(@Param("fare") Float fare);

    Set<Flight> findFlightsByStatus(Status status);
}
