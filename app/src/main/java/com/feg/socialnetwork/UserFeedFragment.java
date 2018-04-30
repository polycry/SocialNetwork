package com.feg.socialnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simon Premstaller on 04.04.2018.
 */

public class UserFeedFragment extends FeedFragment implements View.OnClickListener {

    private Button b;
    private String query;
    private String logged_in = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_feed_fragment, container, false);
        lv = (ListView) view.findViewById(R.id.list_feed);
        b = (Button) view.findViewById(R.id.button_follow);
        b.setOnClickListener(this);
        b.setEnabled(false);
        a = getActivity();
        ArrayList<Post> posts = new ArrayList<Post>();
        pa = new PostAdapter(a, posts);
        lv.setAdapter(pa);
        return view;
    }

    public void setLogged_in(String logged_in) {
        this.logged_in = logged_in;
    }

    @Override
    public void refreshFeed(JSONObject jo) {
        int errorcode = -1;
        ArrayList<Post> posts = new ArrayList();
        try {
            errorcode = jo.getInt("errorcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorcode == 0) {
            Boolean reg = false;
            try {
                reg = jo.getBoolean("registered");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (reg) {
                try {
                    JSONArray arr = jo.getJSONArray("posts");
                    for (int i = 0; i < arr.length(); i++) {
                        posts.add(Post.formPost(arr.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Benutzer \"" + query + "\" existiert nicht", Toast.LENGTH_LONG).show();
            }
            pa.clear();
            for (Post p : posts) {
                pa.add(p);
            }
            pa.notifyDataSetChanged();
        }
    }

    public void reloadAll(String query, String user) {
        this.query = query;
        if (query != "") {
            HashMap<String, String> params;
            PerformNetworkRequest nr = null;
            // TODO: GET THIS SHIT TO WORK AMK DCDJASKLJFDKLSAJFD KLÖSAJF ÖDKLASJFKLÖ DASJKLÖ FDASJKLÖFÄDASKÄÖ!PIO"U§$"!$I KEÄÖQWL JKFÖQWELJ
            if (query != user) {
                showButton();
                params = new HashMap<>();
                params.put("username", user);
                params.put("target", query);
                nr = new PerformNetworkRequest(API.URL_IS_FOLLOWING, params, API.CODE_POST_REQUEST, this);
                nr.execute();
            } else {
                hideButton();
            }
            params = new HashMap<>();
            params.put("username", query);
            nr = new PerformNetworkRequest(API.URL_GETPOSTS_ONLY_USER, params, API.CODE_POST_REQUEST, this);
            nr.execute();
        }
    }

    private void hideButton() {
        b.setVisibility(View.GONE);
        b.setEnabled(false);
    }

    private void showButton() {
        b.setVisibility(View.VISIBLE);
        b.setEnabled(true);
    }

    public void reloadButton(JSONObject jo) {
        int errorcode = -1;
        try {
            errorcode = jo.getInt("errorcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorcode == 0) {
            try {
                b.setEnabled(true);
                if (jo.getBoolean("following")) {
                    Log.i("following", "true");
                    b.setText("Nicht mehr folgen");
                } else {
                    Log.i("following", "false");
                    b.setText("Folgen");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            b.setText("Folgen");
            b.setEnabled(false);
        }
    }

    public void follow(JSONObject jo) {
        int errorcode = -1;
        try {
            errorcode = jo.getInt("errorcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorcode == 0) {
            try {
                if (jo.getBoolean("followed")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Sie folgen nun \"" + query + "\"", Toast.LENGTH_LONG).show();
                    b.setText("Nicht mehr folgen");
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Sie folgen nun \"" + query + "\" nicht mehr", Toast.LENGTH_LONG).show();
                    b.setText("Folgen");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Benutzer \"" + query + "\" existiert nicht", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == b) {
            HashMap<String, String> params = new HashMap<>();
            params.put("target", query);
            params.put("username", logged_in);
            PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_FOLLOW, params, API.CODE_POST_REQUEST, this);
            nr.execute();
        }
    }
}
