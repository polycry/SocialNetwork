package com.feg.socialnetwork;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Jonas on 14.03.2018.
 */

public class Post {

    private String content = null;
    private String poster = null;
    private Date date = null;

    public Post() {

    }

    public Post(String poster, String content, Date date) {
        this.setPoster(poster);
        this.setContent(content);
        this.setDate(date);
    }

    public static Post formPost(JSONObject jo) {
        Post p = new Post();
        try {
            p.setPoster(jo.getString("username"));
            p.setDate(new Date(jo.getString("date")));
            p.setContent(jo.getString("content"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return p;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
