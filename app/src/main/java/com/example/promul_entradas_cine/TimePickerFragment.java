package com.example.promul_entradas_cine;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

//VAMOS A AHCER LO MISMO QUE CON EL CALENDARIO DE LA FECHA, PERO AHORA CON LA HORA.
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        //-----
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minuto = c.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hora, minuto, true);
        return tpd;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Ventana2Activity ventana2Activity = (Ventana2Activity) getActivity();
        ventana2Activity.crearHora(hourOfDay, minute);
    }
}