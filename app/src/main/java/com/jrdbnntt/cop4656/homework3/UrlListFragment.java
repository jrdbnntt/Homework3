package com.jrdbnntt.cop4656.homework3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jrdbnntt.cop4656.homework3.UrlListContent.UrlListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class UrlListFragment extends Fragment {
    public static final String ARG_URL = "url";
    public static final String PREFS_NAME = "UrlListPreferences";
    public static final String PREF_STORED_URL_0 = "PREF_STORED_URL_0";
    public static final String PREF_STORED_URL_1 = "PREF_STORED_URL_1";
    public static final String PREF_STORED_URL_2 = "PREF_STORED_URL_2";
    public static final String PREF_ENTERED_URL = "PREF_ENTERED_URL";

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UrlListFragment() {}

    public static UrlListFragment newInstance(String extraUrl) {
        Bundle args = new Bundle();
        args.putString(ARG_URL, extraUrl);
        UrlListFragment fragment = new UrlListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static UrlListFragment newInstance() {
        return new UrlListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_urllist_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new MyUrlListRecyclerViewAdapter(getItems(), mListener));
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                    layoutManager.getOrientation()));
        }
        return view;
    }

    public List<UrlListItem> getItems() {
        ArrayList<UrlListItem> items = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        String[] defaultUrls = getContext().getResources().getStringArray(R.array.default_urls);

        // Load shared preferences
        SharedPreferences settings = getContext().getSharedPreferences(PREFS_NAME, 0);
        urls.add(settings.getString(PREF_STORED_URL_0, defaultUrls[0]));
        urls.add(settings.getString(PREF_STORED_URL_1, defaultUrls[1]));
        urls.add(settings.getString(PREF_STORED_URL_2, defaultUrls[2]));

        // Load extra url if passed, or try to load from prefs
        Bundle args = getArguments();
        String extraUrl = null;
        if (args != null) {
            extraUrl = args.getString(ARG_URL);
        }

        if (extraUrl == null) {
            extraUrl = settings.getString(PREF_ENTERED_URL, null);
        }

        // Save shared preferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_STORED_URL_0, urls.get(0));
        editor.putString(PREF_STORED_URL_1, urls.get(1));
        editor.putString(PREF_STORED_URL_2, urls.get(2));

        if (extraUrl != null) {
            urls.add(extraUrl);
            editor.putString(PREF_ENTERED_URL, extraUrl);
        }

        editor.apply();

        // Populate items
        for (String url : urls) {
            items.add(new UrlListItem(url));
        }

        return items;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(UrlListItem item);
    }
}
