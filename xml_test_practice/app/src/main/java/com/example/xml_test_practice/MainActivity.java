package com.example.xml_test_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xml_test_practice.network.HttpConnection;
import com.example.xml_test_practice.network.XMLCallableTask;
import com.example.xml_test_practice.utils.Movie;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    ListView lvMovies;
    String urlXML = "https://pastebin.com/raw/BUXXtTfx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        HttpConnection httpConnection = new HttpConnection(urlXML);
        XMLCallableTask xmlCallableTask = new XMLCallableTask(httpConnection);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<List<Movie>> submit = executorService.submit(xmlCallableTask);
        try {
            List<Movie> movies = submit.get();
            ArrayAdapter<Movie> arrayAdapter = new ArrayAdapter<>(getApplication(), R.layout.lv_movies_row, R.id.tvArrayMovie, movies);
            lvMovies.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initComponents() {
        lvMovies = findViewById(R.id.lvMovies);
    }
}