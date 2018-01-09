package com.example.shree.wlug;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DiscussionForum extends AppCompatActivity {

    TabLayout tb;
    ViewPager tl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_forum);
        tb= (TabLayout) findViewById(R.id.tl);
        tl= (ViewPager) findViewById(R.id.vp);
        MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
        tl.setAdapter(adapter);

        tb.setupWithViewPager(tl);
    }

    class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);

        }


        @Override
        public Fragment getItem(int i) {
            Fragment f=null;
            if(i==0)
            {
                f=new Category();
            }
            if(i==1)
            {
                f=new Discussion();
            }
            if(i==2)
            {
                f=new ClubServiceDiscussion();
            }
            return f;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String name=null;
            if(position==0)
            {
                name="Recent Discussion";
            }
            if(position==1)
            {
                name="Technical Discussion";
            }
            if(position==2)
            {
                name="Club Service Discussion";
            }

            return name;
        }
    }
}
