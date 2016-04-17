package com.uksapps.custombooklist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //For opening the page where your book can be bought
        WebView page = (WebView)findViewById(R.id.webView);
        Bundle description = getIntent().getExtras();
        String book_name = description.getString("Name");
        try{getSupportActionBar().setTitle(book_name);
        String url = description.getString("Address");
        page.loadUrl(url);}catch (Exception e){e.fillInStackTrace();}


    }
}
