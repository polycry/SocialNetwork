package com.feg.socialnetwork;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Felix on 25.03.2018.
 */

public class FeedFragment extends Fragment {


    public static final int FOLLOW_FEED = 1;
    public static final int GLOBAL_FEED = 2;
    public static final int USER_FEED = 3;

    protected int tag = 0;

    protected ListView lv;
    protected PostAdapter pa;
    protected Activity a;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        lv = (ListView) view.findViewById(R.id.list_feed);
        a = getActivity();
        ArrayList<Post> posts = new ArrayList<Post>();
        pa = new PostAdapter(a, posts);
        lv.setAdapter(pa);
        return view;
    }

    public void setTag(int tag) {
        this.tag = tag;
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

    public void reloadListView(String query) {
        HashMap<String, String> params = new HashMap<>();
        PerformNetworkRequest nr = null;
        params.put("username", query);
        if (tag == FOLLOW_FEED) {
            nr = new PerformNetworkRequest(API.URL_GETPOSTS, params, API.CODE_POST_REQUEST, this);
        } else if (tag == GLOBAL_FEED) {
            nr = new PerformNetworkRequest(API.URL_GETPOSTS, params, API.CODE_POST_REQUEST, this);
        } /*else if (tag == USER_FEED) {
            nr = new PerformNetworkRequest(API.URL_GETPOSTS_ONLY_USER, params, API.CODE_POST_REQUEST, this);
        }*/
        nr.execute();
    }


}
