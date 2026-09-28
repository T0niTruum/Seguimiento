package Collections;

import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;

/**
 * Collections.Producto ordenado por código para poder guardarlo en un TreeSet.
 */
class Producto implements Comparable<Producto> {
    private final String codigo;
    private String nombre;
    private double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int compareTo(Producto otro) {
        return this.codigo.compareToIgnoreCase(otro.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto producto = (Producto) o;
        return codigo.equalsIgnoreCase(producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("Collections.Producto{codigo='%s', nombre='%s', precio=%.2f}",
                codigo, nombre, precio);
    }
}

/**
 * Collections.Empresa que mantiene su catálogo de productos en un TreeSet (sin duplicados y ordenado por código).
 */
class Empresa {
    private final String nombre;
    private final TreeSet<Producto> productos;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.productos = new TreeSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public TreeSet<Producto> getProductos() {
        return productos;
    }

    public boolean agregarProducto(Producto producto) {
        return productos.add(producto);
    }

    /**
     * Busca un producto por su código. TreeSet está ordenado por código,
     * así que se usa ceiling con un producto "llave" para localizarlo en tiempo logarítmico.
     */
    public Optional<Producto> buscarProductoPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return Optional.empty();
        }

        Producto llave = new Producto(codigo, "", 0);
        Producto encontrado = productos.ceiling(llave);

        if (encontrado != null && encontrado.getCodigo().equalsIgnoreCase(codigo)) {
            return Optional.of(encontrado);
        }
        return Optional.empty();
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("Catálogo de " + nombre + " (" + productos.size() + "):");
        for (Producto producto : productos) {
            System.out.println("  - " + producto);
        }
    }
}

public class exercise1 {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("TecnoSur");

        empresa.agregarProducto(new Producto("P003", "Teclado", 85_000));
        empresa.agregarProducto(new Producto("P001", "Mouse", 35_000));
        empresa.agregarProducto(new Producto("P002", "Monitor", 750_000));
        empresa.agregarProducto(new Producto("P001", "Mouse duplicado", 40_000));

        empresa.listarProductos();

        String codigoBuscado = "P002";
        Optional<Producto> resultado = empresa.buscarProductoPorCodigo(codigoBuscado);
        if (resultado.isPresent()) {
            System.out.println("\nEncontrado: " + resultado.get());
        } else {
            System.out.println("\nNo existe un producto con código " + codigoBuscado);
        }

        String codigoInexistente = "P999";
        Optional<Producto> noEncontrado = empresa.buscarProductoPorCodigo(codigoInexistente);
        if (noEncontrado.isPresent()) {
            System.out.println("Encontrado: " + noEncontrado.get());
        } else {
            System.out.println("No existe un producto con código " + codigoInexistente);
        }
    }
}
