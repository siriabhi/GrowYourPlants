package com.example.growyourplants;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class Forgot_Password extends AppCompatActivity {
    private Button reset;
    private EditText emailID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        reset = findViewById(R.id.reset);
        emailID = findViewById(R.id.Email_add);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Forgot_Password.this, "Enter your email ID", Toast.LENGTH_SHORT).show();
                    emailID.setError("Email is required");
                    emailID.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Forgot_Password.this, "Re-Enter you email ID", Toast.LENGTH_SHORT).show();
                    emailID.setError("Valid email is required");
                    emailID.requestFocus();
                } else {
                   resetPassword(email);
                }
            }
        });
    }

    private void resetPassword(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Forgot_Password.this, "Please check your Email inbox", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Forgot_Password.this,Login_screen.class);
                    startActivity(i);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                }else{
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e){
                        emailID.setError("User doesn't exists or no longer valid. Please register");
                        emailID.requestFocus();}
                     catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                            Toast.makeText(Forgot_Password.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });
    }
}