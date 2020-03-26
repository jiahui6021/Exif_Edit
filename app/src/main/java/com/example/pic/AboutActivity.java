package com.example.pic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.activity_about);
        linearLayout.getBackground().setAlpha(150);
    }
}
