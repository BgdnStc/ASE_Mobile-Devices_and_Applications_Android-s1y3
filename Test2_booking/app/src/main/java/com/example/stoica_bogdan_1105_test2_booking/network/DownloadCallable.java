package com.example.stoica_bogdan_1105_test2_booking.network;

import java.util.concurrent.Callable;

public class DownloadCallable implements Callable<String> {
    private final HttpConnection httpConnection;

    public DownloadCallable(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    @Override
    public String call() throws Exception {
        return httpConnection.readFromHttp();
    }
}
