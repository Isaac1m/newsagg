package com.times.timesmobile.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.times.timesmobile.R;
import com.times.timesmobile.datasources.NewsItemDatasource;
import com.times.timesmobile.models.NewsItem;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewsItemFragment extends DialogFragment implements NewsItemDatasource.NewsItemsListerner {
    private static final String TAG = "ADD_NEWS_ITEM";
    private TextView titleTv;
    private TextView descriptionTv;
    private TextView authorTv;
    private NewsItemDatasource newsItemDatasource;

    public AddNewsItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsItemDatasource = NewsItemDatasource.getInstance(getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_news_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle("News Item");

        titleTv = (TextView) view.findViewById(R.id.news_item_add_title);
        descriptionTv = (TextView) view.findViewById(R.id.news_item_add_description);
        authorTv = (TextView) view.findViewById(R.id.news_item_add_author);
        Button publishButton = (Button) view.findViewById(R.id.publish_news_item);
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsItem newsItem = new NewsItem(titleTv.getText().toString(), descriptionTv.getText().toString());
                newsItem.setAuthor(authorTv.getText().toString());
                newsItemDatasource.addNewsItem(newsItem);
                AddNewsItemFragment.this.dismiss();
            }
        });
    }

    @Override
    public void onNewsItemsReceived(List<NewsItem> newsItems) {

    }
}
