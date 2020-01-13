package com.hamidur.RESTfulSpringBootMicroservice.repos;

import com.hamidur.RESTfulSpringBootMicroservice.models.Flight;
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
                    " on s.source_id = f.source_id and s.date_time >= '2019-01-13T01:00:00'", nativeQuery = true)
    Set<Flight> findByCurrentDateTime();
}
