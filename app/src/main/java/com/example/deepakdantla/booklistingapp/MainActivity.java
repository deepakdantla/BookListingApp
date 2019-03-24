package com.example.deepakdantla.booklistingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String bookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void searchBook(View view) {
        EditText editText = (EditText) findViewById(R.id.search_book);
        bookName = editText.getText().toString();

        Intent intent = new Intent(MainActivity.this, BookActivity.class);
        //sending the intent
        intent.putExtra(BookActivity.EXTRA_BOOKNAME, bookName);
        startActivity(intent);

    }
}
