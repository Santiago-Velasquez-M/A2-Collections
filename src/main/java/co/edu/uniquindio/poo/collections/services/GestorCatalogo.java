package co.edu.uniquindio.poo.collections.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import co.edu.uniquindio.poo.collections.model.Producto;


public class GestorCatalogo {

    private final Map<String, Producto> porCodigo = new HashMap<>();
    private final TreeMap<Double, List<Producto>> porPrecio = new TreeMap<>();
    private final List<Producto> catalogoCompleto = new ArrayList<>();

    public boolean agregarProducto(Producto producto) {
        if (porCodigo.containsKey(producto.getCodigo())) {
            return false;
        }
        porCodigo.put(producto.getCodigo(), producto);
        catalogoCompleto.add(producto);
        porPrecio.computeIfAbsent(producto.getPrecio(), k -> new ArrayList<>()).add(producto);
        return true;
    }

    public Producto buscarPorCodigo(String codigo) {
        return porCodigo.get(codigo);
    }

    public List<Producto> listarOrdenadosPorPrecio() {
        List<Producto> resultado = new ArrayList<>();
        porPrecio.values().forEach(resultado::addAll);
        return resultado;
    }

    public int totalProductos() {
        return porCodigo.size();
    }
}
