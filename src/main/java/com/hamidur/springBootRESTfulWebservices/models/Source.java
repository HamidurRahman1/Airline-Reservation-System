package com.hamidur.springBootRESTfulWebservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "sources")
public class Source
{
    @Id
    @Column(name = "source_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sourceId;

    @Column(name = "date_time")
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime departureDateTime;

    @JoinColumn(name = "airport_id", referencedColumnName = "airport_id")
    @OneToOne
    private Airport airport;

    public Source() {}

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
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
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return Objects.equals(getSourceId(), source.getSourceId()) &&
                Objects.equals(getDepartureDateTime(), source.getDepartureDateTime()) &&
                Objects.equals(getAirport().getAirportId(), source.getAirport().getAirportId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSourceId(), getDepartureDateTime(), getAirport().getAirportId());
    }

    @Override
    public String toString() {
        return "Source{" +
                "sourceId=" + sourceId +
                ", departureDateTime=" + departureDateTime +
                ", airport=" + airport.getAirportId() +
                '}';
    }
}
