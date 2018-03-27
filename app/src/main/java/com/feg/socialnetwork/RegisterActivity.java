package com.feg.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register;
    private EditText email;
    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register_ok);
        register.setOnClickListener(this);

        email = findViewById(R.id.txt_email);
        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);

    }

    @Override
    public void onClick(View view) {

        String email_string = email.getText().toString();
        String username_string = username.getText().toString();
        String password_string = password.getText().toString();

        if (view == register) {
            if (!email_string.isEmpty() && !password_string.isEmpty() && !username_string.isEmpty()) {
                HashMap<String, String> params = new HashMap<>();
                params.put("username", username_string);
                params.put("password", password_string);
                params.put("email", email_string);

                PerformNetworkRequest pn = new PerformNetworkRequest(API.URL_REGISTER, params, API.CODE_POST_REQUEST, this);
                pn.execute();
            }
        }

    }

    public void register(JSONObject object) {

        int errorcode = -1;
        try {
            errorcode = object.getInt("errorcode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (errorcode == 0) {
            Toast.makeText(getApplicationContext(), "Registrierung erfolgreich!", Toast.LENGTH_LONG).show();
            this.finish();
        } else if (errorcode == 1) {
            Toast.makeText(getApplicationContext(), "User bereits vergeben!", Toast.LENGTH_LONG).show();
        } else if (errorcode == 2) {
            Toast.makeText(getApplicationContext(), "Email existiert bereits!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Registierung fehlgeschlagen", Toast.LENGTH_LONG).show();
        }

    }
}
