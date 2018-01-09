package com.example.shree.wlug;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by it on 6/8/17.
 */

public class ExpandedBlog extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_blog);

        Intent intent = getIntent();

        TextView ttitle = (TextView)findViewById(R.id.expanded_title);
        TextView tdesc = (TextView)findViewById(R.id.expanded_desc);

        ttitle.setText(intent.getStringExtra("title"));
        tdesc.setText(intent.getStringExtra("desc"));


    }
}