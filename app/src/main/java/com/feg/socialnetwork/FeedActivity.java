package com.feg.socialnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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
        lst.add(new Post("Feg", "asdasdas", new Date(4, 9, 1999)));
        lst.add(new Post("Jonas", "asdasdsadsad", new Date(4, 9, 1999)));


        PostAdapter pa = new PostAdapter(this, lst);

        lv.setAdapter(pa);

    }
}
