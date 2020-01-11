package com.hamidur.RESTfulSpringBootMicroservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util
{
    private static final String JSON_PATTERN = "MM-dd-yyyy HH:mm:ss";
    private static final String DB_TO_VIEW = "MM-dd-yyyy HH:mm:ss";

    public static LocalDateTime toViewDateTime(LocalDateTime dbDateTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DB_TO_VIEW, Locale.US);
        String formatted = formatter.format(dbDateTime);
        return LocalDateTime.parse(formatted, formatter);
    }

    public static void main(String[] args) {
        System.out.println(toViewDateTime(LocalDateTime.now()));
    }
}
