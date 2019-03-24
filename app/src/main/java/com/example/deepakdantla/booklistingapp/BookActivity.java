package com.example.deepakdantla.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String EXTRA_BOOKNAME = "default";//some random string value

    private static final int BOOK_LOADER_ID = 1;

    //googleapi url
    private static String BOOK_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=&maxResults=5";

    private BookAdapter mAdapter;//cutom adapter

    private TextView mEmptyView;//empty view in the ListView

    private ImageView nosignal;//View to display when no intenet connection

    private TextView noInternetConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //getting the intenet value from the MainActivity
        String book_Name = (String) getIntent().getExtras().get(EXTRA_BOOKNAME);

        String defURL =
                "https://www.googleapis.com/books/v1/volumes?q=&maxResults=5";
        //creating a StringBuffer class
        StringBuffer sb = new StringBuffer(defURL);
        int index = sb.indexOf("=");
        //changing the url with requested book for desired search output
        sb.insert(index + 1, book_Name);
        BOOK_REQUEST_URL = sb.toString();

       /* BOOK_REQUEST_URL = BOOK_REQUEST_URL.replaceAll(dummy, book_Name);
        Log.i("BookActivity",BOOK_REQUEST_URL);
        dummy=book_Name;*/
        ListView bookListView = (ListView) findViewById(R.id.bookListView);
        nosignal = (ImageView) findViewById(R.id.nosignal);
        nosignal.setVisibility(View.GONE);

        noInternetConnection = (TextView) findViewById(R.id.no_internet_connection);

        mEmptyView = (TextView) findViewById(R.id.empty_view);
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            ProgressBar pbar = (ProgressBar) findViewById(R.id.pbar);
            pbar.setVisibility(View.GONE);
            nosignal.setVisibility(View.VISIBLE);

        }


        mAdapter = new BookAdapter(BookActivity.this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);
        bookListView.setEmptyView(mEmptyView);


    }


    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new BookLoader(this, BOOK_REQUEST_URL);


    }

    public void onLoadFinished(Loader<List<Book>> loader, List<Book> booksObtained) {
        ProgressBar pbar = (ProgressBar) findViewById(R.id.pbar);
        pbar.setVisibility(View.GONE);
        ImageView nosignal = (ImageView) findViewById(R.id.nosignal);
        nosignal.setVisibility(View.GONE);
        noInternetConnection.setVisibility(View.GONE);


        mEmptyView.setText(R.string.no_book_found);

        // Clear the adapter of previous Book data
        mAdapter.clear();

        // If there is a valid list of {@link Book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (booksObtained != null && !booksObtained.isEmpty()) {
            mAdapter.addAll(booksObtained);
        }
    }


    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }


}
