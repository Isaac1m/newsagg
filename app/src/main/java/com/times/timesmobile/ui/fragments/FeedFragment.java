package com.times.timesmobile.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.times.timesmobile.R;
import com.times.timesmobile.datasources.NewsItemDatasource;
import com.times.timesmobile.models.NewsItem;
import com.times.timesmobile.ui.adapters.NewsItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements NewsItemDatasource.NewsItemsListerner {

    private List<NewsItem> newsItems;
    private static FeedFragment feedFragmentInstance;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment getFeedFragmentInstance(){
        if(feedFragmentInstance == null){
            feedFragmentInstance = new FeedFragment();
        }
        return feedFragmentInstance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView newsItemsRecyclerView = (RecyclerView) view.findViewById(R.id.news_items_list);
        NewsItemDatasource newsItemDatasource = NewsItemDatasource.getInstance(getContext(), this);
        newsItems = newsItemDatasource.all();
        NewsItemAdapter newsItemAdapter = new NewsItemAdapter(getActivity(), newsItems);
        newsItemsRecyclerView.setAdapter(newsItemAdapter);
        newsItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(newsItemsRecyclerView);
    }

    @Override
    public void onNewsItemsReceived(List<NewsItem> newsItems) {

    }
}
