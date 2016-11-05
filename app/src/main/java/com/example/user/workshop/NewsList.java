package com.example.user.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsList extends AppCompatActivity {

    private ListView lt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        findViewById(R.id.lt);

        lt = (ListView) findViewById(R.id.lt);

        String[] values = new String[] {"Topic News","Date"};




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,  //Context of this activity
                android.R.layout.simple_list_item_1,  //คือชื่อ layout ที่จะแสดง
                values
        );



        lt.setAdapter(adapter);

    }
}
