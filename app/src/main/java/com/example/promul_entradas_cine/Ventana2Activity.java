package com.example.promul_entradas_cine;

import static com.example.promul_entradas_cine.MainActivity.EXTRA_ENTRADA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Ventana2Activity extends AppCompatActivity {
    Entrada entrada;
    TextView txt_tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);
        txt_tipo = (TextView) findViewById(R.id.txt_tipo);
        Intent intent = getIntent();
        if (intent != null) {
            //aqui guardamos en la variabla entrada lo traido por el intent, usando getSerializableExtra,
            //ya que la clase entrada implementa Serializable
            entrada = (Entrada) intent.getSerializableExtra(EXTRA_ENTRADA);

        } else {
            entrada = new Entrada();
        }

        txt_tipo.setText(entrada.getTipo());



        Toast.makeText(this, entrada.toString(), Toast.LENGTH_LONG).show();
    }
}