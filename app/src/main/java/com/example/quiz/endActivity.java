package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class endActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        float score = getIntent().getFloatExtra("score",0);
        Log.d("bhalu",score+"");
        TextView s = findViewById(R.id.scoretextview);
        s.setText(score +" ");
    }
}