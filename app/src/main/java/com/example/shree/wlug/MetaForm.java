package com.example.shree.wlug;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MetaForm extends AppCompatActivity {

    Spinner spinner;
    Button submit;
    Spinner college;
    TextInputLayout ferror,lerror,error,moberror,amouerror;
    String EventRegister[] = { "Python+Docker+Shell", "Python"};
    ArrayAdapter<String> eventType;
    String CollegeName[] = {"AMGOI","ADCES","GPKP","KIT","WLINGDN","DKTE","RIT","GCOEK","DYPK","BVKOP","JJMCOE","DOT","SIT","WCE","S.Bhokare","GPM","SGI"};
    ArrayAdapter<String> collegeType;
    RadioGroup group;
    RadioButton male,female;
    EditText firstname,lastname,emailid,mobno,amount;
    //FirebaseAuth mauth;
    //DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_meta_form);
           // setContentView(R.layout.eventselector);
            ferror= (TextInputLayout) findViewById(R.id.first);
            lerror= (TextInputLayout) findViewById(R.id.last);
            error= (TextInputLayout) findViewById(R.id.mail);
            moberror= (TextInputLayout) findViewById(R.id.mob);
            amouerror= (TextInputLayout) findViewById(R.id.amou);
            spinner = (Spinner) findViewById(R.id.eventRegister);
            college = (Spinner) findViewById(R.id.collegename);
            firstname = (EditText) findViewById(R.id.fname);
            lastname = (EditText) findViewById(R.id.lname);
            emailid = (EditText) findViewById(R.id.mailid);
            mobno = (EditText) findViewById(R.id.mobno);
            group = (RadioGroup) findViewById(R.id.gender);
            male = (RadioButton) findViewById(R.id.male);
            female = (RadioButton) findViewById(R.id.female);
            amount = (EditText) findViewById(R.id.amount);
           // submit= (Button) findViewById(R.id.submit);
            eventType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, EventRegister);
            collegeType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CollegeName);
            eventType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            collegeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(eventType);
            college.setAdapter(collegeType);
      //      mauth = FirebaseAuth.getInstance();
        //    dref = FirebaseDatabase.getInstance().getReference("EventData");
          /*  dref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });*/
            submit = (Button) findViewById(R.id.submit);

      /*  submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname, lname, email, mob, Gender, event, College, Amount;
                fname = firstname.getText().toString();
                lname = lastname.getText().toString();
                email = emailid.getText().toString();
                mob = mobno.getText().toString();
                if (male.isChecked()) {
                    Gender = male.getText().toString();
                } else {
                    Gender = female.getText().toString();
                }
                Amount = amount.getText().toString();
                event = spinner.getSelectedItem().toString();
                College = college.getSelectedItem().toString();
            }

        });*/


      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(final View view) {

              final String fname, lname, email, mob, Gender, event, College, Amount;
              fname = firstname.getText().toString();
              lname = lastname.getText().toString();
              email = emailid.getText().toString();
              mob = mobno.getText().toString();
              if (male.isChecked()) {
                  Gender = male.getText().toString();
              } else {
                  Gender = female.getText().toString();
              }
              Amount = amount.getText().toString().trim();
              event = spinner.getSelectedItem().toString().trim();
              College = college.getSelectedItem().toString().trim();
              int remainingAmount;
              int pAmount=150;
              int Camount=250;
              if(event=="Python")
              {
                  remainingAmount=pAmount-Integer.parseInt(Amount);
              }
              else
              {
                  remainingAmount=Camount-Integer.parseInt(Amount);
              }
              String msg="WLUG Metamorphosis2k17.\n" +
                      "        Congratulations"+fname +  lname+ "\n" +
                      "        You have successfully registered to our Metamorphosis Workshop.\n" +
                      "        Date             :1st & 2nd October 2k17\n" +
                      "        Registration Time:8:30 am\n" +
                      "        Event name       : " +event+"\n"+
                      "        Amount           : "+Amount+"\n" +
                      "        Remaining amount  :"+  remainingAmount+ "\n" +
                      "        Note             :If possible bring your laptop.\n" +
                      "    \n" +
                      "        Thank You!!!";
              SmsManager sms= SmsManager.getDefault();
              sms.sendTextMessage(mob, null,msg, null,null);

              if (fname.isEmpty() && lname.isEmpty() && email.isEmpty() && mob.isEmpty() && Amount.isEmpty()) {

                  Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
              }
                 else if (fname.isEmpty()) {
                      ferror.setError("First name is required");
                  }
                 else if (lname.isEmpty()) {

                      lerror.setError("Last name is required");
                  }
                 else if (mob.isEmpty()) {

                      moberror.setError("Mobile no is required");
                  }
                  else if (Amount.isEmpty()) {

                      amouerror.setError("Amount is required");
                  }
                 else if (email.isEmpty()) {
                      error.setError("Email is required");
                  }
                  else if (Integer.parseInt(Amount)<100) {
                      //Toast.makeText(getApplicationContext(),fname+lname+email+mob+Gender+event+College+Amount,Toast.LENGTH_LONG).show();
                      //dref.push().setValue(new EventData(fname, lname, email, mob, Gender, event, College, Amount));
                      //Toast.makeText(getApplicationContext(), "Successfully Registered!!!", Toast.LENGTH_LONG).show();
                  Snackbar.make(view, "Amount Should be greater than or equal to 100", Snackbar.LENGTH_LONG).show();

              }

               else {

                  StringRequest stringRequest = new StringRequest(Request.Method.POST, EventEntry.eventurl,
                          new Response.Listener<String>() {
                              @Override
                              public void onResponse(String response) {

                                     Snackbar.make(view, response, Snackbar.LENGTH_LONG).show();
                                  //   MetaForm.this.finish();
                                    // startActivity(new Intent(getApplicationContext(),Events.class));
                                //  Intent intent=new Intent(getApplicationContext(),MetaForm.class);
                                  //PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);



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
                          params.put(EventEntry.FirstName, fname);
                          params.put(EventEntry.LastName, lname);
                          params.put(EventEntry.EmailID, email);
                          params.put(EventEntry.MobNo, mob);
                          params.put(EventEntry.Gender, Gender);
                          params.put(EventEntry.EventName, event);
                          params.put(EventEntry.CollegeName, College);
                          params.put(EventEntry.Amount, Amount);
                          return params;
                      }
                  };

                  RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                  requestQueue.add(stringRequest);
              }
          }
          });


}
}
