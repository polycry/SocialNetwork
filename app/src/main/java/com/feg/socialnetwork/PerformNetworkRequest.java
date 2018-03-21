package com.feg.socialnetwork;

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

    Context context;

    public PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode, Context context) {
        this.url = url;
        this.params = params;
        this.requestCode = requestCode;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject object = new JSONObject(s);
            if (!object.getBoolean("error")) {
                Toast.makeText(context, object.getString("errorcode"), Toast.LENGTH_SHORT).show();
            }
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
