package co.edu.uniquindio.poo.collections.benchmark;

/**
 * Utilidad simple para medir tiempo de ejecución y memoria aproximada
 */
public class Medidor {

    public static long medirTiempoMs(Runnable accion) {
        long inicio = System.nanoTime();
        accion.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000;
    }

    
    public static long medirMemoriaKB(Runnable accion) {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        long antes = runtime.totalMemory() - runtime.freeMemory();
        accion.run();
        System.gc();
        long despues = runtime.totalMemory() - runtime.freeMemory();
        long usados = despues - antes;
        return Math.max(usados, 0) / 1024;
    }
}
