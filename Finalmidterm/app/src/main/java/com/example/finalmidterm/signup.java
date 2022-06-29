package com.example.finalmidterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    private EditText Fullname;
    private EditText Email;
    private EditText Password;
    private EditText Confirmpassword;
    private Button Register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initComponents();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }


    private void initComponents(){

        Fullname = (EditText) findViewById(R.id.Fullname);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.Password);
        Confirmpassword = (EditText) findViewById(R.id.confirmpassword);

        Register = (Button) findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
    }

    private void registerUser(){

        String fullname = Fullname.getText().toString();
        String email = Email.getText().toString().trim().toLowerCase();
        String password = Password.getText().toString();
        String confirmpassword = Confirmpassword.getText().toString();

        if(!email.contains("@")){
            Email.setError("Invalid");
            return;
        }

        if(TextUtils.isEmpty(fullname)){
            Fullname.setError("Must be filled");
            return;
        }

        if(TextUtils.isEmpty(email)){
            Email.setError("Must be filled");
            return;
        }

        if(TextUtils.isEmpty(password)){
            Password.setError("Must be filled");
            return;
        }

        if(TextUtils.isEmpty(confirmpassword)){
            Confirmpassword.setError("Must be matching with password");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Successful Registration!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}