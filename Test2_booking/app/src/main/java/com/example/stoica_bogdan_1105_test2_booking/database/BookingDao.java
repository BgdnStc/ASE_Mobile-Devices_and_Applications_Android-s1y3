package com.example.stoica_bogdan_1105_test2_booking.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.stoica_bogdan_1105_test2_booking.utils.Booking;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert
    void insert(Booking booking);
    @Query("SELECT * FROM booking")
    List<Booking> getAllBookings();
    @Query("SELECT * FROM booking WHERE payedSum > 450")
    List<Booking> getBookingAbove450();
}
