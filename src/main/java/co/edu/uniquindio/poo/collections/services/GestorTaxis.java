package co.edu.uniquindio.poo.collections.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import co.edu.uniquindio.poo.collections.model.Solicitud;

public class GestorTaxis {

    private final Map<Long, Solicitud> solicitudes = new LinkedHashMap<>();

    public void registrarSolicitud(Solicitud solicitud) {
        solicitudes.put(solicitud.getId(), solicitud);
    }


    public Solicitud atenderMasAntigua() {
        Iterator<Map.Entry<Long, Solicitud>> it = solicitudes.entrySet().iterator();
        if (!it.hasNext()) {
            return null;
        }
        Solicitud solicitud = it.next().getValue();
        it.remove();
        return solicitud;
    }

  
    public boolean cancelarSolicitud(long id) {
        return solicitudes.remove(id) != null;
    }

    public List<Solicitud> listarPendientes() {
        return new ArrayList<>(solicitudes.values());
    }

    public int totalPendientes() {
        return solicitudes.size();
    }
}
