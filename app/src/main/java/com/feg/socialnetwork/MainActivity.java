package com.feg.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
   // private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        ListView lv = findViewById(R.id.list_feed);
/*
        posts.add(new Post("Geier", "I bims", new Date(4, 9, 1999)));

        // Create an ArrayAdapter from List
        final ArrayAdapter<Post> arrayAdapter = new ArrayAdapter<Post>
                (this, android.R.layout., posts);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);
*/
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);




    }
}
