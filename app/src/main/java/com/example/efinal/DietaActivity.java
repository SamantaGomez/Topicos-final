package com.example.efinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DietaActivity extends AppCompatActivity {

    private Button mButtonRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta);

        mButtonRegresar=(Button) findViewById(R.id.btnRegresar);
        mButtonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), InicioActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}