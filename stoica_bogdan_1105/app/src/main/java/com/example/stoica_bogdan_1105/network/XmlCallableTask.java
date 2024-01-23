package com.example.stoica_bogdan_1105.network;

import com.example.stoica_bogdan_1105.utils.Book;

import java.util.List;
import java.util.concurrent.Callable;

public class XmlCallableTask implements Callable {
    private final HttpConnection httpConnection;

    public XmlCallableTask(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    @Override
    public List<Book> call() throws Exception {
        return httpConnection.getXmlContent();
    }
}
