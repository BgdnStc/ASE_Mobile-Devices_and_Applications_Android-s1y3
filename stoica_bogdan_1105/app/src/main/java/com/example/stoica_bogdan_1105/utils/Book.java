package com.example.stoica_bogdan_1105.utils;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private int bookReleaseYear;

    public Book() {
    }

    public Book(String bookTitle, String bookAuthor, int bookReleaseYear) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookReleaseYear = bookReleaseYear;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookReleaseYear() {
        return bookReleaseYear;
    }

    public void setBookReleaseYear(int bookReleaseYear) {
        this.bookReleaseYear = bookReleaseYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookReleaseYear=" + bookReleaseYear +
                '}';
    }
}
