package test.pro.hub_sparrow.Transport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import test.pro.hub_sparrow.R;

public class Transport_Activity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        webView=findViewById(R.id.webview_transport);
        WebSettings webSettings=webView.getSettings();
        webSettings.getJavaScriptEnabled();
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://hamdarduniversity.edu.bd/campus/transportation");
    }
}