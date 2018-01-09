package com.example.shree.wlug;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SampleDiscussionForum extends AppCompatActivity {


    FirebaseAuth mAuth;
    final static  int SIGN_IN_REQUEST_CODE=1;
    FloatingActionButton fab;
    EditText input;
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_discussion_forum);

        fab= (FloatingActionButton) findViewById(R.id.fab);
        input= (EditText) findViewById(R.id.input);
        mAuth = FirebaseAuth.getInstance();
        mref=FirebaseDatabase.getInstance().getReference("TechnicalDiscussion");

        if (mAuth.getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(getApplicationContext(),
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();


            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mref.push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getDisplayName())
                            );

                    // Clear the input
                    input.setText("");
                }
            });
            // Load chat room contents
            displayChatMessages();


        }
    }




    private void displayChatMessages() {

        ListView listView= (ListView) findViewById(R.id.list_of_messages);
        FirebaseListAdapter firebaseListAdapter=new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.technicaldiscussion,mref) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };
        listView.setAdapter(firebaseListAdapter);
    }
}
