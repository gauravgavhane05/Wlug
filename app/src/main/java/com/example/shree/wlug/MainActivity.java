package com.example.shree.wlug;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {
    TextView register;
    TextView forget;
    AdView adView;
    SignInButton signInButton;
    public static int RC_SIGN_IN = 1;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth mAuth;
    ProgressDialog pdg;
    EditText emailEdit;
    EditText passwordEdit;
    String email,password;
    Button login;
    View view;
    TextInputLayout errorEmail;
    TextInputLayout errorPassword;
     FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (TextView) findViewById(R.id.createnew);
        forget = (TextView) findViewById(R.id.forgetpassword);
        login= (Button) findViewById(R.id.loginId);

        MobileAds.initialize(this,"ca-app-pub-3393340390341566/3155836879");
        adView= (AdView) findViewById(R.id.adView);
        emailEdit = (EditText) findViewById(R.id.email);
        passwordEdit = (EditText) findViewById(R.id.password);
        // default_web_client_id="667763886213-31ish2mk02unsj0ja8g62954kai3f8b4.apps.googleusercontent.com";
        errorEmail= (TextInputLayout) findViewById(R.id.errorEmail);
        errorPassword= (TextInputLayout) findViewById(R.id.errorPassword);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mAuth = FirebaseAuth.getInstance();
        signInButton = (SignInButton) findViewById(R.id.googleSignIn);
        currentUser = mAuth.getCurrentUser();
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();

         mGoogleApiClient=new GoogleApiClient.Builder(this)
          .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
              @Override
              public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

              }
          }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();

        try {
            currentUser = mAuth.getCurrentUser();
            boolean verified = currentUser.isEmailVerified();
            if (!verified) {
                Snackbar.make(view, "Please Verify Your Email", Snackbar.LENGTH_LONG).show();
            } else {
                if (currentUser != null) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));

                } else {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    // Toast.makeText(getApplicationContext(),"Please verify your Email.",Toast.LENGTH_LONG).show();
                }
            }
        }
        catch (Exception e)
        {
            //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
@Override
    public void onBackPressed()
    {
        super.onBackPressed();

    }





    private void signIn() {
        pdg=new ProgressDialog(MainActivity.this);
        pdg.setMessage("Validating");
        pdg.setIndeterminate(true);
        pdg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdg.setCancelable(true);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        pdg.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Toast.makeText(this,""+result.isSuccess(),Toast.LENGTH_LONG).show();
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                       Toast.makeText(getApplicationContext(),"Signin failed",Toast.LENGTH_LONG).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                            pdg.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }
    public void updateUI()
    {
        startActivity(new Intent(this,HomeActivity.class));
    }




    public void loginUser()
    {
        email = emailEdit.getText().toString();
        password = passwordEdit.getText().toString();
        if(email.isEmpty() || password.isEmpty())
        {
            if(email.isEmpty()) {
               // Toast.makeText(MainActivity.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                errorEmail.setError("Email is required.");
            }
            if(password.isEmpty()) {
                //Toast.makeText(MainActivity.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                errorPassword.setError("Password is required.");
            }

        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                boolean verified = user.isEmailVerified();
                                if (verified) {
                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                    Toast.makeText(getApplicationContext(), "Verified", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Please verify your email", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI();
                            }

                            // ...
                        }
                    });
        }
    }

    public void registerUser(View view)
    {
        startActivity(new Intent(this,Register.class));
    }

    public void forgetPassword(View view)
    {

        startActivity(new Intent(this,Forget.class));
    }

}
