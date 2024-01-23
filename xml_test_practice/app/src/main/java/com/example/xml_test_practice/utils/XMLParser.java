package com.example.xml_test_practice.utils;

import static com.example.xml_test_practice.utils.Constants.KEY_MOVIE_MOVIE_GENRE;
import static com.example.xml_test_practice.utils.Constants.KEY_MOVIE_PLATFORM;
import static com.example.xml_test_practice.utils.Constants.KEY_MOVIE_PROFIT;
import static com.example.xml_test_practice.utils.Constants.KEY_MOVIE_RELEASE_DATE;
import static com.example.xml_test_practice.utils.Constants.KEY_MOVIE_TITLE;
import static com.example.xml_test_practice.utils.Constants.KEY_NODE_XML;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private final List<Movie> movies = new ArrayList<>();
    private Movie movie;
    private String text;

    public List<Movie> parse(InputStream is) {

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase(KEY_NODE_XML)) {
                            movie = new Movie();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase(KEY_NODE_XML)) {
                            //the movie is added to the list
                            movies.add(movie);
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_TITLE)) {
                            movie.setTitle(text);
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_RELEASE_DATE)) {
                            movie.setReleaseDate(DateConverter.fromString(text));
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_PROFIT)) {
                            movie.setProfit(Integer.parseInt(text));
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_MOVIE_GENRE)) {
                            movie.setMovieGenre(text);
                        } else if (tagName.equalsIgnoreCase(KEY_MOVIE_PLATFORM)) {
                            movie.setPlatform(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
