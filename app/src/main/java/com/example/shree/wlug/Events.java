package com.example.shree.wlug;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Events extends AppCompatActivity {


    TabLayout tb;
    ViewPager tl;
    Spinner event;
    ArrayAdapter<String> eventType;
    String EventRegister[] = { "Select Your Event","Python+Docker+Shell", "Python"};
    private static final int REQUEST_RESPONSE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

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
            Fragment fm=null;

            if(i==0)
            {
                fm=new Metamorphosis();
            }
            if(i==1)
            {
                fm=new Technotweet();
            }

            return fm;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title="";
            if(position==0)
            {
                title="Metamorphosis";
            }
            if(position==1)
            {
                title="Technotweeet";
            }
            return title;
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.events,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.register)
        {
            startActivity(new Intent(getApplicationContext(),MetaForm.class));
        }
        if(id==R.id.contents)
        {
            showEntry();
            Toast.makeText(getApplicationContext(),"register",Toast.LENGTH_LONG).show();

        }
        if(id==R.id.entries)
        {
            try {
                showEntry();
            }
            catch (Exception ex)
            {
                Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
            }

            //Toast.makeText(getApplicationContext(),"register",Toast.LENGTH_LONG).show();

        }

        return super.onOptionsItemSelected(item);
    }

    public void showEntry()
    {

        //final String[] name = new String[1];
        View view=getLayoutInflater().inflate(R.layout.eventselector,null);
      //  setContentView(view);
        event= (Spinner) view.findViewById(R.id.event);
        eventType=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,EventRegister);
        eventType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        event.setAdapter(eventType);
        AlertDialog.Builder builder=new AlertDialog.Builder(Events.this);
        AlertDialog dialog=builder.create();
        dialog.setView(view);
        dialog.show();

       // Toast.makeText(getApplicationContext(), name[0],Toast.LENGTH_LONG).show();
        event.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), name,Toast.LENGTH_LONG).show();
                if(name=="Python+Docker+Shell")
                {
                   Intent intent=new Intent(Events.this,CompletePackageEntry.class);
                   intent.putExtra("event",name);
                   startActivity(intent);
                }
                if(name=="Python")
                {
                    Intent intent=new Intent(Events.this,PythonEntry.class);
                    intent.putExtra("event",name);
                    startActivityForResult(intent,REQUEST_RESPONSE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}