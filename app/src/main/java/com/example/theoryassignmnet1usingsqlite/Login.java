package com.example.theoryassignmnet1usingsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    EditText email;
    EditText password;
    Button loginBtn;
    TextView forSignUp;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        loginBtn=findViewById(R.id.loginBtn);
        forSignUp=findViewById(R.id.createText);
        DB=new DBHelper(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                if(TextUtils.isEmpty(Email)){
                    email.setError("EMAIL MISSING");
                    return;

                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("PASSWORD MISSING");
                    return;

                }
                Boolean checkEmailPassword=DB.checkemailpassword(Email,Password);
                if (checkEmailPassword==true){
                    Toast.makeText(Login.this, "YOU HAVE BEEN LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AfterLogin.class));
                }else{
                    Toast.makeText(Login.this, "FAILED TO LOGIN", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
    }
}