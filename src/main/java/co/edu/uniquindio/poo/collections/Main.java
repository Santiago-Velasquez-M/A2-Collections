package co.edu.uniquindio.poo.collections;

import co.edu.uniquindio.poo.collections.benchmark.BenchmarkRunner;
import co.edu.uniquindio.poo.collections.model.Paciente;
import co.edu.uniquindio.poo.collections.model.Producto;
import co.edu.uniquindio.poo.collections.model.Solicitud;
import co.edu.uniquindio.poo.collections.services.GestorCatalogo;
import co.edu.uniquindio.poo.collections.services.GestorPacientes;
import co.edu.uniquindio.poo.collections.services.GestorTaxis;
import co.edu.uniquindio.poo.collections.services.GestorVentas;

public class Main {

    public static void main(String[] args) {
        demoEscenario1();
        demoEscenario2();
        demoEscenario3();
        demoEscenario4();

        System.out.println();
        System.out.println("############ FASE 4: MEDICIONES ############");
        BenchmarkRunner.main(args);
    }

    private static void demoEscenario1() {
        System.out.println("--- Escenario 1: Registro de Pacientes ---");
        GestorPacientes gestor = new GestorPacientes();
        gestor.registrar(new Paciente("1001", "Ana", 2, 1));
        gestor.registrar(new Paciente("1002", "Luis", 5, 2));
        gestor.registrar(new Paciente("1003", "Carla", 1, 3));

        System.out.println("Buscar 1002 -> " + gestor.buscarPorDocumento("1002"));
        System.out.println("Atender más grave -> " + gestor.atenderMasGrave());
        System.out.println("Atender por llegada -> " + gestor.atenderPorOrdenLlegada());
        System.out.println();
    }

    private static void demoEscenario2() {
        System.out.println("--- Escenario 2: Ventas Masivas ---");
        GestorVentas gestor = new GestorVentas();
        gestor.agregarProducto(new Producto("A1", "Camisa", 50000, "ropa"));
        gestor.agregarProducto(new Producto("A2", "Pantalón", 80000, "ropa"));
        gestor.agregarProducto(new Producto("A3", "Mouse", 30000, "tecnologia"));

        System.out.println("Buscar A2 -> " + gestor.buscarPorCodigo("A2"));
        System.out.println("Ordenado por precio -> " + gestor.listarOrdenadosPorPrecio());
        System.out.println("Filtro categoria=ropa -> " + gestor.filtrarPorCategoria("ropa"));
        System.out.println();
    }

    private static void demoEscenario3() {
        System.out.println("--- Escenario 3: Solicitud de Taxis ---");
        GestorTaxis gestor = new GestorTaxis();
        gestor.registrarSolicitud(new Solicitud(1, "Pedro", 100));
        gestor.registrarSolicitud(new Solicitud(2, "Maria", 101));
        gestor.registrarSolicitud(new Solicitud(3, "Jose", 102));

        System.out.println("Cancelar id=2 -> " + gestor.cancelarSolicitud(2));
        System.out.println("Atender más antigua -> " + gestor.atenderMasAntigua());
        System.out.println("Pendientes -> " + gestor.listarPendientes());
        System.out.println();
    }

    private static void demoEscenario4() {
        System.out.println("--- Escenario 4: Catálogo E-commerce ---");
        GestorCatalogo gestor = new GestorCatalogo();
        gestor.agregarProducto(new Producto("SKU1", "Teclado", 90000, "tecnologia"));
        gestor.agregarProducto(new Producto("SKU2", "Silla", 250000, "hogar"));
        gestor.agregarProducto(new Producto("SKU3", "Cable", 15000, "tecnologia"));

        System.out.println("Buscar SKU2 -> " + gestor.buscarPorCodigo("SKU2"));
        System.out.println("Ordenado por precio -> " + gestor.listarOrdenadosPorPrecio());
        System.out.println();
    }
}
