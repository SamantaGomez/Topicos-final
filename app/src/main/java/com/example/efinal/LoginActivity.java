package com.example.efinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonIniciar;

    //VARIABLES A REGISTRAR
    private String email = "";
    private String password = "";

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEditTextEmail=(EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword=(EditText) findViewById(R.id.editTextPassword);
        mButtonIniciar=(Button) findViewById(R.id.btnIniciar);

        mButtonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent (LoginActivity.this, InicioActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesión, revise sus datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}