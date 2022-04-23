package com.example.ciclodevida3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ciclodevida3.Datos.Datos;
import com.example.ciclodevida3.Modelo.VistaModelo;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    EditText tnombre, tedad;
    TextView tmostrar;
    Button btnagregar;

    VistaModelo vistaModelo;
    List<Datos> listadatos = new ArrayList<>();

    private Observer<List<Datos>> observar = new Observer<List<Datos>>() {
        @Override
        public void onChanged(List<Datos> datosList) {
            listadatos = datosList;
            String cadena="";
            for (Datos datos : datosList){
                cadena+= "Nombre: " + datos.getNombre() + " edad:" + String.valueOf(datos.getEdad()) + "\n";
                tmostrar.setText(cadena);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tnombre = findViewById(R.id.txtnombre);
        tedad = findViewById(R.id.txtedad);
        tmostrar = findViewById(R.id.txtcontenedor);
        btnagregar = findViewById(R.id.btnagregar);

        vistaModelo = new ViewModelProvider(this).get(VistaModelo.class);
        vistaModelo.getListaDatos().observe(this,observar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Datos datos = new Datos(tnombre.getText().toString(),Integer.valueOf(tedad.getText().toString()));
                vistaModelo.Agregar(datos);
            }
        });
    }
}