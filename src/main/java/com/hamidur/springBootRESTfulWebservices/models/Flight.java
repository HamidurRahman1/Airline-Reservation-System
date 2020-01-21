package com.hamidur.springBootRESTfulWebservices.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "flights")
public class Flight
{
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;
    @Column(name = "flight_code", nullable = false)
    private String flightCode;
    @JoinColumn(name = "source_id", referencedColumnName = "source_id")
    @OneToOne
    private Source source;
    @JoinColumn(name = "destination_id", referencedColumnName = "destination_id")
    @OneToOne
    private Destination destination;
    @OneToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "airplane_id")
    private Airplane airplane;
    @Transient
    private Integer availableSeat;
    @Column(name = "fare")
    private Float fare;
    @Column(name = "capacity", updatable = false, nullable = false)
    private Integer capacity;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "customers_flights",
            inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")},
            joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "flight_id")}
    )
    private Set<Customer> customers;

    public Flight() {}

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return Objects.equals(getFlightId(), flight.getFlightId()) &&
                Objects.equals(getFlightCode(), flight.getFlightCode()) &&
                Objects.equals(getSource(), flight.getSource()) &&
                Objects.equals(getDestination(), flight.getDestination()) &&
                Objects.equals(getAirplane(), flight.getAirplane());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlightId(), getFlightCode(), getSource().hashCode(), getDestination().hashCode(), getAirplane().hashCode());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightCode='" + flightCode + '\'' +
                ", airplane=" + airplane +
                ", availableSeat=" + availableSeat +
                ", fare=" + fare +
                ", capacity=" + capacity +
                ", status=" + status +
                '}';
    }
}
