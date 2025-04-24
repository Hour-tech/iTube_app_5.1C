package com.henry.itubeapp;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PlayVideo extends AppCompatActivity {

    WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myWebView = findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);

        String videoUrl = getIntent().getStringExtra("video_url");

        if (videoUrl != null && videoUrl.contains("watch?v=")) {
            videoUrl = videoUrl.replace("watch?v=", "embed/");
        }

        String html = "<html><body style='margin:0'><iframe width='100%' height='100%' src='" + videoUrl + "' frameborder='0' allowfullscreen></iframe></body></html>";
        myWebView.loadData(html, "text/html", "utf-8");

    }
}
