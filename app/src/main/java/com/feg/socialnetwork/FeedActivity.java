package com.feg.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        ListView lv = findViewById(R.id.list_feed);
        String[] array = {"Shop1", "Shop2", "Shop3"};
        ArrayList<Post> lst = new ArrayList<Post>();
        lst.add(new Post("Geier", "I bims", new Date(4,9,1999).toString()));
        lst.add(new Post("Feg", "asdasdas", new Date(4,9,1999).toString()));
        lst.add(new Post("Jonas", "asdasdsadsad", new Date(4,9,1999).toString()));


        PostAdapter pa = new PostAdapter(this, lst);

        lv.setAdapter(pa);

    }
}
