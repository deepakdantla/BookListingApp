package com.example.deepakdantla.booklistingapp;

public class Book {
    private String mTitle;
    private String mAuthor;

    Book(String mTitle, String mAuthor) {
        this.mAuthor = mAuthor;
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }
}
