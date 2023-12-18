package com.example.stoica_bogdan_1105_test2_booking.parser;

import android.util.Log;

import com.example.stoica_bogdan_1105_test2_booking.utils.Booking;
import com.example.stoica_bogdan_1105_test2_booking.utils.Constants;
import com.example.stoica_bogdan_1105_test2_booking.utils.DateFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private final List<Booking> bookings = new ArrayList<>();

    public List<Booking> getBookingsJson(String json) {
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray(Constants.KEY_JSON_ARRAY);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonBooking = jsonArray.getJSONObject(i);
                    long customerCode = jsonBooking.getLong(Constants.KEY_JSON_C_CODE);
                    String startDate = jsonBooking.getString(Constants.KEY_START_DATE);
                    String payingMethod = jsonBooking.getString(Constants.KEY_PAY_METHOD);
                    int payedSum = jsonBooking.getInt(Constants.KEY_PAY_SUM);
                    Booking booking = new Booking(customerCode, DateFormatter.fromString(startDate), payingMethod, payedSum);
                    bookings.add(booking);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("parseJson", "JSON object is null");
        }
        return bookings;
    }
}
