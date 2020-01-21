package com.hamidur.springBootRESTfulWebservices.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Airport
{
    @Id
    @Column(name = "airport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airportId;
    @Column(name = "airport_name")
    private String airportName;
}
