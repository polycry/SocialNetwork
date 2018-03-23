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
