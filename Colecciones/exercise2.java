import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Pila (Stack) que solo permite apilar elementos cuyo tipo coincida
 * con el tipo del elemento que se encuentra en la cima.
 */
public class exercise2 {

    private final Stack<Object> pila;

    public exercise2() {
        this.pila = new Stack<>();
    }

    /**
     * Inserta un elemento en la pila, solo si su tipo coincide
     * con el tipo del elemento en la cima (o si la pila está vacía).
     *
     * @param elemento el objeto a insertar
     * @throws IllegalArgumentException si el tipo no coincide con el de la cima
     */
    public void apilar(Object elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se permite apilar elementos nulos.");
        }

        if (!pila.isEmpty()) {
            Class<?> tipoCima = pila.peek().getClass();
            Class<?> tipoNuevo = elemento.getClass();

            if (!tipoCima.equals(tipoNuevo)) {
                throw new IllegalArgumentException(
                        "Tipo incompatible. Se esperaba " + tipoCima.getSimpleName()
                                + " pero se intentó insertar " + tipoNuevo.getSimpleName());
            }
        }

        pila.push(elemento);
    }

    /**
     * Extrae y retorna el elemento en la cima de la pila.
     *
     * @return el elemento removido
     * @throws EmptyStackException si la pila está vacía
     */
    public Object desapilar() {
        if (pila.isEmpty()) {
            throw new EmptyStackException();
        }
        return pila.pop();
    }

    /**
     * Retorna (sin remover) el elemento en la cima de la pila.
     */
    public Object verCima() {
        if (pila.isEmpty()) {
            throw new EmptyStackException();
        }
        return pila.peek();
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }

    public int tamanio() {
        return pila.size();
    }

    @Override
    public String toString() {
        return pila.toString();
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        exercise2 pilaEnteros = new exercise2();

        System.out.println("--- Prueba con enteros ---");
        pilaEnteros.apilar(10);
        pilaEnteros.apilar(20);
        pilaEnteros.apilar(30);
        System.out.println("Pila actual: " + pilaEnteros);
        System.out.println("Cima: " + pilaEnteros.verCima());

        try {
            System.out.println("Intentando apilar un String en una pila de enteros...");
            pilaEnteros.apilar("Texto no permitido");
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        System.out.println("Desapilando: " + pilaEnteros.desapilar());
        System.out.println("Pila luego de desapilar: " + pilaEnteros);

        System.out.println("\n--- Prueba con Strings en una nueva pila ---");
        exercise2 pilaTextos = new exercise2();
        pilaTextos.apilar("Java");
        pilaTextos.apilar("Colecciones");
        System.out.println("Pila de textos: " + pilaTextos);

        try {
            pilaTextos.apilar(3.14); // Double, no coincide con String
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }

        System.out.println("Tamaño final: " + pilaTextos.tamanio());
    }
}