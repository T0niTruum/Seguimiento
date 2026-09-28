package Generics;
import java.util.ArrayList;
import java.util.List;
/**
 * Interfaz genérica que define el contrato básico de un contenedor
 * de elementos de tipo T.
 *
 * @param <T> el tipo de elementos que maneja el contenedor
 */
public interface Contenedor<T> {

        /**
         * Agrega un elemento al contenedor.
         *
         * @param item el elemento a agregar
         */
        void agregar(T item);

        /**
         * Obtiene el elemento almacenado en la posición indicada.
         *
         * @param indice la posición del elemento a obtener
         * @return el elemento en esa posición
         */
        T obtener(int indice);

        /**
         * Retorna la cantidad de elementos almacenados.
         */
        int tamanio();
}

/**
 * Implementación de Contenedor<T> respaldada por un ArrayList.
 *
 * @param <T> el tipo de elementos que maneja este contenedor
 */
class ListaContenedor<T> implements Contenedor<T> {

    private final List<T> elementos;

    public ListaContenedor() {
        this.elementos = new ArrayList<>();
    }

    @Override
    public void agregar(T item) {
        elementos.add(item);
        System.out.println("Elemento agregado: " + item);
    }

    @Override
    public T obtener(int indice) {
        if (indice < 0 || indice >= elementos.size()) {
            throw new IndexOutOfBoundsException(
                    "Índice fuera de rango: " + indice + " (tamaño actual: " + elementos.size() + ")");
        }
        return elementos.get(indice);
    }

    @Override
    public int tamanio() {
        return elementos.size();
    }

    @Override
    public String toString() {
        return elementos.toString();
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        System.out.println("--- ListaContenedor de Strings ---");
        Contenedor<String> contenedorTextos = new ListaContenedor<>();
        contenedorTextos.agregar("Java");
        contenedorTextos.agregar("Generics");
        contenedorTextos.agregar("Interfaces");

        System.out.println("Tamaño: " + contenedorTextos.tamanio());
        System.out.println("Elemento en índice 1: " + contenedorTextos.obtener(1));
        System.out.println("Contenido completo: " + contenedorTextos);

        System.out.println("\n--- ListaContenedor de Enteros ---");
        Contenedor<Integer> contenedorNumeros = new ListaContenedor<>();
        contenedorNumeros.agregar(10);
        contenedorNumeros.agregar(20);
        contenedorNumeros.agregar(30);

        System.out.println("Elemento en índice 2: " + contenedorNumeros.obtener(2));
        System.out.println("Contenido completo: " + contenedorNumeros);

        System.out.println("\n--- Probando acceso a un índice inválido ---");
        try {
            contenedorNumeros.obtener(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }
}