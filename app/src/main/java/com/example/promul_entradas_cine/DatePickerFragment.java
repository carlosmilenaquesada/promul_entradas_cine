package com.example.promul_entradas_cine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    //PARA CREAR EL FRAGMENT, VAMOS AL BOTÓN DERECHO CARPETA DEL PROYECTO -> NEW -> FRAGMENT -> FRAGMENT BLANK.
    //VAMOS A USARLO PARA EL CALENDARIO

    public DatePickerFragment() {


    }

    //1º Heredamos de DialogFragment y sobreescribimos el siguiente método onCreateDialog
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        //creamos un datepickerdialog que el calendario físico. le damos por defecto lo que hemos optendio de c.
        DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, anio, mes, dia);
        return dpd;
    }

    //2º ahora implementamos DatePickerDialog.OnDateSetListener y sosbreescribimso el siguiente método onDateSet
    //este método recibe los parámetros de lo decalrado en el onCreateDialog automáticamente
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //llevamos los datos a la ventana del activity2, que es donde necesitamos los datos
        Ventana2Activity ventana2Activity = (Ventana2Activity) getActivity();
        ventana2Activity.crearFecha(year, month, dayOfMonth);//aquí le mandamo los datos con este método que se crea en la Ventana2Activity
    }
}