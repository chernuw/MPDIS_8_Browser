package com.example.chernuwnote.mpdis_8_browser;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {

    Button b1;
    Button bClear;
    Button bNext;
    Button bBack;
    Button bRefresh;

    EditText ed1;

    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        bClear = (Button) findViewById(R.id.hButton);
        bNext = (Button) findViewById(R.id.nButton);
        bBack = (Button) findViewById(R.id.bButton);
        bRefresh = (Button) findViewById(R.id.rButton);
        ed1 = (EditText) findViewById(R.id.editText);

        b1.setOnClickListener(this);
        bClear.setOnClickListener(this);
        bNext.setOnClickListener(this);
        bBack.setOnClickListener(this);
        bRefresh.setOnClickListener(this);

        bNext.setEnabled(false);
        bBack.setEnabled(false);

        wv1 = (WebView) findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());
    }

    public void onClick(View view) {
        if (b1.equals(view)) {
            String url = ed1.getText().toString();
            wv1.getSettings().setLoadsImagesAutomatically(true);
            wv1.getSettings().setJavaScriptEnabled(true);
            wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            url = "http://www." + url;
            wv1.loadUrl(url);
            if (wv1.canGoBack()){
                bBack.setEnabled(true);
            }
            if (wv1.canGoForward()){
                bNext.setEnabled(true);
            }
        } else if (bBack.equals(view)) {
            if (wv1.canGoBack()) {
                wv1.goBack();
            }
        } else if (bNext.equals(view)) {
            if (wv1.canGoForward()) {
                wv1.goForward ();
            }
        } else if (bClear.equals(view)) {
            wv1.clearHistory();
        } else if (bRefresh.equals(view)){
            wv1.reload();
        }

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
