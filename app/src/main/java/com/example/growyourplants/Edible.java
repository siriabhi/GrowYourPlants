package com.example.growyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Edible extends AppCompatActivity {
    ImageView curryleaves,mint,menthi,spinach,coriander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edible);
        curryleaves = findViewById(R.id.curryleaves);
        mint = findViewById(R.id.mint);
        menthi = findViewById(R.id.menthi);
        spinach = findViewById(R.id.spinach);
        coriander = findViewById(R.id.coriander);
        curryleaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Curry leaves";
                Intent i = new Intent(Edible.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        mint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Mint";
                Intent i = new Intent(Edible.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        menthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "menthi leaves";
                Intent i = new Intent(Edible.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        coriander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Coriander";
                Intent i = new Intent(Edible.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        spinach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Spinach";
                Intent i = new Intent(Edible.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
    }
}