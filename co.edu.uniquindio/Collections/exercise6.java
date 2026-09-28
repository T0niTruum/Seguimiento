package Collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Representa un producto del inventario de la tienda.
 */
class Products {

    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad; // cantidad disponible en stock

    public Products(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean estaAgotado() {
        return cantidad <= 0;
    }

    @Override
    public String toString() {
        return "Collections.Products{codigo='" + codigo + "', nombre='" + nombre
                + "', precio=" + precio + ", cantidad=" + cantidad + "}";
    }
}

/**
 * Gestiona el inventario de productos de la tienda usando un ArrayList.
 * Permite agregar, eliminar productos agotados, buscar y listar
 * el inventario ordenado alfabéticamente o por precio.
 */
class InventarioTienda {

    private final List<Products> inventario;

    public InventarioTienda() {
        this.inventario = new ArrayList<>();
    }

    /**
     * Agrega un nuevo producto al inventario.
     */
    public void agregarProducto(Products producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        inventario.add(producto);
        System.out.println("Collections.Producto agregado: " + producto);
    }

    /**
     * Elimina del inventario todos los productos que estén agotados
     * (cantidad <= 0).
     */
    public void eliminarAgotados() {
        int tamanioAntes = inventario.size();
        inventario.removeIf(Products::estaAgotado);
        int eliminados = tamanioAntes - inventario.size();
        System.out.println("Collections.Productos agotados eliminados: " + eliminados);
    }

    /**
     * Busca un producto por su código.
     *
     * @return el producto encontrado o null si no existe
     */
    public Products buscarPorCodigo(String codigo) {
        for (Products p : inventario) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Busca productos cuyo nombre contenga el texto indicado
     * (búsqueda parcial, sin distinguir mayúsculas/minúsculas).
     */
    public List<Products> buscarPorNombre(String textoNombre) {
        List<Products> resultado = new ArrayList<>();
        for (Products p : inventario) {
            if (p.getNombre().toLowerCase().contains(textoNombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Retorna una copia del inventario ordenada alfabéticamente por nombre.
     */
    public List<Products> listarPorNombre() {
        List<Products> copia = new ArrayList<>(inventario);
        copia.sort(Comparator.comparing(Products::getNombre, String.CASE_INSENSITIVE_ORDER));
        return copia;
    }

    /**
     * Retorna una copia del inventario ordenada por precio ascendente.
     */
    public List<Products> listarPorPrecio() {
        List<Products> copia = new ArrayList<>(inventario);
        copia.sort(Comparator.comparingDouble(Products::getPrecio));
        return copia;
    }

    public int tamanio() {
        return inventario.size();
    }

    private static void imprimirLista(List<Products> lista) {
        for (Products p : lista) {
            System.out.println(p);
        }
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        InventarioTienda tienda = new InventarioTienda();

        System.out.println("--- Agregando productos ---");
        tienda.agregarProducto(new Products("P001", "Teclado mecánico", 120000, 10));
        tienda.agregarProducto(new Products("P002", "Monitor 24 pulgadas", 850000, 5));
        tienda.agregarProducto(new Products("P003", "Mouse inalámbrico", 60000, 0));   // agotado
        tienda.agregarProducto(new Products("P004", "Audífonos bluetooth", 150000, 8));
        tienda.agregarProducto(new Products("P005", "Webcam HD", 200000, 0));          // agotado

        System.out.println("\nTamaño del inventario: " + tienda.tamanio());

        System.out.println("\n--- Buscando producto por código (P002) ---");
        Products encontrado = tienda.buscarPorCodigo("P002");
        System.out.println(encontrado != null ? encontrado : "No encontrado");

        System.out.println("\n--- Buscando productos por nombre que contenga 'mouse' ---");
        List<Products> coincidencias = tienda.buscarPorNombre("mouse");
        imprimirLista(coincidencias);

        System.out.println("\n--- Inventario ordenado alfabéticamente ---");
        imprimirLista(tienda.listarPorNombre());

        System.out.println("\n--- Inventario ordenado por precio ---");
        imprimirLista(tienda.listarPorPrecio());

        System.out.println("\n--- Eliminando productos agotados ---");
        tienda.eliminarAgotados();

        System.out.println("\n--- Inventario final ---");
        imprimirLista(tienda.listarPorNombre());

        System.out.println("\nTamaño final del inventario: " + tienda.tamanio());
    }
}