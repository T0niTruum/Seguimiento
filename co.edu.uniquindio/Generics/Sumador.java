package Generics;
/**
 * Contiene un método genérico acotado capaz de sumar dos valores
 * numéricos de cualquier tipo que extienda Number.
 */
public class Sumador {

    /**
     * Método genérico estático acotado: T debe ser Number o una
     * subclase (Integer, Double, Float, Long, etc.). Gracias a esto
     * se puede invocar doubleValue() sobre ambos parámetros.
     *
     * @param a primer valor numérico
     * @param b segundo valor numérico
     * @param <T> tipo numérico de los parámetros, debe extender Number
     * @return la suma de a y b, expresada como double
     */
    public static <T extends Number> double sumar(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    /**
     * Método main para probar el método genérico de forma independiente.
     */
    public static void main(String[] args) {
        System.out.println("--- Suma de Integers ---");
        int resultado1 = (int) sumar(5, 10);
        System.out.println("5 + 10 = " + sumar(5, 10));

        System.out.println("\n--- Suma de Doubles ---");
        System.out.println("3.5 + 2.25 = " + sumar(3.5, 2.25));

        System.out.println("\n--- Suma de Floats ---");
        System.out.println("1.5f + 2.5f = " + sumar(1.5f, 2.5f));

        System.out.println("\n--- Suma de Longs ---");
        System.out.println("1000000000L + 2000000000L = " + sumar(1000000000L, 2000000000L));

        System.out.println("\n--- Suma mezclando tipos numéricos distintos ---");
        // Nota: al mezclar Integer y Double, Java infiere el supertipo común (Number),
        // por lo que el resultado sigue siendo válido.
        Number a = 10;
        Number b = 5.5;
        System.out.println("10 + 5.5 = " + sumar(a, b));

        // Esto NO compilaría, ya que String no extiende Number:
        // sumar("10", "20");
    }
}
