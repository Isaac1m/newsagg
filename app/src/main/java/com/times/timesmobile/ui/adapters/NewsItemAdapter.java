package com.times.timesmobile.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.times.timesmobile.R;
import com.times.timesmobile.models.NewsItem;

import java.util.List;

/**
 * Created by Bwighane on 1/8/2018.
 */

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsItemViewHolder> {
    private Context context;
    private List<NewsItem> newsItems;

    public static class NewsItemViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.news_item_title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.news_item_description);
        }
    }

    public NewsItemAdapter(Context context, List<NewsItem> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View newsItemView = inflater.inflate(R.layout.news_item, parent, false);
        NewsItemViewHolder newsItemViewHolder = new NewsItemViewHolder(newsItemView);
        return newsItemViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsItemViewHolder holder, int position) {
        NewsItem newsItem = newsItems.get(position);
        TextView titleTextView = holder.titleTextView;
        titleTextView.setText(newsItem.getTitle());
        TextView descriptionTextView = holder.descriptionTextView;
        descriptionTextView.setText(newsItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

}
