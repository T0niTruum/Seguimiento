import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * Represents un product simple con Id, nombre y precio.
 */
class Productos{

    private final int id;
    private final String nombre;
    private final double precio;

    public Productos(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + "}";
    }
}


/**
 * Compara el comportamiento de HashMap, LinkedHashMap y TreeMap
 * almacenando productos, usando Id del producto como clave.
 */
class ComparacionMapasProductos {

    public static void main(String[] args) {

        // Se insertan los productos en un orden deliberadamente "desordenado"
        // para poder apreciar cómo cada Map organiza internamente las claves.
        int[] ids        = {5, 1, 4, 2, 3};
        String[] nombres = {"Teclado", "Monitor", "Mouse", "Audífonos", "Webcam"};
        double[] precios = {120000, 850000, 60000, 150000, 200000};

        Map<Integer, Productos> mapaHash = new HashMap<>();
        Map<Integer, Productos> mapaLinkedHash = new LinkedHashMap<>();
        Map<Integer, Productos> mapaTree = new TreeMap<>();

        for (int i = 0; i < ids.length; i++) {
            Productos products = new Productos(ids[i], nombres[i], precios[i]);
            mapaHash.put(products.getId(), products);
            mapaLinkedHash.put(products.getId(), products);
            mapaTree.put(products.getId(), products);
        }

        System.out.println("Orden de inserción: 5, 1, 4, 2, 3\n");

        System.out.println("--- HashMap (sin orden garantizado) ---");
        imprimirMapa(mapaHash);

        System.out.println("\n--- LinkedHashMap (respeta el orden de inserción) ---");
        imprimirMapa(mapaLinkedHash);

        System.out.println("\n--- TreeMap (ordena automáticamente por clave) ---");
        imprimirMapa(mapaTree);


    }

    private static void imprimirMapa(Map<Integer, Productos> mapa) {
        for (Map.Entry<Integer, Productos> entry : mapa.entrySet()) {
            System.out.println("Clave: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    /*"\n=== Diferencias entre HashMap, LinkedHashMap y TreeMap ===");

        System.out.println("""

                1) HashMap:
                   - No garantiza ningún orden en las claves; el orden depende del
                     código hash y puede parecer aleatorio.
                   - Es la implementación más rápida para operaciones básicas
                     (get, put, remove) en promedio O(1).
                   - Permite una clave null y múltiples valores null.
                   - Se usa cuando el orden no importa y se prioriza el rendimiento.

                2) LinkedHashMap:
                   - Mantiene el orden en el que se insertaron los elementos
                     (o el orden de acceso, si se configura así).
                   - Rendimiento similar a HashMap (O(1) en promedio), con una
                     ligera sobrecarga por mantener la lista enlazada interna.
                   - Útil cuando se necesita recorrer los elementos en el mismo
                     orden en que fueron agregados (por ejemplo, un historial).

                3) TreeMap:
                   - Ordena automáticamente las claves según su orden natural
                     (Comparable) o según un Comparator personalizado.
                   - Las operaciones básicas son O(log n), ya que internamente
                     usa un árbol Rojo-Negro (Red-Black Tree).
                   - No permite claves null.
                   - Se usa cuando se necesita mantener los datos ordenados
                     en todo momento (por ejemplo, ordenar productos por id).
                """);
    }*/
}