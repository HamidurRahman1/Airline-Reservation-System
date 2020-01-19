package com.hamidur.RESTfulSpringBootMicroservice.services;

import com.hamidur.RESTfulSpringBootMicroservice.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService
{
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(final ReservationRepository reservationRepository)
    {
        this.reservationRepository = reservationRepository;
    }
}
