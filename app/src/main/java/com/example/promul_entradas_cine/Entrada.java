package com.example.promul_entradas_cine;

import java.io.Serializable;
import java.util.Objects;

public class Entrada implements Serializable {
    private String nombre;
    private String tipo;

    private int cantidad;
    private String pelicula;


    private String dia;
    private String hora;

    public Entrada() {
        this.nombre = "nombre";
        this.tipo = "tipo";
        this.cantidad = 0;
        this.pelicula = "pelicula";
        this.dia = "dia";
        this.hora = "hora";
    }

    public Entrada(String nombre, String tipo, int cantidad, String pelicula, String dia, String hora) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.pelicula = pelicula;
        this.dia = dia;
        this.hora = hora;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Entrada{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                ", pelicula='" + pelicula + '\'' +
                ", dia='" + dia + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return Objects.equals(nombre, entrada.nombre) && Objects.equals(pelicula, entrada.pelicula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, pelicula);
    }
}
