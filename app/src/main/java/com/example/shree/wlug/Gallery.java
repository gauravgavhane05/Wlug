package com.example.shree.wlug;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class Gallery extends AppCompatActivity {

    WebView webView;
    ProgressDialog pdg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toast.makeText(this,"Contains large data can take time to load!!!",Toast.LENGTH_LONG).show();
        pdg=new ProgressDialog(Gallery.this);
        pdg.setMessage("Loading");
        pdg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdg.setIndeterminate(true);
        pdg.setCancelable(true);
        pdg.show();

        webView = (WebView) findViewById(R.id.gallery);
        webView.setWebChromeClient(new WebChromeClient());

        webView.loadUrl("http://wcewlug.org/gallery.php");
        pdg.dismiss();

    }

    @Override
    protected void onStart() {
        super.onStart();
        pdg=new ProgressDialog(Gallery.this);
        pdg.setMessage("Loading");
        pdg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdg.setIndeterminate(true);
        pdg.setCancelable(true);
        pdg.show();

    }
}
