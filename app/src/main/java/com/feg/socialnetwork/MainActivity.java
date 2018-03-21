package com.feg.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
   // private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);

        HashMap<String, String> params = new HashMap<>();
        params.put("username", "test");
        params.put("password", "ibimsdaspasswort");
        params.put("email", "sadsadsa@lol.com");
        PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_REGISTER, params, API.CODE_POST_REQUEST, getApplicationContext());
        nr.execute();

    }


}
