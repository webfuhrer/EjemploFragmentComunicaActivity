package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BlankFragment.InteraccionConFragment{
    ArrayList<String> ciudades=new ArrayList();
    int pais_seleccionado=0;//La primera vez será España
    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=findViewById(R.id.btn_activity);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //La primera vez, estará seleccionado España y cargo sus ciudades
                ciudades.add("Madrid");
                ciudades.add("Barcelona");
                ciudades.add("Valencia");
                cargarFragment();
            }
        });
    }

    private void cargarFragment() {
        //Un objeto de tipo BlankFragment
        //Le paso 0 como pais_seleccionado, que es España
        BlankFragment b=BlankFragment.newInstance(ciudades, pais_seleccionado);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, b);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void seleccionarPais(int pais) {
        //Desde el Fragment se llama a este método para actualizar las ciudades
        this.pais_seleccionado=pais;
ciudades.clear();
        if (pais==0)
        {
            ciudades.add("Madrid");
            ciudades.add("Barcelona");
            ciudades.add("Valencia");
        }
        else if(pais==2)
        {
            ciudades.add("Roma");
            ciudades.add("Milan");
            ciudades.add("Venecia");
        }
        else if (pais==1)
        {
            ciudades.add("Paris");
            ciudades.add("Marsella");
            ciudades.add("Niza");
        }
cargarFragment();
    }
}
