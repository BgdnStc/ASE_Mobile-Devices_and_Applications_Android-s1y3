package com.example.stoica_bogdan_1105.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static Date fromString(String dateString) {
        try {
            return dateString != null ? formatter.parse(dateString) : null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String fromDate(Date date) {
        return date != null ? formatter.format(date) : null;
    }
}
