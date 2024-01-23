package com.example.stoica_bogdan_1105.network;

import com.example.stoica_bogdan_1105.utils.Book;
import com.example.stoica_bogdan_1105.utils.XmlParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpConnection {
    private List<Book> books = new ArrayList<>();
    private final String urlString;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;

    public HttpConnection(String url) {
        this.urlString = url;
    }

    public List<Book> getXmlContent() throws IOException {
        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = httpURLConnection.getInputStream();
            XmlParser xmlParser = new XmlParser();
            books = xmlParser.parse(inputStream);
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
}
