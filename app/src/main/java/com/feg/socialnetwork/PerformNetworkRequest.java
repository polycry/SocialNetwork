package com.feg.socialnetwork;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

    private String url;
    private HashMap<String, String> params;
    private int requestCode;

    // declare here other activities
    Activity activity;
    FeedFragment feed_fragment;

    public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode, Activity activity) {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
        this.activity = activity;
    }

    public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode, FeedFragment activity) {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
        this.feed_fragment = (FeedFragment) activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject object = new JSONObject(s);
            if (url.equals(API.URL_LOGIN)) {
                ((MainActivity) activity).login(object);
            } else if (url.equals(API.URL_GETPOSTS) || url.equals(API.URL_GETPOSTS_ONLY_USER)) {
                feed_fragment.refreshFeed(object);
            } else if (url.equals(API.URL_REGISTER)) {
                ((RegisterActivity) activity).register(object);
            } else if (url.equals(API.URL_INSERTPOST)){
                ((AddPostActivity) activity).addpost(object);
            } else if (url.equals(API.URL_FOLLOW)) {
                ((UserFeedFragment) feed_fragment).follow(object);
            } else if (url.equals(API.URL_IS_FOLLOWING)) {
                ((UserFeedFragment) feed_fragment).reloadButton(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        if (requestCode == API.CODE_POST_REQUEST)
            return requestHandler.sendPostRequest(url, params);


        if (requestCode == API.CODE_GET_REQUEST)
            return requestHandler.sendGetRequest(url);

        return null;
    }
}
