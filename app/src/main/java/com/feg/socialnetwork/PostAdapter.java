package com.feg.socialnetwork;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jonas on 14.03.2018.
 */

public class PostAdapter extends ArrayAdapter<Post> {


    private static class ViewHolder {
        public TextView poster;
        public TextView post;
        public Date date;
    }

    public PostAdapter(@NonNull Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        Post post = getItem(position);
        ViewHolder viewHolder;
        if (contentView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            contentView = inflater.inflate(R.layout.list_feed, viewGroup, false);
            viewHolder.post = contentView.findViewById(R.id.txt_post);
            viewHolder.poster = contentView.findViewById(R.id.txt_poster);
           // viewHolder.date = contentView.findViewById(R.id.txt_date); //noidea

            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }

        viewHolder.poster.setText(post.getPoster());
        viewHolder.post.setText(post.getPost());

        /*
        if (Double.parseDouble(temperatur.getTemperatur()) > 0) {
            viewHolder.temperatur_icon.setImageResource(R.drawable.temp_p);
        } else {
            viewHolder.temperatur_icon.setImageResource(R.drawable.temp_n);
        }
*/

        return contentView;
    }


}