package com.tracking.tas;
/**
 * Esta clase presenta la informacion para la interfaz grafica
 * de la ubicacion (datos de gps), el mapa, la lista que se va
 * generando en la BBDD, bueno ademas de hacer el envio de esa
 * informacion mediante el boton "agregar"
 *
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tracking.tas.Fragments.FragMapa;

public class ActivityMapa extends AppCompatActivity implements Interface.View{

    private  Interface.Presenter presenter;
    private Interface.Model modelo;


    FragMapa fragMapa = new FragMapa();
    TextView tv_latitud, tv_longitud, tv_altura, tv_distancia,
            tv_tiempo, tv_velocidad, tv_idMuestreo, tv_idTransecto,
            tv_riqueza, tv_densidad;
    EditText et_taxon, et_cantidad;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_mapa, fragMapa).commit();

        tv_latitud = (TextView)findViewById(R.id.tv_latitud);
        tv_longitud = (TextView)findViewById(R.id.tv_longitud);
        tv_altura = (TextView)findViewById(R.id.tv_altura);
        tv_distancia = (TextView)findViewById(R.id.tv_distancia);
        tv_tiempo = (TextView)findViewById(R.id.tv_tiempo);
        tv_velocidad = (TextView)findViewById(R.id.tv_velocidad);
        tv_idMuestreo = (TextView)findViewById(R.id.tv_muestreo);
        tv_idTransecto = (TextView)findViewById(R.id.tv_transecto);
        tv_riqueza = (TextView)findViewById(R.id.tv_riqueza);
        tv_densidad = (TextView)findViewById(R.id.tv_densida);
        et_taxon = (EditText) findViewById(R.id.et_taxon);
        et_cantidad = (EditText)findViewById(R.id.et_cantidad);

        presenter = new Presenter(this);

        Log.v("valor_am calculus: "+latitudV, "results: "+((Object)latitudV).getClass().getSimpleName());

    }
    float latitudV, longitudV;
    @Override
    public void setValuesMapUi(float longitudeP, float latitudeP) {
        this.latitudV = latitudeP;
        this.longitudV = longitudeP;
        tv_latitud.setText(String.valueOf(latitudV));
        tv_longitud.setText(String.valueOf(longitudV));
    }

//////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////
    public void xxShowResult(String result) {

        tv_riqueza.setText(result);
    }

    public void independentSent(View view){
        presenter.xxcalculus(et_cantidad.getText().toString());
    }
}

