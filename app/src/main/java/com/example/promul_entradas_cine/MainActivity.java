package com.example.promul_entradas_cine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String EXTRA_ENTRADA = "com.example.forumlariocine.MainActivity.extra_entrada";
    EditText edt_nombre;
    EditText edt_cantidad;
    ImageView img_persona;
    Spinner sp_pelicula;
    String tipo_usuario;
    String pelicula;

    //PARA EL CALENDARIO SELECTOR DE FECHA Y HORA, VAMOS A USAR UNA CLASE NUEVA QUE ES TIPO FRAGMENT
    //PARA CREAR EL FRAGMENT, VAMOS AL BOTÓN DERECHO CARPETA DEL PROYECTO -> NEW -> FRAGMENT -> FRAGMENT BLANK.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_nombre = (EditText) findViewById(R.id.edt_nombre);
        edt_cantidad = (EditText) findViewById(R.id.edt_cantidad);
        img_persona = (ImageView) findViewById(R.id.img_persona);
        sp_pelicula = (Spinner) findViewById(R.id.sp_pelicula);
        tipo_usuario = "general";
        pelicula = "pelicula1";

        //vamos a hacer el spinner. hay que implementarlo con un adaptador.
        //la lista de películas normalmente viene de una base de datos, pero en nuestro caso es un array-adapter, que vamos a usar para
        //simular un acceso a base de datos
        String[] peliculas = {"pelicula A", "pelicula B", "pelicula C"};
        if (sp_pelicula != null) {
            //he creado layout personalizados para el createFormResource y apra createFromResource, que están en
            //res -> laoyout -> item_lista_peliculas.xml e item_lista_peliculas2.xml respectivamente. Existen por defecto
            // pero hemos hecho perpsonalizados
            //      AQUI LO AHRÍAMOS PARA TRAER STRING DE PELICULAS, VENIDO DE VALUES -> STRING.XML, PERO
            //
            //      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.peliculas, R.layout.item_lista_peliculas /*androidx.appcompat.R.layout.support_simple_spinner_dropdown_item*/);
            //AL FINAL VAMOS A USAR String[] peliculas
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_lista_peliculas, peliculas);//peliculas e un array de películas
            adapter.setDropDownViewResource(R.layout.item_lista_peliculas2/*com.google.android.material.R.layout.support_simple_spinner_dropdown_item*/);
            sp_pelicula.setAdapter(adapter);
            sp_pelicula.setOnItemSelectedListener(this);
        }
    }

    public void elegirTipoPersona(View view) {
        RadioButton rb = (RadioButton) view;
        if (rb.isChecked()) {
            switch (rb.getId()) {
                case R.id.rb_infantil:
                    tipo_usuario = "infantil";
                    break;
                case R.id.rb_adulto:
                    tipo_usuario = "general";
                    break;
                case R.id.rb_jubilado:
                    tipo_usuario = "jubilado";
                    break;
                default:
                    tipo_usuario = "general";
            }
        }
        Toast.makeText(this, "tipo -> " + tipo_usuario, Toast.LENGTH_LONG).show();
    }


    //para los spinner hay que implementar la interfaz AdapterView.OnItemSelectedListener y sobreescribir los siguiente métodos:
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        pelicula = String.valueOf(adapterView.getItemAtPosition(position));
        Toast.makeText(this, "pelicula -> " + pelicula, Toast.LENGTH_LONG).show();
        //si tengo varios spinner, debemos implementar un switch para determinar cual ha sido el que se ha activado
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        pelicula = String.valueOf(adapterView.getItemAtPosition(0));
        Toast.makeText(this, "pelicula -> " + pelicula, Toast.LENGTH_LONG).show();
    }

    public void irAPantalla2(View view) {

        String nombre = String.valueOf(edt_nombre.getText());
        boolean errores = false;
        if (nombre.isEmpty()) {
            edt_nombre.setError("Debes escribir un nombre");
            errores = true;
        }
        String textoCantidad = String.valueOf(edt_cantidad.getText());
        if (textoCantidad.isEmpty()) {
            edt_cantidad.setError("Debes escribir una cantidad");
            errores = true;
        }
        if (errores == true) {
            return;
        }
        int cantidad = Integer.valueOf(textoCantidad);
        String fecha = "19/10/2023";
        String hora = "8:52";
        Entrada entrada = new Entrada(nombre, tipo_usuario, cantidad, pelicula, fecha, hora);

        //mandamos el objeto entrada a la segunda ventana
        Intent intent = new Intent(this, Ventana2Activity.class);
        intent.putExtra(EXTRA_ENTRADA, entrada);
        startActivity(intent);


    }

}