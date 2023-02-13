package com.example.next_gen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;



public class Market extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                onStop();
                startActivity(new Intent(this, Wardrobe.class));
                finish();
                break;
            case R.id.item2:
                onStop();
                startActivity(new Intent(this, ClothSelection.class));
                finish();
                break;
            case R.id.item3:
                onStop();
                Intent j = new Intent(this, Market.class);
                startActivity(j);
                finish();
                break;
            case R.id.item4:
                onStop();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }

        return true;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.market_activity);

        assert getSupportActionBar() != null;

            WebView web = findViewById(R.id.web_market);

            web.setWebViewClient(new WebViewClient());

            web.getSettings().setLoadsImagesAutomatically(true);

            web.getSettings().setJavaScriptEnabled(true);

            web.getSettings().setBuiltInZoomControls(true);

            web.getSettings().setSupportZoom(true);

            web.getSettings().setLoadWithOverviewMode(true);

            web.getSettings().setUseWideViewPort(true);

            web.getSettings().setAllowContentAccess(true);

            web.loadUrl("https://www.pepper.pl");
        }
}

