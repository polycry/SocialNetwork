package com.feg.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Post> posts;
    private Button login;
    private Boolean logged_in;


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
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    public void login(JSONObject jo) {
        int errorcode = -1;
        try {
            errorcode = jo.getInt("errorcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorcode == 0) {
            Toast.makeText(getApplicationContext(), "Anmeldung erfolgreich!", Toast.LENGTH_LONG).show();
            logged_in = true;
            Intent i = new Intent(getBaseContext(), FeedActivity.class);
            startActivity(i);

        } else if (errorcode == 1) {
            Toast.makeText(getApplicationContext(), "Falsches Passwort", Toast.LENGTH_LONG).show();
        } else if (errorcode == 2) {
            Toast.makeText(getApplicationContext(), "Benutzer existiert nicht", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Anmeldung fehlgeschlagen", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        if (v == login) {
            EditText username = findViewById(R.id.txt_username);
            EditText password = findViewById(R.id.txt_password);

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (!user.isEmpty() && !pass.isEmpty()) {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", user);
                params.put("password", pass);
                PerformNetworkRequest nr = new PerformNetworkRequest(API.URL_LOGIN, params, API.CODE_POST_REQUEST, this);
                nr.execute();
            } else {
                Toast.makeText(getApplicationContext(), "Fülle bitte beide Felder aus", Toast.LENGTH_LONG).show();
            }


        }
    }
}
