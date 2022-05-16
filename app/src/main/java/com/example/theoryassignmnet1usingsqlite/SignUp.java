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

public class SignUp extends AppCompatActivity {
    EditText userName,email,password;
    Button signupBtn;
    TextView forLogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName=findViewById(R.id.userName);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        forLogin=findViewById(R.id.createText);
        signupBtn=findViewById(R.id.signupBtn);
        DB=new DBHelper(this);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
                if (TextUtils.isEmpty(Email)) {
                    email.setError("EMAIL MISSING");
                    return;

                }
                if (TextUtils.isEmpty(Password)) {
                    password.setError("PASSWORD MISSING");
                    return;

                }
                Boolean checkEmail = DB.checkemail(Email);
                if (checkEmail == false) {
                    Boolean insert = DB.insertData(Email, Password);
                    if (insert == true) {
                        Toast.makeText(SignUp.this, "SIGNED UP SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), AfterSignUp.class));
                    } else {
                        Toast.makeText(SignUp.this, "FAILED TO SIGNED UP", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUp.this, "Account is already exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });
    }
}

