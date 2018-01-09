package com.example.shree.wlug;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MyProfile extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView nameText;
    TextView emailText;
    TextView mobileText;
    String name;
    String email;
    String mobileno;
    Uri url;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        nameText = (TextView)findViewById(R.id.name);
        emailText =(TextView) findViewById(R.id.email);
        mobileText =(TextView) findViewById(R.id.mobileno);
        image= (ImageView) findViewById(R.id.photourl);


        mAuth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        name=currentUser.getDisplayName();
        email=currentUser.getEmail();
        mobileno=currentUser.getPhoneNumber();
        url=currentUser.getPhotoUrl();

        nameText.setText("Name:"+" "+name);
        emailText.setText("Email:"+" "+email);
        mobileText.setText("Mobile No:"+" "+mobileno);
        Picasso.with(getApplicationContext()).load(url).into(image);
        Toast.makeText(getApplicationContext(),url.toString(),Toast.LENGTH_LONG).show();
    }
}
