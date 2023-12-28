package com.example.kamusindonesiaminang.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.widget.Toolbar;

import com.example.kamusindonesiaminang.R;


public class MainActivity extends AppCompatActivity {

    ImageView imgIndo, imgMinang;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgIndo = findViewById(R.id.imgIndo);
        imgMinang = findViewById(R.id.imgMinang);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        imgIndo.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, IndonesiaMinangActivity.class);
            startActivity(intent);
        });

        imgMinang.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MinangIndonesiaActivity.class);
            startActivity(intent);
        });
    }

}
