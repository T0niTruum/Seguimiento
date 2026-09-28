package Generics;
    /**
     * Clase de apoyo que implementa tanto Runnable como Comparable<TareaEjecutable>,
     * para poder probar el método genérico procesar(), que exige ambos bounds.
     * Se compara según la duración estimada de la tarea.
     */
    class TareaEjecutable implements Runnable, Comparable<TareaEjecutable> {

        private final String nombre;
        private final int duracionEstimada; // en segundos, solo para efectos de comparación

        public TareaEjecutable(String nombre, int duracionEstimada) {
            this.nombre = nombre;
            this.duracionEstimada = duracionEstimada;
        }

        public String getNombre() {
            return nombre;
        }

        public int getDuracionEstimada() {
            return duracionEstimada;
        }

        @Override
        public void run() {
            System.out.println("Ejecutando tarea: \"" + nombre + "\" (duración estimada: "
                    + duracionEstimada + "s)");
        }

        @Override
        public int compareTo(TareaEjecutable otra) {
            return Integer.compare(this.duracionEstimada, otra.duracionEstimada);
        }

        @Override
        public String toString() {
            return "TareaEjecutable{nombre='" + nombre + "', duracion=" + duracionEstimada + "}";
        }
    }
    /**
     * Contiene un método genérico con múltiples bounds: T debe ser al mismo
     * tiempo Runnable (para poder ejecutarse) y Comparable<T> (para poder
     * compararse consigo mismo).
     */
    public class ProcesadorTareas {

        /**
         * Ejecuta el objeto recibido (invocando su run()) y luego lo compara
         * con otro objeto del mismo tipo T.
         *
         * @param elemento el objeto a ejecutar y comparar
         * @param otro     el objeto contra el cual se compara después de ejecutar
         * @param <T>      tipo que debe ser Runnable y Comparable consigo mismo
         * @return el resultado de compareTo(): negativo si elemento es menor,
         *         0 si es igual, positivo si es mayor que otro
         */
        public static <T extends Runnable & Comparable<T>> int procesar(T elemento, T otro) {
            System.out.println("--- Ejecutando el elemento antes de comparar ---");
            elemento.run();

            int resultado = elemento.compareTo(otro);
            System.out.println("Resultado de la comparación: " + resultado);
            return resultado;
        }

        /**
         * Método main para probar el método genérico de forma independiente.
         */
        public static void main(String[] args) {
            TareaEjecutable tareaCorta = new TareaEjecutable("Enviar correo", 5);
            TareaEjecutable tareaLarga = new TareaEjecutable("Generar reporte mensual", 120);

            System.out.println("=== Procesando tareaCorta vs tareaLarga ===");
            int resultado1 = procesar(tareaCorta, tareaLarga);
            interpretarResultado(tareaCorta, tareaLarga, resultado1);

            System.out.println("\n=== Procesando tareaLarga vs tareaCorta ===");
            int resultado2 = procesar(tareaLarga, tareaCorta);
            interpretarResultado(tareaLarga, tareaCorta, resultado2);

            System.out.println("\n=== Procesando dos tareas con igual duración ===");
            TareaEjecutable tareaIgual1 = new TareaEjecutable("Backup A", 30);
            TareaEjecutable tareaIgual2 = new TareaEjecutable("Backup B", 30);
            int resultado3 = procesar(tareaIgual1, tareaIgual2);
            interpretarResultado(tareaIgual1, tareaIgual2, resultado3);

            // Esto NO compilaría, ya que String no es Runnable ni Comparable<String> consigo mismo
            // de la forma requerida por el bound:
            // procesar("texto1", "texto2");
        }

        private static void interpretarResultado(TareaEjecutable a, TareaEjecutable b, int resultado) {
            String relacion = resultado < 0 ? "es más corta que" : resultado == 0 ? "dura igual que" : "es más larga que";
            System.out.println(a.getNombre() + " " + relacion + " " + b.getNombre());
        }
    }
