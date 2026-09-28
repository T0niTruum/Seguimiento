package Generics;

/**
 * Clase genérica con múltiples bounds (intersection type):
 * T debe ser al mismo tiempo subtipo de Number Y de Comparable<T>.
 * Esto permite tanto operar con el valor numérico (doubleValue(), etc.)
 * como compararlo directamente con otro valor del mismo tipo.
 *
 * @param <T> tipo numérico y comparable consigo mismo
 */
public class EntidadPersistente<T extends Number & Comparable<T>> {

    private T valor;

    public EntidadPersistente(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * Compara el valor almacenado con otro valor del mismo tipo T,
     * usando el compareTo() garantizado por el bound Comparable<T>.
     *
     * @param otro el valor con el que se va a comparar
     * @return número negativo si este valor es menor, 0 si es igual,
     *         positivo si es mayor
     */
    public int compararCon(T otro) {
        return this.valor.compareTo(otro);
    }

    /**
     * Compara el valor almacenado con el de otra EntidadPersistente<T>.
     */
    public int compararCon(EntidadPersistente<T> otra) {
        return this.valor.compareTo(otra.getValor());
    }

    /**
     * Determina cuál de las dos entidades tiene el mayor valor.
     */
    public EntidadPersistente<T> obtenerMayor(EntidadPersistente<T> otra) {
        return (this.compararCon(otra) >= 0) ? this : otra;
    }

    @Override
    public String toString() {
        return "EntidadPersistente{valor=" + valor + "}";
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        System.out.println("--- EntidadPersistente de Integer ---");
        EntidadPersistente<Integer> entidad1 = new EntidadPersistente<>(50);
        EntidadPersistente<Integer> entidad2 = new EntidadPersistente<>(80);

        System.out.println(entidad1 + " vs " + entidad2);
        int resultado = entidad1.compararCon(entidad2);
        System.out.println("Resultado compareTo: " + resultado
                + " (" + (resultado < 0 ? "entidad1 es menor" : resultado == 0 ? "son iguales" : "entidad1 es mayor") + ")");

        EntidadPersistente<Integer> mayor = entidad1.obtenerMayor(entidad2);
        System.out.println("La mayor es: " + mayor);

        System.out.println("\n--- Comparando directamente contra un valor T ---");
        System.out.println("¿50 comparado con 30? " + entidad1.compararCon(30));

        System.out.println("\n--- EntidadPersistente de Double ---");
        EntidadPersistente<Double> entidadA = new EntidadPersistente<>(3.14);
        EntidadPersistente<Double> entidadB = new EntidadPersistente<>(2.71);

        System.out.println(entidadA + " vs " + entidadB);
        System.out.println("Mayor: " + entidadA.obtenerMayor(entidadB));

        System.out.println("\n--- Actualizando el valor de una entidad ---");
        entidadB.setValor(5.0);
        System.out.println("Nuevo valor de entidadB: " + entidadB);
        System.out.println("Mayor ahora: " + entidadA.obtenerMayor(entidadB));

        // Esto NO compilaría, ya que String no es Number:
        // EntidadPersistente<String> entidadInvalida = new EntidadPersistente<>("texto");
    }
}
