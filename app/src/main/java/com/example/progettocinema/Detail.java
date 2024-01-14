package com.example.progettocinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("movieId", 0);
        TextView tv = findViewById(R.id.textView2);
        tv.setText("ID: " + id);
        Button bottone = findViewById(R.id.detailBack);
        bottone.setOnClickListener(v -> {
            finish();
        });
    }
}