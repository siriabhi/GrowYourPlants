package com.example.growyourplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Steps_to_Grow extends AppCompatActivity {

    Button myGardenButton,next_button;
    WebView webView;
    ArrayList<String> data_item = new ArrayList<>();
    int i=0;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    String userUid = currentUser.getUid();
    DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Registered Users").child(userUid);
    String PlantId = dr.child("My Garden").push().getKey();
    DatabaseReference df = dr.child("My Garden").child(PlantId);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_to_grow);
        myGardenButton = findViewById(R.id.garden);
        Intent intent = getIntent();
        ArrayList<String> receivedArray = intent.getStringArrayListExtra("steps");
        String name = intent.getStringExtra("plant_name");
        webView = findViewById(R.id.webView);
        next_button = findViewById(R.id.next_button);
        data_item.addAll(receivedArray);
        webView.loadUrl(data_item.get(0));

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i < data_item.size()){
                    i = i+1;
                    System.out.println(i);
                    webView.loadUrl(data_item.get(i));
                }
                if(i==data_item.size()){
                    next_button.setEnabled(false);
                    webView.loadUrl(data_item.get(0));
                }
            }
        });
        myGardenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                df.child("name").setValue(name);
                Toast.makeText(Steps_to_Grow.this, "Added to my Garden!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}