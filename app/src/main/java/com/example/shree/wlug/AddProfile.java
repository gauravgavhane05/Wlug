package com.example.shree.wlug;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProfile extends AppCompatActivity {

    EditText firstname;
    EditText lastname;
    EditText address;
    EditText mobileno;
    TextInputLayout errorFirstName;
    TextInputLayout errorLastName;
    TextInputLayout errorAddress;
    TextInputLayout errorMobileno;
    Button addProfile;
    String firstName,lastName,add,mobileNo;
    FirebaseAuth mAuth;
    DatabaseReference mref;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        firstname = (EditText)findViewById(R.id.firstName);
        lastname = (EditText) findViewById(R.id.lastname);
        address =(EditText) findViewById(R.id.address);
        mobileno =(EditText)findViewById(R.id.mobileno);
        mAuth=FirebaseAuth.getInstance();
        addProfile =(Button) findViewById(R.id.changepassword);
        errorFirstName =(TextInputLayout) findViewById(R.id.errorFirstName);
        errorLastName =(TextInputLayout) findViewById(R.id.errorLastName);
        errorAddress =(TextInputLayout) findViewById(R.id.errorAddress);
        errorMobileno =(TextInputLayout) findViewById(R.id.errorMobileNo);
        user = mAuth.getCurrentUser();
        mref= FirebaseDatabase.getInstance().getReference("WlugUsersInfo");
    }

    public void addProfile(View view)
    {
        firstName = firstname.getText().toString();
        lastName =lastname.getText().toString();
        add = address.getText().toString();
        mobileNo =mobileno.getText().toString();

        if( firstName.isEmpty() || lastName.isEmpty() || add.isEmpty() || mobileNo.isEmpty())
        {
            if(firstName.isEmpty())
            {
                errorFirstName.setError("Fill Your First Name");
            }
            if(lastName.isEmpty())
            {
                errorLastName.setError("Fill Your Last Name");
            }
            if(add.isEmpty())
            {
                errorAddress.setError("Fill Your Address");
            }
            if(mobileNo.isEmpty())
            {
                errorMobileno.setError("Fill Your Mobile No");
            }
        }
        else
        {
                    mref.child(user.getUid()).child("First Name").setValue(firstName);
                    mref.child(user.getUid()).child("Last Name").setValue(lastName);
                    mref.child(user.getUid()).child("Address").setValue(add);
                    mref.child(user.getUid()).child("Mobile No").setValue(mobileNo);
                    Toast.makeText(getApplicationContext(),"Your data has been inserted succesfully",Toast.LENGTH_SHORT).show();


        }
    }
}
