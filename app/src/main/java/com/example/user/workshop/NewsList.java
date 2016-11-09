package com.example.user.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    private void customAdap() {
        //lvMenu.setAdapter(new CustomAdapter(getApplicationContext()));
        lvNews.setAdapter(new CustomAdapter(getApplicationContext(),topicNews,topicDate,imgID));
    }
}
