package com.feg.socialnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class FeedActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private SectionsPageAdapter spa;
    private ListView lv;
    private SearchView sv;

    private String logged_in_user = "";

    private FeedFragment followFeed;
    private FeedFragment globalFeed;
    private FeedFragment userFeed;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private FloatingActionButton addPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        //lv = findViewById(R.id.list_feed);
        //ArrayList<Post> posts = new ArrayList<Post>();
        //pa = new PostAdapter(this, posts);
        //lv.setAdapter(pa);

        sv = findViewById(R.id.search_user);
        sv.setOnQueryTextListener(this);

        addPost = findViewById(R.id.addPost);
        addPost.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            logged_in_user = b.getString("username");
        }

        spa = new SectionsPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        switch (tab.getPosition()) {
                            case 0:
                                followFeed.reloadListView(logged_in_user);
                                break;
                            case 1:
                                globalFeed.reloadListView("");
                                break;
                            case 2:
                                userFeed.reloadListView(sv.getQuery().toString());
                                break;
                        }
                    }
                });
        /*viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(onTabSelectedListener(viewPager));*/

        /*HashMap<String, String> params = new HashMap<>();
        params.put("username", "poly");
        PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_GETPOSTS_ONLY_USER, params, API.CODE_POST_REQUEST, this);
        nr.execute();*/
    }

    private void setupViewPager(ViewPager viewPager) {
        followFeed = new FeedFragment();
        followFeed.setTag(FeedFragment.FOLLOW_FEED);

        globalFeed = new FeedFragment();
        globalFeed.setTag(FeedFragment.GLOBAL_FEED);

        userFeed = new FeedFragment();
        userFeed.setTag(FeedFragment.USER_FEED);

        spa.addFragment(followFeed, "Follower");
        spa.addFragment(globalFeed, "Global");
        spa.addFragment(userFeed, "User");
        viewPager.setAdapter(spa);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        if (tabLayout.getSelectedTabPosition() == 2) {
            userFeed.reloadListView(query);
        } else {
            tabLayout.getTabAt(2).select();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == addPost) {
            Intent i = new Intent(getBaseContext(), AddPostActivity.class);
            i.putExtra("username",logged_in_user);
            startActivity(i);
        }
    }
}
