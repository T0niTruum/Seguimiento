package Generics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Inventario genérico que almacena elementos comparables en un ArrayList<T>.
 * Permite filtrar los elementos mayores que un umbral dado, recorriendo
 * la colección únicamente con un Iterator (sin usar for-each).
 *
 * @param <T> tipo de los elementos, debe ser comparable consigo mismo
 */
public class InventarioCaja<T extends Comparable<T>> {

    private final ArrayList<T> elementos;

    public InventarioCaja() {
        this.elementos = new ArrayList<>();
    }

    /**
     * Agrega un elemento al inventario.
     *
     * @param elemento el elemento a agregar (no puede ser nulo)
     */
    public void agregar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permiten elementos nulos.");
        }
        elementos.add(elemento);
    }

    public int tamanio() {
        return elementos.size();
    }

    /**
     * Devuelve una NUEVA lista con los elementos estrictamente mayores
     * que el umbral indicado. El recorrido se hace exclusivamente con
     * un Iterator; el inventario original no se modifica.
     *
     * @param umbral valor de referencia (no puede ser nulo)
     * @return nueva lista con los elementos mayores que el umbral
     */
    public ArrayList<T> filtrarMayoresQue(T umbral) {
        if (umbral == null) {
            throw new IllegalArgumentException("El umbral no puede ser nulo.");
        }

        ArrayList<T> resultado = new ArrayList<>();
        Iterator<T> it = elementos.iterator();

        while (it.hasNext()) {
            T actual = it.next();
            if (actual.compareTo(umbral) > 0) {
                resultado.add(actual);
            }
        }
        return resultado;
    }

    /**
     * Imprime el contenido del inventario, también con Iterator.
     */
    public void imprimir() {
        Iterator<T> it = elementos.iterator();
        System.out.print("Inventario: [ ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println("]");
    }

    @Override
    public String toString() {
        return "InventarioCaja" + elementos;
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        System.out.println("--- InventarioCaja de Integer ---");
        InventarioCaja<Integer> inventarioNumeros = new InventarioCaja<>();
        inventarioNumeros.agregar(15);
        inventarioNumeros.agregar(42);
        inventarioNumeros.agregar(7);
        inventarioNumeros.agregar(99);
        inventarioNumeros.agregar(30);
        inventarioNumeros.imprimir();

        ArrayList<Integer> mayoresQue30 = inventarioNumeros.filtrarMayoresQue(30);
        System.out.println("Mayores que 30: " + mayoresQue30);

        ArrayList<Integer> mayoresQue100 = inventarioNumeros.filtrarMayoresQue(100);
        System.out.println("Mayores que 100: " + mayoresQue100);

        System.out.println("El inventario original no cambia: " + inventarioNumeros);

        System.out.println("\n--- InventarioCaja de String ---");
        InventarioCaja<String> inventarioTextos = new InventarioCaja<>();
        inventarioTextos.agregar("Manzana");
        inventarioTextos.agregar("Banano");
        inventarioTextos.agregar("Naranja");
        inventarioTextos.agregar("Uva");
        inventarioTextos.imprimir();

        System.out.println("Mayores (alfabéticamente) que \"Naranja\": "
                + inventarioTextos.filtrarMayoresQue("Naranja"));

        System.out.println("\n--- InventarioCaja de Double ---");
        InventarioCaja<Double> inventarioPrecios = new InventarioCaja<>();
        inventarioPrecios.agregar(9.99);
        inventarioPrecios.agregar(24.50);
        inventarioPrecios.agregar(5.25);
        inventarioPrecios.agregar(59.90);
        inventarioPrecios.imprimir();

        System.out.println("Mayores que 10.0: " + inventarioPrecios.filtrarMayoresQue(10.0));

        System.out.println("\n--- Probando umbral nulo ---");
        try {
            inventarioNumeros.filtrarMayoresQue(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }
}
