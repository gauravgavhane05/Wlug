package com.example.shree.wlug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    Button add,change;
    Button changePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changePass =(AppCompatButton) findViewById(R.id.changepassword);
        add = (Button) findViewById(R.id.addyourProfile);
        change = (Button) findViewById(R.id.changeProfile);
    }

    public void addYourProfile(View view)
    {
        startActivity(new Intent(getApplicationContext(),AddProfile.class));
    }
    public void changePassword(View view)
    {
        startActivity(new Intent(Settings.this,ChangePassword.class));
    }

    public void changeProfile(View view)
    {
        startActivity(new Intent(Settings.this,ProfilePicture.class));
    }
}
