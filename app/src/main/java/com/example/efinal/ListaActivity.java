package com.example.efinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.efinal.model.Alimento;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListaActivity extends AppCompatActivity {

    TextView tv;
    EditText fecha, tiempo, proteina, fruta, liquido, guarnicion, otro;
    private Button mButtonRegresar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ListView listV_personas;
    private List<Alimento> listPerson=new ArrayList<Alimento>();
    ArrayAdapter<Alimento> arrayAdapterPersona;
    Alimento personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        fecha=findViewById(R.id.txt_fecha);
        tiempo=findViewById(R.id.txt_tiempo);
        proteina=findViewById(R.id.txt_proteina);
        fruta=findViewById(R.id.txt_fruta);
        liquido=findViewById(R.id.txt_liquido);
        guarnicion=findViewById(R.id.txt_guarnicion);
        otro=findViewById(R.id.txt_otro);

        listV_personas=findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        listarDatos();
        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected=(Alimento) parent.getItemAtPosition(position);
                fecha.setText(personaSelected.getFecha());
                tiempo.setText(personaSelected.getTiempo());
                proteina.setText(personaSelected.getProteina());
                fruta.setText(personaSelected.getFruta());
                liquido.setText(personaSelected.getLiquido());
                guarnicion.setText(personaSelected.getGuarnicion());
                otro.setText(personaSelected.getOtro());
            }
        });

        tv = findViewById(R.id.textView);
        mButtonRegresar=(Button) findViewById(R.id.btnRegresar);
        mButtonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), CalendarioActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
    }
    private void listarDatos() {
        databaseReference.child("Alimento").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listPerson.clear();
                for (DataSnapshot objSnapshot:snapshot.getChildren()){
                    Alimento p= objSnapshot.getValue(Alimento.class);
                    listPerson.add(p);

                    arrayAdapterPersona=new ArrayAdapter<Alimento>(ListaActivity.this, android.R.layout.simple_list_item_1,listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String fech=fecha.getText().toString();
        String tiemp=tiempo.getText().toString();
        String protein=proteina.getText().toString();
        String frut=fruta.getText().toString();
        String liq=liquido.getText().toString();
        String guar=guarnicion.getText().toString();
        String otr=otro.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:{
                if (fech.equals("") || tiemp.equals("")||protein.equals("")||frut.equals("")||liq.equals("")||guar.equals("")||otr.equals("")){
                    valicacion();
                }else{
                    Alimento p=new Alimento();
                    p.setUid(UUID.randomUUID().toString());
                    p.setFecha(fech);
                    p.setTiempo(tiemp);
                    p.setProteina(protein);
                    p.setFruta(frut);
                    p.setLiquido(liq);
                    p.setGuarnicion(guar);
                    p.setOtro(otr);
                    databaseReference.child("Alimento").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregados",Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            }
            case R.id.icon_update:{
                Alimento p=new Alimento();
                p.setUid(personaSelected.getUid());
                p.setFecha(fecha.getText().toString().trim());
                p.setTiempo(tiempo.getText().toString().trim());
                p.setProteina(proteina.getText().toString().trim());
                p.setFruta(fruta.getText().toString().trim());
                p.setLiquido(liquido.getText().toString().trim());
                p.setGuarnicion(guarnicion.getText().toString().trim());
                p.setOtro(otro.getText().toString().trim());
                databaseReference.child("Alimento").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Actualizados",Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            case R.id.icon_delete:{
                Alimento p=new Alimento();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Alimento").child(p.getUid()).removeValue();
                Toast.makeText(this,"Eliminados",Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            }
            default:break;
        }
        return true;
    }

    private void limpiarCajas() {
        fecha.setText("");
        tiempo.setText("");
        proteina.setText("");
        fruta.setText("");
        liquido.setText("");
        guarnicion.setText("");
        otro.setText("");
    }

    private void valicacion(){

        String fech=fecha.getText().toString();
        String tiemp=tiempo.getText().toString();
        String protein=proteina.getText().toString();
        String frut=fruta.getText().toString();
        String liq=liquido.getText().toString();
        String guar=guarnicion.getText().toString();
        String otr=otro.getText().toString();

        if (fech.equals("")){
            fecha.setError("Required");
        }else if (tiemp.equals("")){
            tiempo.setError("Required");
        }else if (protein.equals("")){
            proteina.setError("Required");
        }else if (frut.equals("")){
            fruta.setError("Required");
        }else if (liq.equals("")){
            liquido.setError("Required");
        }else if (guar.equals("")){
        guarnicion.setError("Required");
        }else if (otr.equals("")){
        otro.setError("Required");
    }

    }
}