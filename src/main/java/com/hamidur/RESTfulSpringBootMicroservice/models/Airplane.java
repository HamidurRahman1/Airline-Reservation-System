package com.hamidur.RESTfulSpringBootMicroservice.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "airplanes")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Airplane
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
}
