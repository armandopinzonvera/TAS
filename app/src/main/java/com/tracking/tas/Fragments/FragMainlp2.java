package com.tracking.tas.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tracking.tas.BBDD.ConexionSQLi;
import com.tracking.tas.BBDD.Entidades;
import com.tracking.tas.BBDD.Utilidades;
import com.tracking.tas.R;

import java.util.ArrayList;


public class FragMainlp2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView_fg2;
    ConexionSQLi conexion;
    ArrayList<String> listaInformacion;
    ArrayList<Entidades> listaProyectos;

    public FragMainlp2() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragMainlp2 newInstance(String param1, String param2) {
        FragMainlp2 fragment = new FragMainlp2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_mainlp2, container, false);
        conexion = new ConexionSQLi(getContext(), Utilidades.BBDD_TAS, null, 1);
        listView_fg2 = (ListView) view.findViewById(R.id.lv1_fragmainlp2);
        consultarListaProyectos();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaInformacion);
        listView_fg2.setAdapter(adapter);

        listView_fg2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Este es el Proyecto: "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    private void consultarListaProyectos() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        Entidades entidades = null;
        listaProyectos = new ArrayList<Entidades>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.BBDD_TAS, null);

        while(cursor.moveToNext()){
            entidades = new Entidades();
            entidades.setNombre_muestreo(cursor.getString(1));
            entidades.setId_transecto(cursor.getString(2));
            listaProyectos.add(entidades);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for(int i = 0; i<listaProyectos.size();i++){
            listaInformacion.add(listaProyectos.get(i).getNombre_muestreo()+" - "+listaProyectos.get(i).getId_transecto());
        }
    }
}
