package com.example.deepakdantla.booklistingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {


    private Context context;

    //constructor
    public BookAdapter(Activity context, ArrayList<Book> books)
    {
        super(context, 0, books);
    }

    //overriden getView method of ArrayAdapter<Book>
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list, parent, false);
        }
        //getting current object of the Book class
        Book currentBook = getItem(position);
        TextView titleView = (TextView) listItemView.findViewById(R.id.list_title);
        TextView subTitleView = (TextView) listItemView.findViewById(R.id.list_subtitle);
        titleView.setText(currentBook.getmTitle());
        subTitleView.setText(currentBook.getmAuthor());


        return listItemView;

    }


}

