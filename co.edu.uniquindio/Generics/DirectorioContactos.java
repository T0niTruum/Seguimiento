package Generics;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Representa un contacto con nombre, teléfono y email.
 * Su orden natural (Comparable) es alfabético por nombre,
 * sin distinguir mayúsculas de minúsculas.
 */
class Contacto implements Comparable<Contacto> {

    private final String nombre;
    private final String telefono;
    private final String email;

    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Orden natural: alfabético por nombre (ignorando mayúsculas/minúsculas).
     */
    @Override
    public int compareTo(Contacto otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

    @Override
    public String toString() {
        return "Contacto{nombre='" + nombre + "', telefono='" + telefono
                + "', email='" + email + "'}";
    }
}

/**
 * Directorio de contactos respaldado por una LinkedList<Contacto>.
 * Permite buscar contactos por dominio de email (recorriendo solo con
 * Iterator) y ordenarlos por teléfono usando un Comparator.
 */
public class DirectorioContactos {

    private final LinkedList<Contacto> contactos;

    public DirectorioContactos() {
        this.contactos = new LinkedList<>();
    }

    /**
     * Agrega un contacto al directorio.
     */
    public void agregarContacto(Contacto contacto) {
        if (contacto == null) {
            throw new IllegalArgumentException("El contacto no puede ser nulo.");
        }
        contactos.add(contacto);
    }

    public int tamanio() {
        return contactos.size();
    }

    /**
     * Retorna una NUEVA lista con los contactos cuyo email termina en el
     * dominio indicado. El recorrido se hace exclusivamente con un Iterator.
     * El dominio puede indicarse con o sin arroba ("gmail.com" o "@gmail.com");
     * internamente se exige la arroba para evitar falsos positivos como
     * "xgmail.com". La comparación no distingue mayúsculas/minúsculas.
     *
     * @param dominio dominio a buscar (no puede ser nulo ni vacío)
     * @return lista de contactos que coinciden (vacía si no hay ninguno)
     */
    public LinkedList<Contacto> buscarPorDominio(String dominio) {
        if (dominio == null || dominio.isBlank()) {
            throw new IllegalArgumentException("El dominio no puede estar vacío.");
        }

        String sufijo = dominio.trim().toLowerCase();
        if (!sufijo.startsWith("@")) {
            sufijo = "@" + sufijo;
        }

        LinkedList<Contacto> resultado = new LinkedList<>();
        Iterator<Contacto> it = contactos.iterator();

        while (it.hasNext()) {
            Contacto actual = it.next();
            if (actual.getEmail().toLowerCase().endsWith(sufijo)) {
                resultado.add(actual);
            }
        }
        return resultado;
    }

    /**
     * Retorna una copia del directorio ordenada por teléfono (ascendente)
     * usando un Comparator. El directorio original no se modifica.
     */
    public LinkedList<Contacto> ordenarPorTelefono() {
        LinkedList<Contacto> copia = new LinkedList<>(contactos);
        Comparator<Contacto> comparadorPorTelefono = Comparator.comparing(Contacto::getTelefono);
        copia.sort(comparadorPorTelefono);
        return copia;
    }

    /**
     * Retorna una copia del directorio ordenada por su orden natural
     * (nombre), definido en Contacto.compareTo().
     */
    public LinkedList<Contacto> ordenarPorNombre() {
        LinkedList<Contacto> copia = new LinkedList<>(contactos);
        Collections.sort(copia);
        return copia;
    }

    /**
     * Imprime una lista de contactos usando un Iterator.
     */
    public static void imprimirLista(LinkedList<Contacto> lista) {
        if (lista.isEmpty()) {
            System.out.println("(sin resultados)");
            return;
        }
        Iterator<Contacto> it = lista.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Imprime el directorio actual (orden de inserción).
     */
    public void imprimirDirectorio() {
        imprimirLista(contactos);
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        DirectorioContactos directorio = new DirectorioContactos();

        System.out.println("--- Agregando contactos ---");
        directorio.agregarContacto(new Contacto("Carlos Pérez", "3105558899", "carlos.perez@gmail.com"));
        directorio.agregarContacto(new Contacto("Ana Gómez", "3001234567", "ana.gomez@uniquindio.edu.co"));
        directorio.agregarContacto(new Contacto("Luis Rodríguez", "3204567890", "luis.rodriguez@gmail.com"));
        directorio.agregarContacto(new Contacto("Beatriz Ruiz", "3157778899", "bea.ruiz@outlook.com"));
        directorio.agregarContacto(new Contacto("David Torres", "3012223344", "david.torres@uniquindio.edu.co"));

        System.out.println("Tamaño del directorio: " + directorio.tamanio());

        System.out.println("\n--- Directorio en orden de inserción ---");
        directorio.imprimirDirectorio();

        System.out.println("\n--- Contactos con email @gmail.com ---");
        DirectorioContactos.imprimirLista(directorio.buscarPorDominio("gmail.com"));

        System.out.println("\n--- Contactos con email @uniquindio.edu.co (con arroba) ---");
        DirectorioContactos.imprimirLista(directorio.buscarPorDominio("@uniquindio.edu.co"));

        System.out.println("\n--- Contactos con email @yahoo.com (sin coincidencias) ---");
        DirectorioContactos.imprimirLista(directorio.buscarPorDominio("yahoo.com"));

        System.out.println("\n--- Directorio ordenado por nombre (orden natural) ---");
        DirectorioContactos.imprimirLista(directorio.ordenarPorNombre());

        System.out.println("\n--- Directorio ordenado por teléfono (Comparator) ---");
        DirectorioContactos.imprimirLista(directorio.ordenarPorTelefono());

        System.out.println("\n--- El directorio original no cambió ---");
        directorio.imprimirDirectorio();

        System.out.println("\n--- Probando dominio vacío ---");
        try {
            directorio.buscarPorDominio("  ");
        } catch (IllegalArgumentException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }
}