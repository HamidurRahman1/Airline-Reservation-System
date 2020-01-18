package com.hamidur.RESTfulSpringBootMicroservice.utils;

import com.hamidur.RESTfulSpringBootMicroservice.errors.InvalidRequestException;
import org.springframework.http.HttpStatus;

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
        if(json.size() != 3) throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request must have 3 values, found="+json.size());

        if(json.getOrDefault(CUSTOMER_ID_JKEY, -1).equals(-1))
        {
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST.value(), "request body missing key="+CUSTOMER_ID_JKEY);
        }
        if(json.getOrDefault("flight_id", -1).equals(-1))
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

    public static void main(String[] args) {
        System.out.println(toViewDateTime(LocalDateTime.now()));
    }
}
