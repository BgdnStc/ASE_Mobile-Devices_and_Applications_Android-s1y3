package com.example.xml_test_practice.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static Date fromString(String dateString) {
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
