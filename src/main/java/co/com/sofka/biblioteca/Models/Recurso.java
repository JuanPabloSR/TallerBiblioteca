package co.com.sofka.biblioteca.Models;

import co.com.sofka.biblioteca.Utils.AreaTematica;
import co.com.sofka.biblioteca.Utils.Tipo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Recurso")
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private boolean isDisponible = true;
    private Date fechaPrestamo;
    private AreaTematica areaTematica;
    private Tipo tipo;

    public Recurso() {
    }

    public Recurso(String id, String nombre, AreaTematica areaTematica, Tipo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipo = tipo;
    }

    public Recurso(String nombre, AreaTematica areaTematica, Tipo tipo) {
        this.nombre = nombre;
        this.areaTematica = areaTematica;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public void setDisponible(boolean disponible) {
        isDisponible = disponible;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}