package com.example.shree.wlug;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    TextView discussion;
    TextView member;
    TextView mentor,memberBoard,mainBoard;
    TextView send;
    TextView blog;
    TextView notice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        mentor= (TextView) findViewById(R.id.mentorboard);
        send= (TextView) findViewById(R.id.send);
        memberBoard= (TextView) findViewById(R.id.memberboard);
        mainBoard= (TextView) findViewById(R.id.mainboard);
        blog = (TextView) findViewById(R.id.blog);
        notice = (TextView) findViewById(R.id.notice);
        blog.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),com.example.shree.wlug.BlogMainActivity.class));
            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NoticeMainActivity.class));
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        discussion= (TextView) findViewById(R.id.disussion);
        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),com.example.shree.wlug.DiscussionForum.class));
            }
        });
        member= (TextView) findViewById(R.id.member);
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
                View mview=getLayoutInflater().inflate(R.layout.board,null);
                AlertDialog dialog=builder.create();
                dialog.setView(mview);
                dialog.show();
            }
        });
        mAuth=FirebaseAuth.getInstance();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FirebaseUser user=mAuth.getCurrentUser();
        //Toast.makeText(getApplicationContext(),""+user.getPhotoUrl(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
            AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("WLUG");
            builder.setMessage("Are you sure to exit?");
            builder.setIcon(R.drawable.logo1);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert=builder.create();
            alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),Settings.class));
            return true;
        }

        if(id==R.id.signout)
        {
            HomeActivity.this.finish();
             mAuth.signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        if(id==R.id.myprofile)
        {
            startActivity(new Intent(getApplicationContext(),MyProfile.class));
        }

        if(id==R.id.exit)
        {

            AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("WLUG");
            builder.setMessage("Are you sure to exit?");
            builder.setIcon(R.drawable.logo1);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finishAffinity();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert=builder.create();
            alert.show();
        }

        if(id==R.id.contactus)
        {
            startActivity(new Intent(HomeActivity.this,ContactUs.class  ));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_aboutwlug) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(HomeActivity.this,Gallery.class));

        } else if (id == R.id.nav_clubservices) {
            startActivity(new Intent(HomeActivity.this,ClubServices.class));

        } else if (id == R.id.nav_visitwebsite) {

            startActivity(new Intent(HomeActivity.this,VisitWebsite.class));

        }
        else if (id == R.id.nav_aboutdeveloper)
        {
            startActivity(new Intent(HomeActivity.this,AboutDevelopers.class));
        }
        else if(id==R.id.nav_event)
        {
            startActivity(new Intent(this,Events.class));
        }
        else if (id == R.id.nav_share) {



            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/html");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
            startActivity(Intent.createChooser(sharingIntent,"Share using"));


        } else if (id == R.id.nav_feedback) {
            startActivity(new Intent(HomeActivity.this,Feedback.class));
        }
        else if(id==R.id.nav_video_gallery){
            startActivity(new Intent(this,VideoLibrary.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void viewMainBoard(View view)
    {
          startActivity(new Intent(getApplicationContext(),MainBoard.class));
    }

    public void viewMentorBoard(View view)
    {
        startActivity(new Intent(getApplicationContext(),MentorBoard.class));
    }
}
