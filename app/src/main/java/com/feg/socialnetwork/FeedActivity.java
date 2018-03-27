package com.feg.socialnetwork;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {

    private SectionsPageAdapter spa;
    private ListView lv;

    private String logged_in_user = "";

    private FeedFragment followFeed;
    private FeedFragment globalFeed;
    private FeedFragment userFeed;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        //lv = findViewById(R.id.list_feed);
        //ArrayList<Post> posts = new ArrayList<Post>();
        //pa = new PostAdapter(this, posts);
        //lv.setAdapter(pa);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            logged_in_user = b.getString("username");
        }

        spa = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        /*viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(onTabSelectedListener(viewPager));*/

        /*HashMap<String, String> params = new HashMap<>();
        params.put("username", "poly");
        PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_GETPOSTS_ONLY_USER, params, API.CODE_POST_REQUEST, this);
        nr.execute();*/
    }

    /*private TabLayout.OnTabSelectedListener onTabSelectedListener(final ViewPager viewPager) {

        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment f = spa.getItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }*/

    private void setupViewPager(ViewPager viewPager) {
        followFeed = new FeedFragment();
        followFeed.setTag(FeedFragment.FOLLOW_FEED);
        followFeed.setUser(logged_in_user);

        globalFeed = new FeedFragment();
        globalFeed.setTag(FeedFragment.GLOBAL_FEED);
        globalFeed.setUser(logged_in_user);

        userFeed = new FeedFragment();
        userFeed.setTag(FeedFragment.USER_FEED);
        userFeed.setUser(logged_in_user);

        spa.addFragment(followFeed, "Follower");
        spa.addFragment(globalFeed, "Global");
        spa.addFragment(userFeed, "User");
        viewPager.setAdapter(spa);
    }



}

/*
    <ListView
            android:id="@+id/list_feed"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="0dp"
            android:layout_marginBottom="8dp"
            android:layout_marginEnd="8dp"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            android:scrollbars="none"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintHorizontal_bias="0.0"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintVertical_bias="0.09" />*/
