package com.example.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.InteraccionConFragment} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    private ArrayList<String> lista_ciudades;//Eso me llega del Activity. En vuestro caso serían las Estaciones, creo.
    private int pais_seleccionado;
    //La pais_seleccionado la pongo como int para facilitar que al cargar el Fragment aparezca ese pais seleccionado en el sipnner

    private InteraccionConFragment oyente;//Este será el vínculo con el Activity. Se inicializa en onAttach, que es del ciclo de vida del fragment

    public BlankFragment() {
        // Required empty public constructor
        //Este tiene que existoir
    }

    public BlankFragment(ArrayList<String> lista_ciudades, int pais_seleccionado) {

        this.lista_ciudades=lista_ciudades;
        this.pais_seleccionado=pais_seleccionado;
    }
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(ArrayList<String> ciudades, int pais_seleccionado) {

        BlankFragment fragment = new BlankFragment(ciudades, pais_seleccionado);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_blank, container, false);
        final Spinner sp=v.findViewById(R.id.spn_paises);
        sp.setSelection(pais_seleccionado);//Hago que en el spinner esté seleccionado el país que deba
        TextView t=v.findViewById(R.id.tv_ciudades);
        /***********Esto es para sacar las ciudades en un TextView, no tiene misterio************/
        String aux="";
        for(String ciudad: lista_ciudades)
        {
            aux+=", "+ciudad;
        }
        t.setText(aux);//Aquí llamarias al AnyGraph
        /****************************************************************/

        //Al clicar el botón, llamo al método seleccionarPais que está implementado en el Activity.
        //Así comunico con el Activity
        Button btn=v.findViewById(R.id.btn_fragment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oyente.seleccionarPais(sp.getSelectedItemPosition());
            }
        });
        return v;
    }



    @Override
    public void onAttach(Context context) {
        //Aquí es donde se inicializa el objeto oyente, que sirve de nexo con el Activity
        super.onAttach(context);
        if (context instanceof InteraccionConFragment) {
            oyente = (InteraccionConFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        oyente = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface InteraccionConFragment {
        // Este método será implementado en el Activity que llama al Fragment
        void seleccionarPais(int pais);
    }
}
