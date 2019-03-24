package com.example.deepakdantla.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    String mURL;

    //constructor
    public BookLoader(Context context, String url) {
        super(context);
        mURL = url;
    }

    //it calls the loadInBackgorund() method
    protected void onStartLoading() {
        forceLoad();
    }

    //performs the action on background thread
    public List<Book> loadInBackground() {
        if (mURL == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Book> books = QueryUtils.fetchBookData(mURL);
        return books;
    }


}

