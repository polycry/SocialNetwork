package com.feg.socialnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_feed);
        ListView lv = findViewById(R.id.list_feed);

        ArrayList<Post> lst = new ArrayList<Post>();
        lst.add(new Post("Geier", "I bimsdssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssfsdfsdfdsffsddsfdsfsfsdfsfsefsfefsefeeffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", new Date(4, 9, 1999)));
        lst.add(new Post("Feg", "asdasdas", new Date(1999, 9, 4)));
        lst.add(new Post("Jonas", "asdasdsadsad", new Date(1999, 12, 3)));
        lst.add(new Post("Jonadasdadaas", "asdasdasd", new Date(1999, 12, 2)));
        lst.add(new Post("Jonsdasdasdaas", "asdasdsaasdasdasdasdasdadadsad", new Date(1999, 9, 1)));

        PostAdapter pa = new PostAdapter(this, lst);

        lv.setAdapter(pa);

    }

    public void refreshFeed(JSONObject jo) {
        int errorcode = -1;
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

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            
        }
    }

}
