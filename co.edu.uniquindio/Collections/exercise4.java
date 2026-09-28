package Collections;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Representa una tarea con un nombre y una prioridad asociada.
 * Se considera que a MENOR número de prioridad, MAYOR importancia
 * (por ejemplo: 1 = urgente, 5 = baja prioridad).
 * Implementa Comparable para que PriorityQueue sepa cómo ordenarlas.
 */
class Tarea implements Comparable<Tarea> {

    private final String nombre;
    private final int prioridad;

    public Tarea(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Define el orden natural de las tareas según su prioridad.
     * A menor valor numérico, mayor importancia (se procesa primero).
     */
    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tarea)) return false;
        Tarea tarea = (Tarea) obj;
        return prioridad == tarea.prioridad && Objects.equals(nombre, tarea.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, prioridad);
    }

    @Override
    public String toString() {
        return "Collections.Tarea{nombre='" + nombre + "', prioridad=" + prioridad + "}";
    }
}

/**
 * Cola de tareas basada en un PriorityQueue.
 * Las tareas se procesan según su prioridad: la de mayor importancia
 * (menor valor numérico) sale primero de la cola.
 */
class ColaDeTareas {

    private final Queue<Tarea> cola;

    public ColaDeTareas() {
        // PriorityQueue usa el compareTo() de Collections.Tarea para ordenar
        this.cola = new PriorityQueue<>();
    }

    public void agregarTarea(Tarea tarea) {
        if (tarea == null) {
            throw new IllegalArgumentException("La tarea no puede ser nula.");
        }
        cola.offer(tarea);
    }

    /**
     * Extrae y retorna la tarea con mayor prioridad (menor número).
     */
    public Tarea atenderTarea() {
        return cola.poll();
    }

    /**
     * Consulta la siguiente tarea a atender, sin removerla.
     */
    public Tarea siguienteTarea() {
        return cola.peek();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int tamanio() {
        return cola.size();
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    static void main(String[] args) {
        ColaDeTareas colaTareas = new ColaDeTareas();

        System.out.println("--- Agregando tareas ---");
        colaTareas.agregarTarea(new Tarea("Responder correos", 3));
        colaTareas.agregarTarea(new Tarea("Corregir vulnerabilidad crítica", 1));
        colaTareas.agregarTarea(new Tarea("Actualizar documentación", 5));
        colaTareas.agregarTarea(new Tarea("Reunión con el equipo", 2));
        colaTareas.agregarTarea(new Tarea("Revisar backups", 4));

        System.out.println("Tamaño de la cola: " + colaTareas.tamanio());
        System.out.println("Siguiente tarea a atender: " + colaTareas.siguienteTarea());

        System.out.println("\n--- Atendiendo tareas en orden de prioridad ---");
        while (!colaTareas.estaVacia()) {
            Tarea atendida = colaTareas.atenderTarea();
            System.out.println("Atendiendo -> " + atendida);
        }

        System.out.println("\nCola vacía: " + colaTareas.estaVacia());
    }
}