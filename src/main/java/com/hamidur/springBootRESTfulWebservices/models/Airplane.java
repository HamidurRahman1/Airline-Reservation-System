package com.hamidur.springBootRESTfulWebservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "airplanes")
public class Airplane implements Serializable
{
    @Id
    @Column(name = "airplane_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airplaneId;
    @Column(name = "airplane_name")
    private String airplaneName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "airline_id", name = "airline_id")
    private Airline airline;

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airplane)) return false;
        Airplane airplane = (Airplane) o;
        return Objects.equals(getAirplaneId(), airplane.getAirplaneId()) &&
                Objects.equals(getAirplaneName(), airplane.getAirplaneName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAirplaneId(), getAirplaneName());
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "airplaneId=" + airplaneId +
                ", airplaneName='" + airplaneName + '\'' +
                ", airline=" + airline.getAirlineName() +
                '}';
    }
}
