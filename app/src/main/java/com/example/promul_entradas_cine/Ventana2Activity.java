package com.example.promul_entradas_cine;

import static com.example.promul_entradas_cine.MainActivity.EXTRA_ENTRADA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.TimeZone;

public class Ventana2Activity extends AppCompatActivity {
    private Entrada entrada;
    private TextView txt_tipo;

    private TextView txt_cantidad;
    private TextView txt_precio_entrada;
    private TextView txt_total_pagar;
    private CheckBox ck_condiciones;

    private EditText edt_dia;
    private EditText edt_hora;
    private double precio_general;
    private double precio_infantil;
    private double precio_jubilado;

    private double precio_entrada;

    private double precio_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana2);
        txt_tipo = (TextView) findViewById(R.id.txt_tipo);
        txt_precio_entrada = (TextView) findViewById(R.id.txt_precio_entrada);
        txt_cantidad = (TextView) findViewById(R.id.txt_cantidad);
        txt_total_pagar = (TextView) findViewById(R.id.txt_total_pagar);
        ck_condiciones = (CheckBox) findViewById(R.id.ck_condiciones);
        edt_dia = (EditText) findViewById(R.id.edt_dia);
        edt_hora = (EditText) findViewById(R.id.edt_hora);
        /*para que salga por defecto el día de hoy----------------------*/
        Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        crearFecha(anio, mes, dia);
        /*------------------------------------------------------*/
        /*Para que salga la hora de ahora mismo---------------------*/
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);
        crearHora(hora, minuto);
        /*------------------------*/
        Intent intent = getIntent();
        if (intent != null) {
            //aqui guardamos en la variabla entrada lo traido por el intent, usando getSerializableExtra,
            //ya que la clase entrada implementa Serializable
            entrada = (Entrada) intent.getSerializableExtra(EXTRA_ENTRADA);

        } else {
            entrada = new Entrada();
        }

        txt_tipo.setText(entrada.getTipo());

        //la declaración del tipo de precio debería de venir de una base de datos, pero como no tenemos, lo decalramos como atributos
        precio_general = 3.0;
        precio_infantil = 5.0;
        precio_jubilado = 4.0;
        switch (entrada.getTipo().toLowerCase()) {
            case "infantil":
                precio_entrada = precio_infantil;
                break;
            case "general":
                precio_entrada = precio_general;
                break;
            case "jubilado":
                precio_entrada = precio_jubilado;
                break;
            default:
                precio_entrada = precio_general;
        }
        txt_precio_entrada.setText(String.valueOf(precio_entrada));
        txt_cantidad.setText(String.valueOf(entrada.getCantidad()));
        precio_total = precio_entrada * entrada.getCantidad();
        txt_total_pagar.setText(String.valueOf(precio_total));

        Toast.makeText(this, entrada.toString(), Toast.LENGTH_LONG).show();
    }

    public void ir_a_pago(View view) {
        if (ck_condiciones.isChecked()) {
            Toast.makeText(this, "se está redirigiendo a paypal", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "debe aceptar las condiciones", Toast.LENGTH_LONG).show();
        }

    }

    public void mostrar_calendario(View view) {
        //instanciamos un objeto de la clase    que hemos creado que tenía del calendario,
        DatePickerFragment calendario = new DatePickerFragment();
        //y arhora lo mostramos
        calendario.show(getSupportFragmentManager(), "DatePicker");

        //ahora tenemos que darle al texto que va a mostrar la fecha y que hace de "boton" al
        //calendario la propiedad en xml android:focusableInTouchMode="false", para que no haya
        //que darle dos veces para que muestre el calendario

    }

    public void crearFecha(int year, int month, int dayOfMonth) {
        String textoYear = String.valueOf(year);
        String textoMonth = String.valueOf(month + 1);//el mes hay que añadirle 1
        String textoDay = String.valueOf(dayOfMonth);
        String textFormatoEuropeo = textoDay + "-" + textoMonth + "-" + textoYear;
        edt_dia.setText(textFormatoEuropeo);

    }

    public void mostar_hora(View view){
        TimePickerFragment reloj = new TimePickerFragment();
        reloj.show(getSupportFragmentManager(), "TimePicker");
    }
    public void crearHora(int hourOfDay, int minute) {
        String textoHora = String.valueOf(hourOfDay);
        String textoMinuto = String.valueOf(minute);
        String textoHoraTotal = textoHora + ":" + textoMinuto;
        edt_hora.setText(textoHoraTotal);

    }
}