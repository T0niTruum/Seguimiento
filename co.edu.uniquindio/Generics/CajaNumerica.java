package Generics;
/**
 * Clase genérica acotada (bounded) que solo acepta tipos que sean
 * subclase de Number (Integer, Double, Float, Long, etc.).
 * Esto permite invocar métodos propios de Number, como doubleValue().
 *
 * @param <T> tipo numérico almacenado, debe extender Number
 */
class CajaNumerica<T extends Number> {

    private T numero;

    public CajaNumerica(T numero) {
        this.numero = numero;
    }

    public void guardar(T numero) {
        this.numero = numero;
    }

    public T obtener() {
        return numero;
    }

    /**
     * Retorna el doble del valor almacenado.
     * Gracias al bound "extends Number", se puede llamar a
     * doubleValue() sin necesidad de casting.
     *
     * @return el valor almacenado multiplicado por 2, como double
     */
    public double doble() {
        return numero.doubleValue() * 2;
    }

    @Override
    public String toString() {
        return "CajaNumerica{numero=" + numero + "}";
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        System.out.println("--- CajaNumerica de Integer ---");
        CajaNumerica<Integer> cajaEntero = new CajaNumerica<>(15);
        System.out.println("Valor: " + cajaEntero.obtener());
        System.out.println("Doble: " + cajaEntero.doble());

        System.out.println("\n--- CajaNumerica de Double ---");
        CajaNumerica<Double> cajaDouble = new CajaNumerica<>(7.5);
        System.out.println("Valor: " + cajaDouble.obtener());
        System.out.println("Doble: " + cajaDouble.doble());

        System.out.println("\n--- CajaNumerica de Float ---");
        CajaNumerica<Float> cajaFloat = new CajaNumerica<>(3.2f);
        System.out.println("Valor: " + cajaFloat.obtener());
        System.out.println("Doble: " + cajaFloat.doble());

        System.out.println("\n--- Reemplazando el valor guardado ---");
        cajaEntero.guardar(100);
        System.out.println("Nuevo valor: " + cajaEntero.obtener());
        System.out.println("Nuevo doble: " + cajaEntero.doble());

        // Esto NO compilaría, ya que String no extiende Number:
        // CajaNumerica<String> cajaInvalida = new CajaNumerica<>("texto");
    }
}
