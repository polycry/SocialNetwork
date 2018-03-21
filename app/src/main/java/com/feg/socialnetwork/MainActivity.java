package com.feg.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> posts;
   // private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* HashMap<String, String> params = new HashMap<>();
        params.put("username", "feggg");
        params.put("password", "geggg");
        params.put("email", "sadsadsggggega@lol.com");
        PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_REGISTER, params, API.CODE_POST_REQUEST, getApplicationContext());
        nr.execute();
*/


        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText username = findViewById(R.id.txt_username);
                EditText password=findViewById(R.id.txt_password);

                 String user= username.getText().toString();
                 String pass= password.getText().toString();


                HashMap<String, String> params = new HashMap<>();
                params.put("username", user);
                params.put("password", pass);
                PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_LOGIN, params, API.CODE_POST_REQUEST, getApplicationContext());
                nr.execute();
                //Intent i = new Intent(getBaseContext(), FeedActivity.class);
                //startActivity(i);
            }
        });
    }
}
