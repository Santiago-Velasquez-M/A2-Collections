package co.edu.uniquindio.poo.collections.services;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import co.edu.uniquindio.poo.collections.model.Paciente;

public class GestorPacientes {

    private final Map<String, Paciente> porDocumento = new HashMap<>();
    private final Queue<Paciente> ordenLlegada = new LinkedList<>();
    private final PriorityQueue<Paciente> porGravedad =
            new PriorityQueue<>(Comparator.comparingInt(Paciente::getGravedad).reversed());

    public boolean registrar(Paciente paciente) {
        if (porDocumento.containsKey(paciente.getDocumento())) {
            return false;
        }
        porDocumento.put(paciente.getDocumento(), paciente);
        ordenLlegada.add(paciente);
        porGravedad.add(paciente);
        return true;
    }

  
    public Paciente buscarPorDocumento(String documento) {
        return porDocumento.get(documento);
    }

  
    public Paciente atenderPorOrdenLlegada() {
        Paciente paciente = ordenLlegada.poll();
        if (paciente != null) {
            porDocumento.remove(paciente.getDocumento());
            porGravedad.remove(paciente);
        }
        return paciente;
    }

    public Paciente atenderMasGrave() {
        Paciente paciente = porGravedad.poll();
        if (paciente != null) {
            porDocumento.remove(paciente.getDocumento());
            ordenLlegada.remove(paciente);
        }
        return paciente;
    }

    public int totalRegistrados() {
        return porDocumento.size();
    }
}
