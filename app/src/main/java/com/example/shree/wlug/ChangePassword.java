package com.example.shree.wlug;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePassword extends AppCompatActivity {

    EditText changeEmail;
    TextInputLayout errorChangepassword;
    Button changePassword;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mAuth = FirebaseAuth.getInstance();
        changeEmail = (EditText) findViewById(R.id.email);
        changePassword =(Button) findViewById(R.id.changepassword);
        errorChangepassword =(TextInputLayout) findViewById(R.id.errorEmail);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    public void changePassword()
    {
        final String email =changeEmail.getText().toString();
        if(email.isEmpty())
        {
            errorChangepassword.setError("Your Email is Required");
        }
        else
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ChangePassword.this);
            final AlertDialog.Builder yesbuilder = new AlertDialog.Builder(ChangePassword.this);
            builder.setMessage("Do you want to change your password??");

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                   // Toast.makeText(ChangePassword.this," Your password remains unchanged",Toast.LENGTH_SHORT).show();
                    yesbuilder.setMessage("Your password remains unchanged..");
                    AlertDialog alert1=yesbuilder.create();
                    alert1.show();
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int which) {
                            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //Toast.makeText(ChangePassword.this, "Email has been sent to your Gmail account to change your Password.. ", Toast.LENGTH_SHORT).show();
                                    yesbuilder.setMessage("Email has been sent to your Gmail account to change your Password..");
                                    AlertDialog alert1=yesbuilder.create();
                                    alert1.show();
                                    dialog.cancel();
                                }
                            });
                        }
                    });



            AlertDialog alert=builder.create();
            alert.show();


        }
    }
}
