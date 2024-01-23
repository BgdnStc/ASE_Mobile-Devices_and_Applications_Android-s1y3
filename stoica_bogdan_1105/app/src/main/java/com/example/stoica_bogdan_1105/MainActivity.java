package com.example.stoica_bogdan_1105;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.stoica_bogdan_1105.network.HttpConnection;
import com.example.stoica_bogdan_1105.network.XmlCallableTask;
import com.example.stoica_bogdan_1105.utils.Book;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {
    private ListView lvBooks;
    private static final String url = "https://pastebin.com/raw/H3AQjA3T";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        try {
            HttpConnection httpConnection = new HttpConnection(url);
            XmlCallableTask xmlCallableTask = new XmlCallableTask(httpConnection);
            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<List<Book>> future = executorService.submit(xmlCallableTask);
            List<Book> books = future.get();
            ArrayAdapter<Book> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.lv_book_row, R.id.tvBook, books);
            lvBooks.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void initComponents() {
        lvBooks = findViewById(R.id.lvBooks);
    }
}