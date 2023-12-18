package com.example.stoica_bogdan_1105_test2_booking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.stoica_bogdan_1105_test2_booking.database.BookingDB;
import com.example.stoica_bogdan_1105_test2_booking.utils.Booking;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {
    ListView lvDatabase;
    Button btnSort;
    List<Booking> bookings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        initComponents();
        loadMovieList();

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingDB bookingDB = BookingDB.getInstance(getApplicationContext());
                List<Booking> bookingsAbove450 = bookingDB.getBookingDao().getBookingAbove450();
                ArrayAdapter<Booking> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.adapter_database, R.id.tvBooking, bookingsAbove450);
                lvDatabase.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initComponents() {
        lvDatabase = findViewById(R.id.lvDatabase);
        btnSort = findViewById(R.id.btnSort);
    }

    private void loadMovieList() {
        BookingDB bookingDB = BookingDB.getInstance(getApplicationContext());
        List<Booking> bookingsDB = bookingDB.getBookingDao().getAllBookings();
        bookings.addAll(bookingsDB);
        ArrayAdapter<Booking> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.adapter_database, R.id.tvBooking, bookings);
        lvDatabase.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
}