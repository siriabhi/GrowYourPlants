package com.example.growyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class lucky extends AppCompatActivity {
    ImageView money,snake,jade,bamboo,tulasi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);
        money = findViewById(R.id.money);
        snake = findViewById(R.id.snake);
        jade = findViewById(R.id.jade);
        bamboo = findViewById(R.id.bamboo);
        tulasi = findViewById(R.id.tulasi);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Money Plant";
                Intent i = new Intent(lucky.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        snake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Snake Plant";
                Intent i = new Intent(lucky.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        jade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Jade Plant";
                Intent i = new Intent(lucky.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        bamboo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Bamboo Plant";
                Intent i = new Intent(lucky.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        tulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Tulasi Plant";
                Intent i = new Intent(lucky.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
    }
}