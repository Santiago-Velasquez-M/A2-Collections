package co.edu.uniquindio.poo.collections.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import co.edu.uniquindio.poo.collections.model.Producto;

public class GestorVentas {

    private final Map<String, Producto> porCodigo = new HashMap<>();
    private final LinkedList<Producto> insertadosAlInicio = new LinkedList<>();
    private final TreeSet<Producto> porPrecio =
            new TreeSet<>(Comparator.comparingDouble(Producto::getPrecio).thenComparing(Producto::getCodigo));
    private final Map<String, List<Producto>> porCategoria = new HashMap<>();

    public boolean agregarProducto(Producto producto) {
        if (porCodigo.containsKey(producto.getCodigo())) {
            return false;
        }
        porCodigo.put(producto.getCodigo(), producto);
        insertadosAlInicio.addFirst(producto); 
        porPrecio.add(producto);
        porCategoria.computeIfAbsent(producto.getCategoria(), k -> new ArrayList<>()).add(producto);
        return true;
    }

    public Producto buscarPorCodigo(String codigo) {
        return porCodigo.get(codigo);
    }

    public List<Producto> listarOrdenadosPorPrecio() {
        return new ArrayList<>(porPrecio);
    }

    public List<Producto> filtrarPorCategoria(String categoria) {
        return porCategoria.getOrDefault(categoria, List.of());
    }

    public int totalProductos() {
        return porCodigo.size();
    }
}
