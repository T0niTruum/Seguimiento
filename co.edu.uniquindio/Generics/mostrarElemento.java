package Generics;

/**
 * Contiene un método genérico estático capaz de recibir e imprimir
 * en consola un elemento de cualquier tipo T.
 */
class MostrarElemento {

    /**
     * Método genérico estático: el parámetro de tipo <T> se declara
     * antes del tipo de retorno, lo que permite invocarlo con
     * cualquier tipo de dato sin necesidad de una clase genérica.
     *
     * @param elemento el valor a mostrar, de cualquier tipo
     * @param <T> el tipo del elemento recibido
     */
    public static <T> void mostrarElemento(T elemento) {
        System.out.println("Elemento recibido: " + elemento
                + " (tipo: " + elemento.getClass().getSimpleName() + ")");
    }

    /**
     * Método main para probar el método genérico de forma independiente.
     */
    public static void main(String[] args) {
        System.out.println("--- Probando con distintos tipos de datos ---");

        mostrarElemento("Hola, mundo");          // String
        mostrarElemento(123);                    // Integer
        mostrarElemento(3.1416);                 // Double
        mostrarElemento(true);                   // Boolean
        mostrarElemento('J');                    // Character

        System.out.println("\n--- Probando con un objeto personalizado ---");
        Caja<String> caja = new Caja<>();
        mostrarElemento(caja);                   // Cualquier objeto, incluso genérico

        System.out.println("\n--- Probando con un arreglo ---");
        Integer[] numeros = {1, 2, 3};
        mostrarElemento(numeros);
    }
}