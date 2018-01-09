package com.example.shree.wlug;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfilePicture extends AppCompatActivity {

    Button choose,upload;
    ImageView imageView;
    FirebaseStorage storage;
    StorageReference sref;
    Bitmap bitmap;
    Uri uri;
    public static  final int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);

        choose = (Button) findViewById(R.id.chooseimage);
        upload =(Button) findViewById(R.id.uploadimage);
        imageView =(ImageView) findViewById(R.id.imageView);

        storage = FirebaseStorage.getInstance();
        sref=storage.getReference();
    }

    /*public String getStringImage(Bitmap bmp)
    {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageBytes=baos.toByteArray();
        String encodeImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encodeImage;
    }*/

    public void chooseImage(View view)
    {
        Intent intent =new Intent(Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        Intent chooser = Intent.createChooser(intent,"Gallery");
        startActivityForResult(chooser,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data)
    {
        super.onActivityResult(requestcode,resultcode,data);
        uri=data.getData();
        if(requestcode==REQUEST_CODE && resultcode==RESULT_OK && data!=null && data.getData()!=null)
        {
            try
            {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public void uploadImage(View view)
    {
        StorageReference iref = sref.child("WLUG").child(uri.getLastPathSegment());
          //String image = getStringImage(bitmap);
        iref.putFile(uri);
        Toast.makeText(getApplicationContext(),"Uploaded Successfully",Toast.LENGTH_LONG).show();
    }
}
