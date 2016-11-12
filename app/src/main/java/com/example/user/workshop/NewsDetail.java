package com.example.user.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsDetail extends AppCompatActivity {

    private int listID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent i = getIntent();
        listID = i.getIntExtra("listID", 0);
    }
}
