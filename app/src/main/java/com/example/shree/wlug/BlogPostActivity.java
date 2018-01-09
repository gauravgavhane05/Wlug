package com.example.shree.wlug;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class BlogPostActivity extends AppCompatActivity {


    private ImageButton mSelectButton;
    private Uri mImageUri = null;
    private EditText mPostTitle;
    private EditText mPostDesc;
    private Button mSubmitPost;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;

    private static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);
        FirebaseApp.initializeApp(this);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
        mSelectButton = (ImageButton)findViewById(R.id.ImageSelect);
        mPostTitle = (EditText)findViewById(R.id.TitleField);
        mPostDesc =  (EditText)findViewById(R.id.DescField);
        mSubmitPost =  (Button)findViewById(R.id.SubmitBtn);


        mProgress = new ProgressDialog(this);


        mSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent GalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                GalleryIntent.setType("image/*");
                startActivityForResult(GalleryIntent,GALLERY_REQUEST);

            }
        });

        mSubmitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });


    }

    private void startPosting() {
        mProgress.setMessage("Updating Post.....");
        mProgress.show();
        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(title_val)&&!TextUtils.isEmpty(desc_val)&&mImageUri!=null)
        {
            //DatabaseReference newPost = mDatabase.push();
            //newPost.child("title").setValue(title_val);
            //newPost.child("desc").setValue(desc_val);
            //mProgress.dismiss();
            //Toast.makeText(this,"Title : "+title_val,Toast.LENGTH_LONG).show();

            StorageReference filepath = mStorage.child("Blog_Images").child(mImageUri.getLastPathSegment());
            //Log.d("Storage",mImageUri.toString());
            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloaduri = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("title").setValue(title_val);
                    newPost.child("desc").setValue(desc_val);
                    newPost.child("image").setValue(downloaduri.toString());


                    mProgress.dismiss();
                    finish();
                    /*Intent intent = new Intent(getApplicationContext(), BlogMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);*/

                }
            });

        }
        else
        {
            Toast.makeText(this,"All Fields are Mandatory",Toast.LENGTH_LONG).show();
            mProgress.dismiss();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK)
        {

            mImageUri = data.getData();
            mSelectButton.setImageURI(mImageUri);

        }
    }
}
