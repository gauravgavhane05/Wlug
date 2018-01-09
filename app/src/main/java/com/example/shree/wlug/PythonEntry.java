package com.example.shree.wlug;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PythonEntry extends AppCompatActivity {

    TextView entry;
    View view;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python_entry);
        entry = (TextView) findViewById(R.id.entry);

                showPythonEntry();
        //Intent intent = getIntent();
        //name = intent.getExtras().getString("event");
        //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
        //showPythonEntry();
    }
    public void showPythonEntry()
    {
        Intent intent = getIntent();
        name = intent.getExtras().getString("event");
       // name="Python";
        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, EventEntry.fetchurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Snackbar.make(view, response, Snackbar.LENGTH_LONG).show();
                        entry.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(EventEntry.EventName, name);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
