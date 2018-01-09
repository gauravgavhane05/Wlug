package com.example.shree.wlug;


import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Discussion extends Fragment {

    FirebaseAuth mAuth;
    final static  int SIGN_IN_REQUEST_CODE=1;
    FloatingActionButton fab;
    EditText input;
    View view;
    DatabaseReference mref;
    public Discussion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //view=getView();
        view=inflater.inflate(R.layout.fragment_discussion, container, false);
        mref=FirebaseDatabase.getInstance().getReference("TechnicalDiscussion");
        fab= (FloatingActionButton) view.findViewById(R.id.fab);
        input= (EditText) view.findViewById(R.id.input);
        mAuth=FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser() == null) {
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
            Toast.makeText(getContext(),
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();


            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mref
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getDisplayName())

                            );


                    NotificationCompat.Builder mBuilder=(NotificationCompat.Builder)new NotificationCompat.Builder(getContext())
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentText("WLUG")
                            .setContentText(input.getText().toString());

                    
                    // Clear the input
                    input.setText("");
                }
            });
            // Load chat room contents
            displayChatMessages();
        }


        // Inflate the layout for this fragment
        return view;
    }

    private void displayChatMessages() {

        ListView listView= (ListView) view.findViewById(R.id.list_of_messages);
        FirebaseListAdapter firebaseListAdapter=new FirebaseListAdapter<ChatMessage>(getActivity(),ChatMessage.class,R.layout.technicaldiscussion,mref) {
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
