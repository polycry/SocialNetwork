package com.feg.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {

    Button post;
    EditText post_content;
    String logged_in_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        Bundle b = getIntent().getExtras();
        logged_in_user = b.getString("username");
        post = findViewById(R.id.post);
        post.setOnClickListener(this);

        post_content = findViewById(R.id.post_content);
    }

    @Override
    public void onClick(View view) {

        String post_content_txt = post_content.getText().toString();
        if (view == post) {

            HashMap<String, String> params = new HashMap<>();
            params.put("username", logged_in_user);
            params.put("content", post_content_txt);
            if (!post_content_txt.isEmpty()) {

                PerformNetworkRequest pn = new PerformNetworkRequest(API.URL_INSERTPOST, params, API.CODE_POST_REQUEST, this);
                pn.execute();
            }
        }

    }

    public void addpost(JSONObject object) {
        Toast.makeText(getApplicationContext(), "Post erfolgreich erstellt", Toast.LENGTH_SHORT).show();

        int errorcode = -1;
        try {
            errorcode = object.getInt("errorcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorcode == 0) {
            Toast.makeText(getApplicationContext(), "Erfolgreich gepostet.", Toast.LENGTH_LONG).show();
            this.finish();
        } else {
            Toast.makeText(getApplicationContext(), "Unerwarteter Fehler", Toast.LENGTH_LONG).show();
        }


    }
}
