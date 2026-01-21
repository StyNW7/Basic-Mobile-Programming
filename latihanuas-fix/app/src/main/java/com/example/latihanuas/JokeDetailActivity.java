package com.example.latihanuas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JokeDetailActivity extends AppCompatActivity {
    TextView txtSetup, txtPunchline, txtType, txtLikes, txtViews, txtshares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_joke_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtSetup = findViewById(R.id.txtSetup);
        txtPunchline = findViewById(R.id.txtPunchline);
        txtType = findViewById(R.id.txtType);
        txtLikes = findViewById(R.id.txtLikes);
        txtViews = findViewById(R.id.txtViews);
        txtshares = findViewById(R.id.txtShare);

        //TODO: tampilin data dari Intent ke textview
        Intent i = getIntent();
        txtSetup.setText(i.getStringExtra("setup"));
        txtPunchline.setText(i.getStringExtra("punchline"));
        txtType.setText(i.getStringExtra("type"));
        txtLikes.setText(String.valueOf(i.getIntExtra("likes", 0)));
        txtViews.setText(String.valueOf(i.getIntExtra("views", 0)));
        txtshares.setText(String.valueOf(i.getIntExtra("share", 0)));

    }
}