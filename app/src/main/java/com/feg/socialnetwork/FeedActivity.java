package com.feg.socialnetwork;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewParent;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {

    private PostAdapter pa;
    private SectionsPageAdapter spa;
    private ListView lv;

    private FeedFragment followFeed;
    private FeedFragment globalFeed;
    private FeedFragment userFeed;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        //lv = findViewById(R.id.list_feed);
        //ArrayList<Post> posts = new ArrayList<Post>();
        //pa = new PostAdapter(this, posts);
        //lv.setAdapter(pa);

        spa = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*HashMap<String, String> params = new HashMap<>();
        params.put("username", "poly");
        PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_GETPOSTS_ONLY_USER, params, API.CODE_POST_REQUEST, this);
        nr.execute();*/
    }

    private void setupViewPager(ViewPager viewPager) {
        followFeed = new FeedFragment();
        globalFeed = new FeedFragment();
        userFeed = new FeedFragment();
        spa.addFragment(followFeed, "Follower");
        spa.addFragment(globalFeed, "Global");
        spa.addFragment(userFeed, "User");
        viewPager.setAdapter(spa);
    }

    public void refreshFeed(JSONObject jo) {
        int errorcode = -1;
        ArrayList<Post> posts = new ArrayList();
        try {
            errorcode = jo.getInt("errorcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorcode == 0) {
            JSONArray arr = null;
            try {
                arr = jo.getJSONArray("posts");
                for (int i = 0; i < arr.length(); i++) {
                    posts.add(Post.formPost(arr.getJSONObject(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        pa.clear();
        for (Post p : posts) {
            pa.add(p);
        }
        pa.notifyDataSetChanged();
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
