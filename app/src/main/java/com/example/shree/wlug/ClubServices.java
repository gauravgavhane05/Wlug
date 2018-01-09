package com.example.shree.wlug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.example.shree.wlug.R.*;

public class ClubServices extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_club_services);

        webView = (WebView)findViewById(R.id.clubservices);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://wcewlug.org/clubservices.php");

    }
}
