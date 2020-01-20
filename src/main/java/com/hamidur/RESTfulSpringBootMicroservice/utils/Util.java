package com.hamidur.RESTfulSpringBootMicroservice.utils;

import com.hamidur.RESTfulSpringBootMicroservice.errors.InvalidRequestException;
import com.hamidur.RESTfulSpringBootMicroservice.models.Status;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

public class Util
{
    private static final String JSON_PATTERN = "MM-dd-yyyy HH:mm:ss";
    private static final String DB_TO_VIEW = "MM-dd-yyyy HH:mm:ss";

    //custom keys for json
    public static final String RSVP_ID_JKEY = "reservation_id";
    public static final String FLIGHT_ID_JKEY = "flight_id";
    public static final String CUSTOMER_ID_JKEY = "customer_id";
    public static final String DATE_TIME_JKEY = "date_time";

    public static boolean verifyRSVPByCustomerId(final Map<String, Object> json) throws InvalidRequestException
    {
        if(json.size() != 2) throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request must have 2 values, found="+json.size());

        if(json.getOrDefault(CUSTOMER_ID_JKEY, -1).equals(-1))
        {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request body missing key="+CUSTOMER_ID_JKEY);
        }
        if(json.getOrDefault(FLIGHT_ID_JKEY, -1).equals(-1))
        {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request body missing key="+FLIGHT_ID_JKEY);
        }
        return true;
    }

    public static LocalDateTime toViewDateTime(LocalDateTime dbDateTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_TO_VIEW, Locale.US);
        String formatted = formatter.format(dbDateTime);
        return LocalDateTime.parse(formatted, formatter);
    }

    public static LocalDateTime toDBDateTime(LocalDateTime dateTime)
    {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(),
                dateTime.getHour(), dateTime.getMinute(), dateTime.getSecond());
    }

    public static boolean validateAirlineName(String airlineName)
    {
        if(airlineName == null || airlineName.trim().toLowerCase().isEmpty())
            throw new IllegalArgumentException("Invalid input for airline name.");
        return true;
    }

    public static boolean validateAirportName(String airportName)
    {
        if(airportName == null || airportName.trim().toLowerCase().isEmpty())
            throw new IllegalArgumentException("Invalid input for airport name.");
        return true;
    }

    public static boolean validateEmail(String airportName)
    {
        if(airportName == null || airportName.trim().toLowerCase().isEmpty())
            throw new IllegalArgumentException("Invalid input for email.");
        return true;
    }

    public static boolean validateNumber(Integer number)
    {
        if(number >= 0) return true;
        else throw new IllegalArgumentException("Invalid number provided.");
    }

    public static LocalDateTime stringDateToDateTime(String date)
    {
        try
        {
            String[] parts = date.split("-");
            return LocalDateTime.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]), 0, 0, 1);
        }
        catch (Exception ex)
        {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public static Status validateFlightStatus(String status)
    {
        if(status.trim().equalsIgnoreCase(Status.ON_TIME.toString()))
            return Status.ON_TIME;
        else if(status.trim().equalsIgnoreCase(Status.CANCELLED.toString()))
            return Status.CANCELLED;
        else throw new IllegalArgumentException("Invalid status provided. status=ON_TIME or CANCELLED");
    }
}
