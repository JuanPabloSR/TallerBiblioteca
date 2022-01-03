package co.com.sofka.biblioteca.Utils;

import java.util.Date;

public class Mensaje {
    private boolean isDisponible;
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(boolean isDisponible, String mensaje) {
        this.isDisponible = isDisponible;
        this.mensaje = mensaje;
    }
    public boolean isDisponible() {
        return isDisponible;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Mensaje imprimirMensajeDisponibilidad(Boolean disponibilidad, Date fechaPrestamo){
        if(disponibilidad){
            return new Mensaje(true, "El recurso esta disponible");
        }
        return new Mensaje(false,"El recurso fue prestado el: " + fechaPrestamo);
    }

    public Mensaje imprimirMensajePrestamo(Boolean disponibilidad, Date fechaPrestamo){
        if(disponibilidad){
            return new Mensaje(true, "El recurso esta disponible");
        }
        return new Mensaje(false, "El recurso no esta disponible, fue prestado el: " + fechaPrestamo);
    }

    public Mensaje imprimirMensajeDevolucion(Boolean disponibilidad, Date fechaPrestamo){
        if(!disponibilidad){
            return new Mensaje(true, "El recurso fue entregado con exito");
        }
        return new Mensaje(false,"El recurso no esta prestado");
    }
}
