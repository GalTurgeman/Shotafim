package com.example.gal.shotafim;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    //Button
    private Button signUpBtn;
    //EditText
    private EditText nameTxt;
    private EditText passTxt;
    private EditText emailTxt;
    private EditText aptTxt;
    private EditText aptIDTxt;
    private EditText cityTxt;
    private EditText streetTxt;
    private EditText countryTxt;
    //RadioButtons
    private RadioButton hasAptRadio;
    private RadioButton createAptRadio;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameTxt = findViewById(R.id.nameTxt);
        passTxt = findViewById(R.id.passTxt);
        emailTxt = findViewById(R.id.emailTxt);
        aptTxt = findViewById(R.id.aptTxt);
        aptIDTxt = findViewById(R.id.aptIDTxt);
        cityTxt = findViewById(R.id.cityTxt);
        streetTxt = findViewById(R.id.streetTxt);
        countryTxt = findViewById(R.id.countryTxt);
        hasAptRadio =findViewById(R.id.radioButtonHas);
        createAptRadio = findViewById(R.id.radioButtonCreate);
        signUpBtn = findViewById(R.id.signUpBtn);

        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if( radioGroup.getCheckedRadioButtonId()==hasAptRadio.getId() ){
                    createAptInvisiable();
                }
                else{
                    createAptVisiable();
                }
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickSignUp();
            }
        });
    }
    private void createAptVisiable(){
        aptIDTxt.setVisibility(View.INVISIBLE);
        aptTxt.setVisibility(View.VISIBLE);
        cityTxt.setVisibility(View.VISIBLE);
        streetTxt.setVisibility(View.VISIBLE);
        countryTxt.setVisibility(View.VISIBLE);
    }
    private void createAptInvisiable(){
        aptIDTxt.setVisibility(View.VISIBLE);
        aptTxt.setVisibility(View.INVISIBLE);
        cityTxt.setVisibility(View.INVISIBLE);
        streetTxt.setVisibility(View.INVISIBLE);
        countryTxt.setVisibility(View.INVISIBLE);
    }
    public void OnClickSignUp(){
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final User user = new User(
                Generator.nextSessionId(),
                nameTxt.getText().toString(),
                emailTxt.getText().toString(),
                passTxt.getText().toString());
            if((HasEmptyFields() || radioGroup.getCheckedRadioButtonId()== -1) ){
                Toast.makeText(RegisterActivity.this,"There is empty field,try again!",Toast.LENGTH_LONG).show();
            }
            else{
                db.child("Users").child(user.getEmail().replace(".","|").toLowerCase()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_LONG).show();
                        }
                        else{
                            db.child("Users").child(user.getEmail().replace(".","|").toLowerCase()).setValue(user);
                            Toast.makeText(RegisterActivity.this,"Registration done.",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
    }

    /**
     *  Check if all field is filled.
     * @return True / False , True if there is empty field ,False otherwise.
     */
    private boolean HasEmptyFields(){
         if(radioGroup.getCheckedRadioButtonId() == hasAptRadio.getId()){
             return (aptIDTxt.getText().toString().isEmpty() ||
                     nameTxt.getText().toString().isEmpty() ||
                     emailTxt.getText().toString().isEmpty() ||
                     passTxt.getText().toString().isEmpty());
         }
         else{
             return !(aptTxt.getText().toString().isEmpty() ||
                     streetTxt.getText().toString().isEmpty() ||
                     countryTxt.getText().toString().isEmpty() ||
                     cityTxt.getText().toString().isEmpty() ||
                     nameTxt.getText().toString().isEmpty() ||
                     emailTxt.getText().toString().isEmpty() ||
                     passTxt.getText().toString().isEmpty());
         }
    }
}
