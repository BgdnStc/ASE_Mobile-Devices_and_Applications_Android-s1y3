package com.example.xml_test_practice.network;

import com.example.xml_test_practice.utils.Movie;
import com.example.xml_test_practice.utils.XMLParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpConnection {
    private List<Movie> movies = new ArrayList<>();
    private final String urlString;
    private HttpURLConnection httpURLConnection;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public HttpConnection(String url) {
        this.urlString = url;
    }

    public List<Movie> getXmlContent() throws IOException {
        try {
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = httpURLConnection.getInputStream();
            XMLParser xmlParser = new XMLParser();
            movies = xmlParser.parse(inputStream);
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    private void openConnection() throws IOException {
        URL url = new URL(urlString);
        httpURLConnection = (HttpURLConnection) url.openConnection();
        inputStream = httpURLConnection.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    private void closeConnection() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
    }
}
