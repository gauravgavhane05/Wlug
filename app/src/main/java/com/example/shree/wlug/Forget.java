package com.example.shree.wlug;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget extends AppCompatActivity {


    EditText emailforget;
    Button getPassword;
    FirebaseAuth mAuth;
    TextInputLayout getEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        mAuth=FirebaseAuth.getInstance();
        emailforget = (EditText) findViewById(R.id.Email);
        getPassword = (Button) findViewById(R.id.getPassword);
        getEmail =(TextInputLayout) findViewById(R.id.getEmail);
        }

        public void getPassword(View view)
        {
            String email=emailforget.getText().toString();
            if(email.isEmpty()) {
                getEmail.setError("Email is Required");
            }
            else {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(Forget.this, "Password Has Been Sent To Your Email..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }



}
