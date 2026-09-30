package co.edu.uniquindio.poo.collections.benchmark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import co.edu.uniquindio.poo.collections.model.Paciente;
import co.edu.uniquindio.poo.collections.model.Producto;
import co.edu.uniquindio.poo.collections.model.Solicitud;


public class BenchmarkRunner {

    private static final int[] TAMANOS = {100, 1_000, 10_000, 100_000};

    public static void main(String[] args) {
        System.out.println("===================================================");
        System.out.println(" ESCENARIO 1 - Pacientes: buscar por documento");
        System.out.println(" HashMap.get(doc)  vs  ArrayList recorrido lineal");
        System.out.println("===================================================");
        for (int n : TAMANOS) {
            benchmarkPacientes(n);
        }

        System.out.println();
        System.out.println("===================================================");
        System.out.println(" ESCENARIO 2 - Ventas: insertar producto al inicio");
        System.out.println(" LinkedList.addFirst  vs  ArrayList.add(0, x)");
        System.out.println("===================================================");
        for (int n : TAMANOS) {
            benchmarkInsercionInicio(n);
        }

        System.out.println();
        System.out.println("===================================================");
        System.out.println(" ESCENARIO 3 - Taxis: cancelar solicitud por id");
        System.out.println(" LinkedHashMap.remove(id)  vs  LinkedList (buscar+eliminar)");
        System.out.println("===================================================");
        for (int n : TAMANOS) {
            benchmarkCancelarTaxi(n);
        }

        System.out.println();
        System.out.println("===================================================");
        System.out.println(" ESCENARIO 4 - Catálogo: buscar producto por código");
        System.out.println(" HashMap.get(codigo)  vs  ArrayList recorrido lineal");
        System.out.println("===================================================");
        for (int n : TAMANOS) {
            benchmarkCatalogo(n);
        }
    }

    //  Escenario 1 
    private static void benchmarkPacientes(int n) {
        Map<String, Paciente> mapa = new HashMap<>();
        List<Paciente> lista = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Paciente p = new Paciente("DOC" + i, "Paciente " + i, (i % 5) + 1, i);
            mapa.put(p.getDocumento(), p);
            lista.add(p);
        }
        String buscado = "DOC" + (n - 1); 

        long tiempoMapa = Medidor.medirTiempoMs(() -> {
            for (int i = 0; i < 1000; i++) mapa.get(buscado);
        });
        long tiempoLista = Medidor.medirTiempoMs(() -> {
            for (int i = 0; i < 1000; i++) {
                for (Paciente p : lista) {
                    if (p.getDocumento().equals(buscado)) break;
                }
            }
        });

        System.out.printf("n=%-7d | HashMap: %4d ms (1000 búsquedas) | ArrayList: %6d ms (1000 búsquedas)%n",
                n, tiempoMapa, tiempoLista);
    }

    // Escenario 2 
    private static void benchmarkInsercionInicio(int n) {
        long tiempoLinked = Medidor.medirTiempoMs(() -> {
            LinkedList<Producto> lista = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                lista.addFirst(new Producto("P" + i, "Producto " + i, i * 1.5, "cat" + (i % 10)));
            }
        });
        long tiempoArray = Medidor.medirTiempoMs(() -> {
            ArrayList<Producto> lista = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                lista.add(0, new Producto("P" + i, "Producto " + i, i * 1.5, "cat" + (i % 10)));
            }
        });

        System.out.printf("n=%-7d | LinkedList.addFirst: %6d ms | ArrayList.add(0,x): %8d ms%n",
                n, tiempoLinked, tiempoArray);
    }

    // Escenario 3 
    private static void benchmarkCancelarTaxi(int n) {
        Map<Long, Solicitud> mapaOrden = new LinkedHashMap<>();
        LinkedList<Solicitud> lista = new LinkedList<>();
        for (long i = 0; i < n; i++) {
            Solicitud s = new Solicitud(i, "Pasajero " + i, i);
            mapaOrden.put(i, s);
            lista.add(s);
        }
        long idACancelar = n / 2; 

        long tiempoMapa = Medidor.medirTiempoMs(() -> mapaOrden.remove(idACancelar));
        long tiempoLista = Medidor.medirTiempoMs(() -> {
            lista.removeIf(s -> s.getId() == idACancelar);
        });

        System.out.printf("n=%-7d | LinkedHashMap.remove: %4d ms | LinkedList buscar+eliminar: %6d ms%n",
                n, tiempoMapa, tiempoLista);
    }

    //  Escenario 4 
    private static void benchmarkCatalogo(int n) {
        Map<String, Producto> mapa = new HashMap<>();
        List<Producto> lista = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Producto p = new Producto("SKU" + i, "Producto " + i, i * 2.0, "cat" + (i % 10));
            mapa.put(p.getCodigo(), p);
            lista.add(p);
        }
        String buscado = "SKU" + (n - 1);

        long tiempoMapa = Medidor.medirTiempoMs(() -> {
            for (int i = 0; i < 1000; i++) mapa.get(buscado);
        });
        long tiempoLista = Medidor.medirTiempoMs(() -> {
            for (int i = 0; i < 1000; i++) {
                for (Producto p : lista) {
                    if (p.getCodigo().equals(buscado)) break;
                }
            }
        });

        System.out.printf("n=%-7d | HashMap: %4d ms (1000 búsquedas) | ArrayList: %6d ms (1000 búsquedas)%n",
                n, tiempoMapa, tiempoLista);
    }
}
