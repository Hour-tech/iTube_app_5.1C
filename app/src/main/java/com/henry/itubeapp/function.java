package com.henry.itubeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class function extends AppCompatActivity {

    Button myPlayList, AddButton, playButton;
    EditText urlInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_function);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myPlayList = findViewById(R.id.buttonMyPlayList);
        urlInput = findViewById(R.id.inputURL);
        AddButton = findViewById(R.id.addButton);
        playButton = findViewById(R.id.ButtonPlay);

        myPlayList.setOnClickListener(v -> {
            Intent intent = new Intent(function.this, MyPlaylist.class);
            startActivity(intent);
        });

        AddButton.setOnClickListener(v -> {
            DataBaseHelper myDB = new DataBaseHelper(function.this);
            myDB.addUrl(urlInput.getText().toString().trim());
        });

        playButton.setOnClickListener(v -> {
            String url = urlInput.getText().toString().trim();
            Intent intent = new Intent(function.this, PlayVideo.class);
            intent.putExtra("video_url", url); // Send the YouTube URL
            startActivity(intent);
        });

    }
}
