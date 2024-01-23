package com.example.stoica_bogdan_1105.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    private final List<Book> books = new ArrayList<>();
    private Book book;
    private String text;

    public List<Book> parse(InputStream is) {

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase(Constants.KEY_BOOK)) {
                            book = new Book();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase(Constants.KEY_BOOK)) {
                            books.add(book);
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_BOOK_TITLE)) {
                            book.setBookTitle(text);
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_BOOK_AUTHOR)) {
                            book.setBookAuthor(text);
                        } else if (tagName.equalsIgnoreCase(Constants.KEY_RELEASE_YEAR)) {
                            book.setBookReleaseYear(Integer.parseInt(text.trim()));
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
        return books;
    }
}
