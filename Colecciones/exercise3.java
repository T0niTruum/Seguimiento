import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lista que no permite elementos duplicados.
 * Internamente usa una List (ArrayList) mas antes de insertar valida
 * que el elemento no exista ya en la colección.
 * El recorrido/impresión del contenido se realiza usando un Iterator.
 */
public class exercise3 {

    private final List<Object> lista;

    public exercise3() {
        this.lista = new ArrayList<>();
    }

    /**
     * Agrega un elemento a la lista solo si no existe ya (según equals()).
     *
     * @param elemento el objeto a insertar
     * @return true si se agregó, false si ya existía (duplicado)
     */
    public boolean agregar(Object elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permiten elementos nulos.");
        }

        if (lista.contains(elemento)) {
            System.out.println("Elemento duplicado, no se agregó: " + elemento);
            return false;
        }

        return lista.add(elemento);
    }

    public boolean eliminar(Object elemento) {
        return lista.remove(elemento);
    }

    public int tamanio() {
        return lista.size();
    }

    public boolean estaVacia() {
        return lista.isEmpty();
    }

    /**
     * Imprime el contenido de la lista utilizando un Iterator explícito.
     */
    public void imprimirConIterador() {
        Iterator<Object> it = lista.iterator();
        System.out.print("[ ");
        while (it.hasNext()) {
            Object elemento = it.next();
            System.out.print(elemento + " ");
        }
        System.out.println("]");
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        exercise3 lista = new exercise3();

        System.out.println("--- Insertando elementos ---");
        lista.agregar("Java");
        lista.agregar("Python");
        lista.agregar("Java");      // duplicado, no se agrega
        lista.agregar("JavaScript");
        lista.agregar(10);
        lista.agregar(10);          // duplicado, no se agrega

        System.out.println("\n--- Contenido de la lista (usando iterador) ---");
        lista.imprimirConIterador();

        System.out.println("\nTamaño de la lista: " + lista.tamanio());

        System.out.println("\n--- Eliminando 'Python' ---");
        lista.eliminar("Python");
        lista.imprimirConIterador();

        System.out.println("\nTamaño final: " + lista.tamanio());
    }
}