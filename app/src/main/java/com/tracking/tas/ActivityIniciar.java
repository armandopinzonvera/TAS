package com.tracking.tas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tracking.tas.BBDD.ConexionSQLi;
import com.tracking.tas.BBDD.Utilidades;

public class ActivityIniciar extends AppCompatActivity {

    TextView nombre_muestreo, id_transecto, grupo_organismos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        nombre_muestreo = (TextView) findViewById(R.id.et_nombre_muestreo);
        id_transecto = (TextView) findViewById(R.id.et_id_transecto);
        grupo_organismos = (TextView) findViewById(R.id.et_grupo_organismo);
    }
    public void volver(View view){
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
    public void onClickIniciar(View view){
        iniciar();
    }

    public void iniciar(){
        ConexionSQLi conexion = new ConexionSQLi(this, Utilidades.BBDD_TAS, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.NOMBRE_MUESTREO, nombre_muestreo.getText().toString());
        values.put(Utilidades.ID_TRANSECTO, id_transecto.getText().toString());
        values.put(Utilidades.GRUPO_ORGANISMOS, grupo_organismos.getText().toString());

        Long idResultante = db.insert(Utilidades.BBDD_TAS, Utilidades.ID, values);
        Toast.makeText(getApplicationContext(),"numero de registro = "+idResultante, Toast.LENGTH_LONG).show();
        nombre_muestreo.setText("");
        id_transecto.setText("");
        grupo_organismos.setText("");


        Intent intent2 = new Intent(this, ActivityMapa.class);
        Intent intent3 = new Intent(this, Ubicaxion.class);
        startActivity(intent2);
        startActivity(intent3);
    }

}
