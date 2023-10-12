package com.example.growyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Vegetables extends AppCompatActivity {
    ImageView tomato,potato,cauliflower,cabbage,chilli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetables);
        tomato = findViewById(R.id.tomato);
        potato = findViewById(R.id.potato);
        cauliflower = findViewById(R.id.cauliflower);
        cabbage = findViewById(R.id.cabbage);
        chilli = findViewById(R.id.chilli);
        tomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant_data = "file:///android_asset/tomato.html";
                String plant_name = "Tomato";
                Intent i = new Intent(Vegetables.this,Steps_to_Grow.class);
                i.putExtra("plant_data",plant_data);
                i.putExtra("plant_name",plant_name);
                startActivity(i);
                finish();
            }
        });
        cabbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Cabbage";
                Intent i = new Intent(Vegetables.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        cauliflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Cauliflower";
                Intent i = new Intent(Vegetables.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        potato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant = "Potato";
                Intent i = new Intent(Vegetables.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant);
                startActivity(i);
                finish();
            }
        });
        chilli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plant_name = "Chilli";
                Steps_Data sd = new Steps_Data();
                Intent i = new Intent(Vegetables.this,Steps_to_Grow.class);
                i.putExtra("plant_name",plant_name);
                i.putStringArrayListExtra("steps",sd.getSteps_chilli());
                startActivity(i);
                finish();
            }
        });
    }
}