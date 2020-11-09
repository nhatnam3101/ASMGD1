package com.example.asmgd1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class xemtin extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemtin);
        getWindow().setStatusBarColor(ContextCompat.getColor(xemtin.this, R.color.colorOrange));
        webView=findViewById(R.id.xemtin);
        Intent i =getIntent();
        String dlink= i.getStringExtra("link");
        webView.loadUrl(dlink);
        webView.setWebViewClient(new WebViewClient());
    }
}