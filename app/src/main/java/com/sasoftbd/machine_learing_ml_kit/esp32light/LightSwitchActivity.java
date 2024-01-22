package com.sasoftbd.machine_learing_ml_kit.esp32light;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sasoftbd.machine_learing_ml_kit.R;

public class LightSwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_switch);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://192.168.4.1/L");
        myWebView.setWebViewClient(new WebViewClient());

        //TEST THIS APPS
    }
}