package com.example.twoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondAcrivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String information = intent.getStringExtra("information");
        mTextView.setText(information);
        Intent data = new Intent();
        data.putExtra("second information", "返回了secondActivity的信息");
        setResult(Activity.RESULT_OK, data);
    }
}