package com.jrdbnntt.cop4656.homework3;

import android.net.Uri;
import android.util.Log;

/**
 * Manages content of UrlList
 */

public class UrlListContent {


    public static class UrlListItem {
        private static final String DEFAULT_SCHEME = "http://";
        private final Uri uri;

        public UrlListItem(String url) {
            uri = getNormalizedUri(Uri.parse(url));
            Log.d("TEST", "" + uri.getScheme());
        }

        /**
         * Ensures valid scheme
         */
        private Uri getNormalizedUri(Uri baseUri) {
            baseUri = baseUri.normalizeScheme();
            if (baseUri.getScheme() == null) {
                return Uri.parse(DEFAULT_SCHEME + baseUri.toString());
            }
            return baseUri;
        }


        public String getUrl() {
            return uri.toString();
        }

        public String getUrlWithoutProtocol() {
            return uri.getHost() + uri.getPath();
        }
    }
}
