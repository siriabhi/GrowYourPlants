package com.example.growyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class flowers extends AppCompatActivity {
    ImageView rose,lily,hibiscus,marigold,chrys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers);
        rose = findViewById(R.id.rose);
        lily = findViewById(R.id.lily);
        marigold = findViewById(R.id.marigold);
        hibiscus = findViewById(R.id.hibiscus);
        chrys = findViewById(R.id.chrys);
        rose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Rose";
                Intent i = new Intent(flowers.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        lily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Lily";
                Intent i = new Intent(flowers.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        marigold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Marigold";
                Intent i = new Intent(flowers.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        hibiscus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Hibiscus";
                Intent i = new Intent(flowers.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        chrys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Chrysanthemum";
                Intent i = new Intent(flowers.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });

    }
}