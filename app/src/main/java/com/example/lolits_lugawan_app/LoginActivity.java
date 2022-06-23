package com.example.lolits_lugawan_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import models.ModelCallback;
import models.User;


public class LoginActivity extends AppCompatActivity {


    EditText emailTextBox;
    EditText passwordTextBox;
    Button signInBtn;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.emailTextBox = (EditText) findViewById(R.id.emailTextBox);
        this.passwordTextBox = (EditText) findViewById(R.id.passwordTextBox);
        this.signInBtn = (Button) findViewById(R.id.signInButton);
        this.user = new User();

        signInBtn.setOnClickListener(view -> {
            String email = emailTextBox.getText().toString();
            String password = passwordTextBox.getText().toString();
            signIn(email, password);
        });
    }
    protected void signIn(String email, String password){

          this.user.getUserByEmail(email, new ModelCallback() {
                @Override
                public void getOne(QueryDocumentSnapshot user) {
                    if(user != null){
                        String dbPassword = user.getData().get("password").toString();
                        String enteredPassword = passwordTextBox.getText().toString();
                        if(dbPassword.equals(enteredPassword)){
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_LONG).show();
                    }

                }
            });


    }



}