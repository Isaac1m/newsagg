package com.times.timesmobile.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.FirebaseDatabase;
import com.times.timesmobile.R;
import com.times.timesmobile.ui.fragments.AddNewsItemFragment;
import com.times.timesmobile.ui.fragments.FeedFragment;
import com.times.timesmobile.ui.fragments.MediaFragment;
import com.times.timesmobile.ui.fragments.BookmarksFragment;

public class MobileBroadcasterActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Resources resources = MobileBroadcasterActivity.this.getResources();
            switch (item.getItemId()) {
                case R.id.navigation_feed:
                    adapt(resources.getString(R.string.title_feed), FeedFragment.getFeedFragmentInstance());
                    return true;
                case R.id.navigation_media:
                    adapt(resources.getString(R.string.title_media), new MediaFragment());
                    return true;
                case R.id.navigation_bookmarked:
                    adapt(resources.getString(R.string.title_bookmark), new BookmarksFragment());
                    return true;
            }
            return false;
        }

        public void adapt(String title, Fragment fragment){
            MobileBroadcasterActivity.this.setTitle(title);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_container, fragment);
            fragmentTransaction.commit();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.title_feed);
        setContentView(R.layout.activity_times_mobile);

//        initializing Firebase
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_container, FeedFragment.getFeedFragmentInstance());
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_add:
                FragmentManager fragmentManager = getSupportFragmentManager();
                AddNewsItemFragment addNewsItemFragment = new AddNewsItemFragment();
                addNewsItemFragment.show(fragmentManager, "ADD_NEWS_ITEM");
                break;
        }
        return true;
    }
}
