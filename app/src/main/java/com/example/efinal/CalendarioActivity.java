package com.example.efinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;

public class CalendarioActivity extends AppCompatActivity {

    Button calcular;
    EditText numero1;
    EditText numero2;
    TextView resultado;

    String snumero1;
    String snumero2;
    String sresultado = "";
    TextView tv;
    private Button mButtonRegresar;
    private Button mButtonList;

    AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calcular = (Button) findViewById(R.id.btnCalcular);
        numero1 = (EditText) findViewById(R.id.edtxtNumero1);
        numero2 = (EditText) findViewById(R.id.edtxtNumero2);
        resultado = (TextView) findViewById(R.id.txtResultado);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snumero1 = numero1.getText().toString();
                snumero2 = numero2.getText().toString();
                sresultado = "";
                if(snumero1.length() == 0 ){
                    dialog = new AlertDialog.Builder(CalendarioActivity.this);
                    dialog.setTitle("Error");
                    dialog.setMessage("Ingresar el primer número");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo, int id) {
                            dialogo.cancel();
                            numero1.requestFocus();
                        }
                    });
                    dialog.show();
                }else{
                    if(snumero2.length() == 0){
                        dialog = new AlertDialog.Builder(CalendarioActivity.this);
                        dialog.setTitle("Error");
                        dialog.setMessage("Ingresar el segundo número");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo, int id) {
                                dialogo.cancel();
                                numero1.requestFocus();
                            }
                        });
                        dialog.show();
                    }else {
                        if(Float.parseFloat(snumero1) == 0){
                            sresultado += snumero2 + " / " + snumero1 + " = (División entre cero) \n";
                        }else{
                            DecimalFormat formato1 = new DecimalFormat("#.00");
                            double division = Float.parseFloat(snumero2) / (Float.parseFloat(snumero1) * Float.parseFloat(snumero1));
                            if (division % 1 == 0) { //Si es entero pero termina en .0
                                sresultado += snumero2 + " / " + snumero1 + " = " + (int) division + "\n";
                            } else{
                                sresultado += snumero2 + " / " + snumero1 + " = " + division + "\n";
                            }
                        }
                        resultado.setText(sresultado);
                        calcular.setEnabled(false);
                    }
                }
            }
        });


        tv = findViewById(R.id.textView);
        mButtonRegresar=(Button) findViewById(R.id.btnRegresar);
        mButtonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), InicioActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        mButtonList=(Button) findViewById(R.id.btnList);
        mButtonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(),ListaActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

}