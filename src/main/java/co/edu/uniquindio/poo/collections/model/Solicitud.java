package co.edu.uniquindio.poo.collections.model;

public class Solicitud {

    private final long id;
    private final String pasajero;
    private final long horaSolicitud;

    public Solicitud(long id, String pasajero, long horaSolicitud) {
        this.id = id;
        this.pasajero = pasajero;
        this.horaSolicitud = horaSolicitud;
    }

    public long getId() {
        return id;
    }

    public String getPasajero() {
        return pasajero;
    }

    public long getHoraSolicitud() {
        return horaSolicitud;
    }

    @Override
    public String toString() {
        return "Solicitud{id=" + id + ", pasajero='" + pasajero + "'}";
    }
}
