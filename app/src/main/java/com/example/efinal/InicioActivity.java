package com.example.efinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class InicioActivity extends AppCompatActivity {

    private Button mButtonDieta;
    private Button mButtonCalendario;
    private Button mButtonCerrar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mButtonDieta=(Button) findViewById(R.id.btnDieta);
        mButtonCalendario=(Button) findViewById(R.id.btnCalendario);
        mButtonCerrar=(Button) findViewById(R.id.btnCerrar);
        mAuth = FirebaseAuth.getInstance();

        mButtonDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), DietaActivity.class);
                startActivityForResult(intent, 0);

            }
        });
        mButtonCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), CalendarioActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        mButtonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent (InicioActivity.this, MainActivity.class));
                finish();
            }
        });


    }
}