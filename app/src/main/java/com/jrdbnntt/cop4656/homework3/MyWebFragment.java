package com.jrdbnntt.cop4656.homework3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Web browser fragment
 */
public class MyWebFragment extends Fragment {
    private WebView webView;

    public MyWebFragment() {
        // Required empty public constructor
    }

    public static MyWebFragment newInstance() {
        return new MyWebFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_view = inflater.inflate(R.layout.fragment_my_web, container, false);

        webView = (WebView) fragment_view.findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        return fragment_view;
    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

}
