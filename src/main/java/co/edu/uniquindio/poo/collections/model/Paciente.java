package co.edu.uniquindio.poo.collections.model;

import java.util.Objects;

public class Paciente {

    private final String documento;
    private final String nombre;
    private final int gravedad; 
    private final long horaLlegada;

    public Paciente(String documento, String nombre, int gravedad, long horaLlegada) {
        this.documento = documento;
        this.nombre = nombre;
        this.gravedad = gravedad;
        this.horaLlegada = horaLlegada;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getGravedad() {
        return gravedad;
    }

    public long getHoraLlegada() {
        return horaLlegada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(documento, paciente.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }

    @Override
    public String toString() {
        return "Paciente{documento='" + documento + "', nombre='" + nombre +
                "', gravedad=" + gravedad + "}";
    }
}
