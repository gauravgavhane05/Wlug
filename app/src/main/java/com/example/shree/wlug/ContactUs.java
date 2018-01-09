package com.example.shree.wlug;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.Manifest.permission;
public class ContactUs extends AppCompatActivity {

        TextView mobile;
        String sNum;
        TextView email;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_contact_us);
            mobile=(TextView)findViewById(R.id.mobileno);
            email=(TextView)findViewById(R.id.email);
        }

        public void launchMap(View view)
        {
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("geo:16.85846935,74.601281"));
            Intent chooser=Intent.createChooser(i, "Launch Map");
            startActivity(chooser);
        }
        public void mobileClick(View view)
        {
            Intent i=new Intent(Intent.ACTION_CALL);
            sNum = "9021526132";
            if(sNum.trim().isEmpty())
            {
                i.setData(Uri.parse("tel:8275592198"));
            }
            else
            {
                i.setData(Uri.parse("tel:"+sNum));
            }
            if(ActivityCompat.checkSelfPermission(getApplicationContext(), permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermission();
            }
            else
            {
                startActivity(i);
            }

        }

        public void requestPermission()
        {
            ActivityCompat.requestPermissions(this,new String[]{permission.CALL_PHONE},1);
        }

        public void getMail(View view)
        {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setData(Uri.parse("email"));
            String[] s={"wce.wlugclub@gmail.com"};
            i.putExtra(Intent.EXTRA_EMAIL,s);
            i.putExtra(Intent.EXTRA_SUBJECT,"WLUG Team");
            i.setType("message/rfc822");
            Intent chooser= Intent.createChooser(i,"Launch Email");
            startActivity(chooser);
        }

        public void openBrowser(View view)
        {
            Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://wcewlug.org"));
            Intent chooser=Intent.createChooser(intent,"Launch Browser");
            startActivity(chooser);
        }
}
