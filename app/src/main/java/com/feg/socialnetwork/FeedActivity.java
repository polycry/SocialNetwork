package com.feg.socialnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class FeedActivity extends AppCompatActivity {

    private PostAdapter pa;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        lv = findViewById(R.id.list_feed);
        ArrayList<Post> posts = new ArrayList<Post>();
        pa = new PostAdapter(this, posts);
        lv.setAdapter(pa);


        TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup();


        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Tab One");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tab Two");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab Three");
        host.addTab(spec);

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
                arr = new JSONArray("posts");
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
