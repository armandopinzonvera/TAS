package com.tracking.tas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tracking.tas.Fragments.FragMainlogo;
import com.tracking.tas.Fragments.FragMainlp1;
import com.tracking.tas.Fragments.FragMainlp2;

public class MainActivity extends AppCompatActivity {

    FragMainlogo fragMainlogo;
    FragMainlp1 fragMainlp1;
    FragMainlp2 fragMainlp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragMainlogo = new FragMainlogo();
        fragMainlp1 = new FragMainlp1();
        fragMainlp2 = new FragMainlp2();

        getSupportFragmentManager().beginTransaction().add(R.id.LayoutFragmentMain, fragMainlogo).commit();
    }
    public void irIniciar(View view){
        Intent inten1 = new Intent(this, ActivityIniciar.class);
        startActivity(inten1);
    }

    public void onClick(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch(view.getId()){
            case R.id.bt2_main:
                transaction.replace(R.id.LayoutFragmentMain, fragMainlp1);
                break;
            case R.id.bt3_main:
                transaction.replace(R.id.LayoutFragmentMain, fragMainlp2);
                break;
        }transaction.commit();

    }

}
