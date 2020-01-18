package com.hamidur.RESTfulSpringBootMicroservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util
{
    private static final String JSON_PATTERN = "MM-dd-yyyy HH:mm:ss";
    private static final String DB_TO_VIEW = "MM-dd-yyyy HH:mm:ss";

    //custom keys for json
    public static final String RSVP_ID_JKEY = "reservation_id";
    public static final String FLIGHT_ID_JKEY = "flight_id";
    public static final String CUSTOMER_ID_JKEY = "customer_id";
    public static final String DATE_TIME_JKEY = "date_time";

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
