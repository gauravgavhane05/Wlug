package com.example.shree.wlug;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText emailEdit;
    EditText passwordEdit;
    String email,password;
    Button register;
    TextInputLayout errorEmail;
    TextInputLayout errorPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        emailEdit = (EditText) findViewById(R.id.email);
        passwordEdit = (EditText) findViewById(R.id.password);
        register= (Button) findViewById(R.id.register);

        errorEmail= (TextInputLayout) findViewById(R.id.errorEmail);
        errorPassword= (TextInputLayout) findViewById(R.id.errorPassword);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Register.this.finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
    public void register(View view)
    {

        email = emailEdit.getText().toString();
        password = passwordEdit.getText().toString();
        if(email.isEmpty() || password.isEmpty())
        {
            if(email.isEmpty()) {
                // Toast.makeText(MainActivity.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                errorEmail.setError("Email is required.");
            }
            if(password.isEmpty()) {
                //Toast.makeText(MainActivity.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                errorPassword.setError("Password is required.");
            }

        }
        else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(getApplicationContext(),task.toString(),Toast.LENGTH_LONG).show();
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Successfully Registered.", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        boolean verified = user.isEmailVerified();
                        if (verified) {
                            Toast.makeText(getApplicationContext(), "Email Verified", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Email not Verified", Toast.LENGTH_LONG).show();
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    Toast.makeText(getApplicationContext(), "Email sent to your Gmail Account. Please verify your Email", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                }
                            });
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

}
