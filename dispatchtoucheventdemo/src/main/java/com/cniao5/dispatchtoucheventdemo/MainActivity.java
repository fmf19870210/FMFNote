package com.cniao5.dispatchtoucheventdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cniao5.dispatchtoucheventdemo.ViewPagerListClash.ViewPagerListViewClashActivity;
import com.cniao5.dispatchtoucheventdemo.scrollvieweditclash.ScrollViewEditTextClashActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          findViewById(R.id.tv_1).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 Intent intent =   new Intent(MainActivity.this, ScrollViewEditTextClashActivity.class);
                   startActivity(intent);
              }
          });

        findViewById(R.id.tv_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivity.this, ViewPagerListViewClashActivity.class);
                startActivity(intent);
            }
        });
    }
}