package com.hamidur.springBootRESTfulWebservices.models;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "airports")
@NoArgsConstructor
@ToString
public class Airport
{
    @Id
    @Column(name = "airport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airportId;
    @Column(name = "airport_name")
    private String airportName;

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return Objects.equals(getAirportId(), airport.getAirportId()) &&
                Objects.equals(getAirportName(), airport.getAirportName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAirportId(), getAirportName());
    }
}
