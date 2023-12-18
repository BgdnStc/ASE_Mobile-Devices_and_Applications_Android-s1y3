package com.example.stoica_bogdan_1105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stoica_bogdan_1105.network.DownloadCallable;
import com.example.stoica_bogdan_1105.network.HttpConnection;
import com.example.stoica_bogdan_1105.parser.JsonParser;
import com.example.stoica_bogdan_1105.utils.Vacancy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    ListView stoica_bogdan_1105listViewJson;
    List<Vacancy> vacancies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        String url = "https://pastebin.com/raw/W4UgDu5C";
        HttpConnection httpConnection = new HttpConnection(url);
        DownloadCallable downloadCallable = new DownloadCallable(httpConnection);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(downloadCallable);
        String result;
        try {
            result = submit.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JsonParser jsonParser = new JsonParser();
        vacancies = jsonParser.getBookingsJson(result);
        ArrayAdapter<Vacancy> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.adapter_vacancies, R.id.stoica_bogdan_1105textView, vacancies);
        stoica_bogdan_1105listViewJson.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    private void initComponents() {
        this.stoica_bogdan_1105listViewJson = findViewById(R.id.stoica_bogdan_1105listViewJson);
    }
}