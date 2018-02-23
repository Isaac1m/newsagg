package com.times.timesmobile.datasources;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.times.timesmobile.R;
import com.times.timesmobile.models.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bwighane on 1/8/2018.
 */

public class NewsItemDatasource {
    private Context context;
    private DatabaseReference databaseReference;
    private static NewsItemDatasource newsItemDatasource;

    private NewsItemDatasource(Context context, NewsItemsListerner newsItemsListerner){
        this.context = context;
        this.databaseReference = FirebaseDatabase.getInstance().getReference("news_items");
    }

    public static NewsItemDatasource getInstance(Context context, NewsItemsListerner newsItemsListerner){
        if(newsItemDatasource == null){
            newsItemDatasource = new NewsItemDatasource(context, newsItemsListerner);
        }
        return newsItemDatasource;
    }

    public List<NewsItem> all(){
        List<NewsItem> newsItems = new ArrayList<>();
        String[] sample_titles = context.getResources().getStringArray(R.array.sample_titles);
        String[] sample_descriptions = context.getResources().getStringArray(R.array.sample_descriptions);
        for (int x = 0; x < 20; x++){
            NewsItem newsItem = new NewsItem(sample_titles[x], sample_descriptions[x]);
            newsItems.add(newsItem);
        }
        return newsItems;
    }

    public void addNewsItem(NewsItem newsItem){
        String newsItemId = databaseReference.push().getKey();
        databaseReference.child(newsItemId).setValue(newsItem);
    }

    public interface NewsItemsListerner{
        void onNewsItemsReceived(List<NewsItem> newsItems);
    }
}
