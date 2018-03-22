package com.feg.socialnetwork;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

    private String url;
    private HashMap<String, String> params;
    private int requestCode;

    MainActivity activity;
    // declare here other activities

    public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode, Activity activity) {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
        if (url.equals(API.URL_LOGIN)) {
            this.activity = (MainActivity) activity;
        } // else if blabla donn ondre activity de oben deklariert isch initialisieren
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            // do a nor nomol obfrogen welche ausgführt worden isch!! not implemented yet yolo
            JSONObject object = new JSONObject(s);
            activity.login(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        if (requestCode == API.CODE_POST_REQUEST)
            return requestHandler.sendPostRequest(url, params);


        if (requestCode == API.CODE_GET_REQUEST)
            return requestHandler.sendGetRequest(url);

        return null;
    }
}
