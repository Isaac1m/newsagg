package com.times.timesmobile.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Bwighane on 1/8/2018.
 */

@IgnoreExtraProperties
public class NewsItem {
    private String title;
    private String description;
    private String timeAgo;
    private String author;

    public NewsItem() {
    }

    public NewsItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return title;
    }
}
