package com.example.user.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsList extends AppCompatActivity {

    private ListView lvNews;
    static String[] topicNews = new String[] {"Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News", "Topic News"};
    static String[] topicDate = new String[] {"5 nov 2016", "5 nov 2016", "5 nov 2016", "5 nov 2016", "5 nov 2016", "5 nov 2016", "5 nov 2016", "5 nov 2016"};
    static int[] imgID = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        lvNews = (ListView) findViewById(R.id.lvNews);
        customAdap();

        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(NewsList.this,NewsDetail.class);
                i.putExtra("listID", (int)id);
                startActivity(i);
            }
        });

    }

    private void customAdap() {
        //lvMenu.setAdapter(new CustomAdapter(getApplicationContext()));
        lvNews.setAdapter(new CustomAdapter(getApplicationContext(),topicNews,topicDate,imgID));
    }
}
