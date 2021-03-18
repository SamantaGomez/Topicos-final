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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButtonRegister;
    private Button mButtonInicio;

    //VARIABLES A REGISTRAR
    private String name = "";
    private String email = "";
    private String password = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mEditTextName=(EditText) findViewById(R.id.editTextName);
        mEditTextEmail=(EditText) findViewById(R.id.editTextEmail);
        mEditTextPassword=(EditText) findViewById(R.id.editTextPassword);
        mButtonRegister=(Button) findViewById(R.id.btnRegister);
        mButtonInicio=(Button) findViewById(R.id.btnInicio);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEditTextName.getText().toString();
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    if (password.length() >= 6){
                        registerUser();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email", email);
                    map.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map);

                    Intent intent= new Intent (MainActivity.this, InicioActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(MainActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}