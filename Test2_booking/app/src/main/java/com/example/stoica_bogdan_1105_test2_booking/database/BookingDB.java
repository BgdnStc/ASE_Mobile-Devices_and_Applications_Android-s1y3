package com.example.stoica_bogdan_1105_test2_booking.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.stoica_bogdan_1105_test2_booking.utils.Booking;
import com.example.stoica_bogdan_1105_test2_booking.utils.DateFormatter;

@Database(entities = {Booking.class}, version = 1, exportSchema = false)
@TypeConverters({DateFormatter.class})
public abstract class BookingDB extends RoomDatabase {
    public static final String DB_NAME = "booking.db";
    public static BookingDB INSTANCE;

    public static BookingDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BookingDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, BookingDB.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract BookingDao getBookingDao();
}
