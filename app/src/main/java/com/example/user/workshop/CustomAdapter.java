package com.example.user.workshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by user on 11/7/2016.
 */
public class CustomAdapter extends BaseAdapter {

    Context mContext;
    String [] TopicNews;
    String [] TopicDate;
    int[] imgID;

    public CustomAdapter(Context mContext, String[] topicNews, String[] topicDate, int[] imgID) {
        this.mContext = mContext;
        TopicNews = topicNews;
        TopicDate = topicDate;
        this.imgID = imgID;
    }

    @Override
    public int getCount() {
        return TopicNews.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder myHolder = null;

        if (convertView == null) {
            //คื่อช่วงแรกๆที่ convertView ยังไม่มีค่า

            //เตรียม Layout ที่ต้องการ ให้กับ convertView
            convertView = mInflater.inflate(R.layout.activity_news__style, null);

            myHolder = new ViewHolder();
            myHolder.tvTopicNews = (TextView)convertView.findViewById(R.id.TopicNews);
            myHolder.tvTopicDate = (TextView)convertView.findViewById(R.id.TopicDate);
            myHolder.imgID = (ImageView)convertView.findViewById(R.id.imgID);

            convertView.setTag(myHolder);

        } else {
            //คือช่วงที่ convertView ผ่านการ Scroll มาแล้ว

            myHolder = (ViewHolder)convertView.getTag();
        }
        // ต้อง set ค่าจะแสดงผลกับแต่ละ widget
        myHolder.tvTopicNews.setText(TopicNews[position]);
        myHolder.tvTopicDate.setText(TopicDate[position]);
        myHolder.imgID.setImageResource(imgID[position]);

        return convertView;
    }
    public class ViewHolder {
        TextView tvTopicNews;
        TextView tvTopicDate;
        ImageView imgID;
    }
}

