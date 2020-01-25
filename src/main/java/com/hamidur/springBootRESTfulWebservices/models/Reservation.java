package com.hamidur.springBootRESTfulWebservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "reservations")
public class Reservation
{
    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rsvpId;
    @Column(name = "date_time")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime rsvpDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private Flight flight;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    public Reservation() {}

    public Integer getRsvpId() {
        return rsvpId;
    }

    public void setRsvpId(Integer rsvpId) {
        this.rsvpId = rsvpId;
    }

    public LocalDateTime getRsvpDate() {
        return rsvpDate;
    }

    public void setRsvpDate(LocalDateTime rsvpDate) {
        this.rsvpDate = rsvpDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(getRsvpId(), that.getRsvpId()) &&
                Objects.equals(getRsvpDate(), that.getRsvpDate()) &&
                getStatus() == that.getStatus() &&
                Objects.equals(getFlight(), that.getFlight()) &&
                Objects.equals(getCustomer(), that.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRsvpId(), getRsvpDate(), getStatus(), getFlight().hashCode(), getCustomer().hashCode());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "rsvpId=" + rsvpId +
                ", rsvpDate=" + rsvpDate +
                ", status=" + status.toString() +
                ", flight=" + flight.getFlightCode() +
                ", customer=" + customer.getCustomerId() +
                '}';
    }
}
