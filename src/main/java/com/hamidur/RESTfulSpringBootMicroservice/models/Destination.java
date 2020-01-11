package com.hamidur.RESTfulSpringBootMicroservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "destinations")
public class Destination
{
    @Id
    @Column(name = "destination_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer destinationId;
    @Column(name = "date_time")
    private LocalDateTime localDateTime;
    @OneToOne
    @JoinColumn(name = "airport_id", referencedColumnName = "airport_id")
    private Airport airport;

    public Destination() {}

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination)) return false;
        Destination that = (Destination) o;
        return Objects.equals(getDestinationId(), that.getDestinationId()) &&
                Objects.equals(getLocalDateTime(), that.getLocalDateTime()) &&
                Objects.equals(getAirport().getAirportId(), that.getAirport().getAirportId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDestinationId(), getLocalDateTime(), getAirport().getAirportId());
    }

    @Override
    public String toString() {
        return "Destination{" +
                "destinationId=" + destinationId +
                ", localDateTime=" + localDateTime +
                ", airport=" + airport.getAirportId() +
                '}';
    }
}
