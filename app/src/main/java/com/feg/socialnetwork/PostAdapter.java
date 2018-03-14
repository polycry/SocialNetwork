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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Post post = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.feed_item, viewGroup, false);
            viewHolder.post = convertView.findViewById(R.id.txt_post);
            viewHolder.poster = convertView.findViewById(R.id.txt_poster);
            //viewHolder.date= convertView.findViewById(R.id.txt_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
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

        return convertView;
    }


}
