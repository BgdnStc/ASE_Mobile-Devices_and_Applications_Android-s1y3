package com.example.xml_test_practice.network;

import com.example.xml_test_practice.utils.Movie;

import java.util.List;
import java.util.concurrent.Callable;

public class XMLCallableTask implements Callable {
    private final HttpConnection httpConnection;

    public XMLCallableTask(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    @Override
    public List<Movie> call() throws Exception {
        return httpConnection.getXmlContent();
    }
}
