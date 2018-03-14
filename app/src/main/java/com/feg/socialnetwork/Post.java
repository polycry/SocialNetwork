package com.feg.socialnetwork;

import java.util.Date;

/**
 * Created by Jonas on 14.03.2018.
 */

public class Post {

    private String post = null;
    private String poster = null;
    private String date = null;

    public Post(String poster, String post, String date) {
        this.setPoster(poster);
        this.setPost(post);
        this.setDate(date);
    }


    //GETTER SETTER

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
