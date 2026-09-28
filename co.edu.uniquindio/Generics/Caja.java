package Generics;

/**
 * Clase genérica que representa una "caja" capaz de almacenar
 * un único valor de cualquier tipo T.
 *
 * @param <T> el tipo de dato que contendrá la caja
 */
class Caja<T> {

    private T contenido;

    /**
     * Guarda (o reemplaza) el valor almacenado en la caja.
     *
     * @param valor el valor a guardar
     */
    public void guardar(T valor) {
        this.contenido = valor;
        System.out.println("Valor guardado en la caja: " + valor);
    }

    /**
     * Obtiene el valor actualmente almacenado en la caja.
     *
     * @return el valor guardado, o null si la caja está vacía
     */
    public T obtener() {
        return contenido;
    }

    public boolean estaVacia() {
        return contenido == null;
    }

    @Override
    public String toString() {
        return "Caja{contenido=" + contenido + "}";
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        System.out.println("--- Caja de Strings ---");
        Caja<String> cajaTexto = new Caja<>();
        System.out.println("¿Está vacía? " + cajaTexto.estaVacia());
        cajaTexto.guardar("Hola, Generics!");
        System.out.println("Contenido obtenido: " + cajaTexto.obtener());
        System.out.println("¿Está vacía? " + cajaTexto.estaVacia());

        System.out.println("\n--- Caja de Enteros ---");
        Caja<Integer> cajaEntero = new Caja<>();
        cajaEntero.guardar(42);
        System.out.println("Contenido obtenido: " + cajaEntero.obtener());

        System.out.println("\n--- Caja reemplazando su contenido ---");
        cajaEntero.guardar(100);
        System.out.println("Nuevo contenido: " + cajaEntero.obtener());

        System.out.println("\n--- Caja de un tipo personalizado (Double) ---");
        Caja<Double> cajaDouble = new Caja<>();
        cajaDouble.guardar(3.1416);
        System.out.println("Contenido obtenido: " + cajaDouble.obtener());

        System.out.println("\n--- toString() de una caja ---");
        System.out.println(cajaTexto);
    }
}