# A2: Collections — Estructuras de Datos

Proyecto Maven (Java 21) que resuelve el taller **A2: Collections** (Java
Collections Framework), con la misma estructura de paquetes usada en otros
proyectos del curso: `model`, `services` (clases `Gestor...`) y `benchmark`.

## Estructura del proyecto

```
src/main/java/co/edu/uniquindio/poo/collections/
├── Main.java                     # Punto de entrada: demo + benchmarks
├── model/
│   ├── Paciente.java
│   ├── Producto.java
│   └── Solicitud.java
├── services/
│   ├── GestorPacientes.java      # Escenario 1: hospital
│   ├── GestorVentas.java         # Escenario 2: ventas masivas
│   ├── GestorTaxis.java          # Escenario 3: solicitudes de taxi
│   └── GestorCatalogo.java       # Escenario 4: catálogo e-commerce
└── benchmark/
    ├── Medidor.java              # Utilidad para medir tiempo/memoria
    └── BenchmarkRunner.java      # Corre las mediciones de la Fase 4
```

## Cómo ejecutar

Desde IntelliJ: abrir el proyecto Maven y correr `Main.java`.

Desde consola (con Maven instalado):

```
mvn compile
mvn exec:java -Dexec.mainClass="co.edu.uniquindio.poo.collections.Main"
```

`Main` imprime una pequeña demo de cada escenario y luego ejecuta
`BenchmarkRunner`, que mide tiempo real de ejecución con tamaños de datos de
100, 1.000, 10.000 y 100.000 elementos, comparando la estructura elegida
contra una alternativa más simple (ArrayList) para dejar en evidencia la
diferencia de complejidad (O(1) vs O(n)).
