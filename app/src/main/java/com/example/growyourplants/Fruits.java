package com.example.growyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileUriExposedException;
import android.view.View;
import android.widget.ImageView;

public class Fruits extends AppCompatActivity {
    ImageView banana,guava,mango,pomeg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
        banana = findViewById(R.id.banana);
        guava = findViewById(R.id.Guava);
        mango = findViewById(R.id.Mango);
        pomeg = findViewById(R.id.pomegranate);
        banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Banana";
                Intent i = new Intent(Fruits.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        guava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Guava";
                Intent i = new Intent(Fruits.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        mango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Mango";
                Intent i = new Intent(Fruits.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        pomeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Pomegranate";
                Intent i = new Intent(Fruits.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
    }
}