package com.example.gal.shotafim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity  {
    //Buttons
    private Button signInBtn;
    private Button signUpBtn;
    //TextField
    private EditText usrNameTxt;
    private EditText passTxt;
    //Strings
    private String usrNameTxtString;
    private String passTxtString;
    private FirebaseAuth mAuth;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        signInBtn=findViewById(R.id.signInBtn);
        signUpBtn=findViewById(R.id.signUpBtn);

        usrNameTxt = findViewById(R.id.usrNameTxt);
        passTxt = findViewById(R.id.passTxt);

        usrNameTxtString = usrNameTxt.toString();
        passTxtString = passTxt.toString();

        //On Click sign in Button
//        signInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //On click sign up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
    public void switchToRegister(){
        Intent i = new Intent("com.example.gal.shotafim.RegisterActivity");
        startActivity(i);
    }
}
