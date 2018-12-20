package com.example.gal.shotafim;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signInBtn=findViewById(R.id.signInBtn);
        signUpBtn=findViewById(R.id.signUpLoginBtn);

        usrNameTxt = findViewById(R.id.usrNameTxt);
        passTxt = (EditText)findViewById(R.id.passTxt);

        usrNameTxtString = usrNameTxt.getText().toString();
        passTxtString = passTxt.getText().toString();

        //On Click sign in Button
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrNameTxtString = usrNameTxt.getText().toString();
                passTxtString = passTxt.getText().toString();
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference();

                myRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        boolean loginIsOk = false;
                        User user;
                        if(dataSnapshot.child("user154,gmail,com").exists()){
                            user = (User)dataSnapshot.getValue(User.class);
                            Log.d("Motek",""+dataSnapshot.getValue().toString());
//                            Log.d("Motek",""+user.toString());
                            Log.d("Motek",""+passTxtString);
                            Log.d("Motek",""+user.getPassword());
                            if(passTxtString.toString().equals(user.getPassword())){
                                Log.d("Motek","Third");
                                loginIsOk = true;
                            }
                        }
                        if(loginIsOk){
                            Toast.makeText(LoginActivity.this
                            ,"LOGIN GOOD GOOD",(Toast.LENGTH_LONG)).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //On click sign up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });
    }

    public void switchToRegister(){
        Intent i = new Intent("com.example.gal.shotafim.RegisterActivity");
        startActivity(i);
    }
}
