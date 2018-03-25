package com.feg.socialnetwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Felix on 25.03.2018.
 */

public class FeedFragment extends Fragment {

    private static final String TAG = "FeedFragment";
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        lv = (ListView) view.findViewById(R.id.list_feed);
        return view;
    }
}
