package com.jrdbnntt.cop4656.homework3;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jrdbnntt.cop4656.homework3.UrlListContent.UrlListItem;


public class MainActivity extends AppCompatActivity implements UrlListFragment.OnListFragmentInteractionListener {

    private static final String TAG_WEB_FRAGMENT = "web_fragment";
    private static final String TAG_LIST_FRAGMENT = "list_fragment";

    private MyWebFragment webFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the web and list fragments
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans;

        trans = manager.beginTransaction();
        webFragment = new MyWebFragment();
        trans.add(R.id.web_fragment_container, webFragment, TAG_WEB_FRAGMENT);
        trans.commit();

        trans = manager.beginTransaction();
        UrlListFragment listFragment = new UrlListFragment();
        trans.add(R.id.list_fragment_container, listFragment, TAG_LIST_FRAGMENT);
        trans.commit();
    }

    @Override
    public void onListFragmentInteraction(UrlListItem item) {
        Log.d("TEST", "onListFragmentInteraction Called");
        webFragment.loadUrl(item.getUrl());
    }

}
