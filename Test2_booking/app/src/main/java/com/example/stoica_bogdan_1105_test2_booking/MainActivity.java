package com.example.stoica_bogdan_1105_test2_booking;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.stoica_bogdan_1105_test2_booking.database.BookingDB;
import com.example.stoica_bogdan_1105_test2_booking.network.DownloadCallable;
import com.example.stoica_bogdan_1105_test2_booking.network.HttpConnection;
import com.example.stoica_bogdan_1105_test2_booking.parser.JsonParser;
import com.example.stoica_bogdan_1105_test2_booking.utils.Booking;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnSync, btnView;
    List<Booking> bookings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < bookings.size(); i++) {
                    insertBookingInDB(bookings.get(i));
                }
            }
        });

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = "https://pastebin.com/raw/9ck6tNRU";
                    HttpConnection httpConnection = new HttpConnection(url);
                    DownloadCallable downloadCallable = new DownloadCallable(httpConnection);
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    Future<String> submit = executorService.submit(downloadCallable);
                    String result = submit.get();
                    JsonParser jsonParser = new JsonParser();
                    bookings = jsonParser.getBookingsJson(result);
                    if (bookings != null) {
                        Toast.makeText(getApplicationContext(), R.string.jsonSuccessToast, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.jsonErrorToast, Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentView = new Intent(getApplicationContext(), DatabaseActivity.class);
                startActivity(intentView);
            }
        });
    }

    private void initComponents() {
        btnSave = findViewById(R.id.btnSave);
        btnSync = findViewById(R.id.btnSync);
        btnView = findViewById(R.id.btnView);
    }

    private void insertBookingInDB(Booking booking) {
        BookingDB bookingDB = BookingDB.getInstance(getApplicationContext());
        Random random = new SecureRandom();
        Booking booking1 = new Booking(random.nextInt(), booking.getCustomerCode(), booking.getStartDate(), booking.getPayingMethod(), booking.getPayedSum());
        bookingDB.getBookingDao().insert(booking1);
    }
}