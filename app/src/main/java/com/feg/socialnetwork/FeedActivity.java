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
        lst.add(new Post("Geier", "I bims", "04/09/1999"));
        lst.add(new Post("Feg", "asdasdas", "04/09/1999"));
        lst.add(new Post("Jonas", "asdasdsadsad", "04/09/1999"));


        PostAdapter pa = new PostAdapter(this, lst);

        lv.setAdapter(pa);

    }
}
