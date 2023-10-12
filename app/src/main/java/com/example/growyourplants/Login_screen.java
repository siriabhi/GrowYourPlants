package com.example.growyourplants;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login_screen extends AppCompatActivity {
    private EditText emailID, password;
    private Button login, register;
    private ImageView show_hide;
    private ProgressBar pb;
    private TextView forgot_pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        emailID = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
        pb = findViewById(R.id.progress);
        show_hide = findViewById(R.id.show_hide);
        forgot_pass = findViewById(R.id.forgot_pass);
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_screen.this,Forgot_Password.class));
            }
        });
        show_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    // if password is visible hide it
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    // change icon to hide
                    show_hide.setImageResource(R.drawable.ic_hide_pwd);
                }else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show_hide.setImageResource(R.drawable.ic_show_pwd);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                String email = emailID.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_screen.this, "Enter your email ID", Toast.LENGTH_SHORT).show();
                    emailID.setError("Email is required");
                    emailID.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Login_screen.this, "Re-Enter you email ID", Toast.LENGTH_SHORT).show();
                    emailID.setError("Valid email is required");
                    emailID.requestFocus();
                } else if (pass.isEmpty()) {
                    Toast.makeText(Login_screen.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    password.setError("Password is required");
                    password.requestFocus();
                } else if (pass.length() < 6) {
                    Toast.makeText(Login_screen.this, "Enter password with more than 6 characters", Toast.LENGTH_SHORT).show();
                    password.setError("Password is weak");
                    password.requestFocus();
                } else {

                    registerUser(email, pass);
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                String email = emailID.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_screen.this, "Enter your email ID", Toast.LENGTH_SHORT).show();
                    emailID.setError("Email is required");
                    emailID.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Login_screen.this, "Re-Enter you email ID", Toast.LENGTH_SHORT).show();
                    emailID.setError("Valid email is required");
                    emailID.requestFocus();
                } else if (pass.isEmpty()) {
                    Toast.makeText(Login_screen.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    password.setError("Password is required");
                    password.requestFocus();
                } else if (pass.length() < 6) {
                    Toast.makeText(Login_screen.this, "Enter password with more than 6 characters", Toast.LENGTH_SHORT).show();
                    password.setError("Password is weak");
                    password.requestFocus();
                } else {

                    loginUser(email, pass);
                }
            }
        });

    }

    private void loginUser(String email, String pass) {
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(Login_screen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    UserDatabase udb = new UserDatabase(email, pass);
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Registered Users").child(user.getUid());
                    DatabaseReference myGardenReference = dr.child("UserData");
                    myGardenReference.setValue(udb);
                    Toast.makeText(Login_screen.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login_screen.this,Home.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }else{
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e){
                        emailID.setError("User doesn't exists or no longer valid. Please register");
                        emailID.requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        emailID.setError("Invalid login credentials.Please try again");
                        emailID.requestFocus();
                    }
                    catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(Login_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(Login_screen.this, "Unable to login, Try again", Toast.LENGTH_SHORT).show();
                }
                pb.setVisibility(View.GONE);
            }
        });
    }
    private void registerUser(String email, String pass) {
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(Login_screen.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    UserDatabase udb = new UserDatabase(email, pass);
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Registered Users");

                    dr.child(user.getUid()).setValue(udb).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login_screen.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login_screen.this, Home.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login_screen.this, "Registration failed,Try again", Toast.LENGTH_SHORT).show();
                            }
                            pb.setVisibility(View.GONE);
                        }
                    });

                } else {
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        password.setError("Your email is invalid or already in use , Re-Enter");
                        password.requestFocus();
                    } catch (FirebaseAuthUserCollisionException e) {
                        password.setError("User is already registered with this email. Use another email");
                        password.requestFocus();
                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(Login_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    pb.setVisibility(View.GONE);

                }
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login_screen.this,Home.class));
        }else{
            Toast.makeText(this, "You can login now", Toast.LENGTH_SHORT).show();
        }

    }
}