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
    MainActivity main_activity;
    FeedActivity feed_activity;


    public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode, Activity activity) {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
        if (url.equals(API.URL_LOGIN)) {
            this.main_activity = (MainActivity) activity;
        } else if (url.equals(API.URL_GETPOSTS) || url.equals(API.URL_GETPOSTS_ONLY_USER)) {
            this.feed_activity = (FeedActivity) activity;
        }
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
                main_activity.login(object);
            } else if (url.equals(API.URL_GETPOSTS) || url.equals(API.URL_GETPOSTS_ONLY_USER)) {
                feed_activity.refreshFeed(object);
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
