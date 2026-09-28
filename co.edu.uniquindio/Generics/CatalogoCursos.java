package Generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Representa un curso con código, nombre y año.
 */
class Curso {

    private final String codigo;
    private final String nombre;
    private final int anio;

    public Curso(String codigo, String nombre, int anio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.anio = anio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnio() {
        return anio;
    }

    @Override
    public String toString() {
        return "Curso{codigo='" + codigo + "', nombre='" + nombre + "', anio=" + anio + "}";
    }
}


/**
 * Catálogo de cursos respaldado por un ArrayList<Curso>.
 * Permite buscar cursos por año (recorriendo solo con Iterator)
 * y ordenarlos por código usando un Comparator.
 */
public class CatalogoCursos {

    private final ArrayList<Curso> cursos;

    public CatalogoCursos() {
        this.cursos = new ArrayList<>();
    }

    /**
     * Agrega un curso al catálogo.
     */
    public void agregarCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        }
        cursos.add(curso);
    }

    public int tamanio() {
        return cursos.size();
    }

    /**
     * Retorna una NUEVA lista con todos los cursos del año indicado.
     * El recorrido se hace exclusivamente con un Iterator (sin for-each).
     *
     * @param anioObjetivo el año a buscar
     * @return lista de cursos de ese año (vacía si no hay coincidencias)
     */
    public ArrayList<Curso> buscarPorAnio(int anioObjetivo) {
        ArrayList<Curso> resultado = new ArrayList<>();
        Iterator<Curso> it = cursos.iterator();

        while (it.hasNext()) {
            Curso actual = it.next();
            if (actual.getAnio() == anioObjetivo) {
                resultado.add(actual);
            }
        }
        return resultado;
    }

    /**
     * Retorna una copia del catálogo ordenada por código (ascendente)
     * usando un Comparator. El catálogo original no se modifica.
     */
    public ArrayList<Curso> ordenarPorCodigo() {
        ArrayList<Curso> copia = new ArrayList<>(cursos);
        Comparator<Curso> comparadorPorCodigo = Comparator.comparing(Curso::getCodigo);
        copia.sort(comparadorPorCodigo);
        return copia;
    }

    /**
     * Imprime una lista de cursos usando un Iterator.
     */
    public static void imprimirLista(ArrayList<Curso> lista) {
        if (lista.isEmpty()) {
            System.out.println("(sin resultados)");
            return;
        }
        Iterator<Curso> it = lista.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Imprime el catálogo actual (orden de inserción).
     */
    public void imprimirCatalogo() {
        imprimirLista(cursos);
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        CatalogoCursos catalogo = new CatalogoCursos();

        System.out.println("--- Agregando cursos ---");
        catalogo.agregarCurso(new Curso("MAT301", "Cálculo Diferencial", 2024));
        catalogo.agregarCurso(new Curso("INF101", "Introducción a la Programación", 2025));
        catalogo.agregarCurso(new Curso("SEG205", "Seguridad de la Información", 2025));
        catalogo.agregarCurso(new Curso("BD210", "Bases de Datos", 2024));
        catalogo.agregarCurso(new Curso("INF150", "Estructuras de Datos", 2025));
        catalogo.agregarCurso(new Curso("RED220", "Redes de Computadores", 2023));

        System.out.println("Tamaño del catálogo: " + catalogo.tamanio());

        System.out.println("\n--- Catálogo en orden de inserción ---");
        catalogo.imprimirCatalogo();

        System.out.println("\n--- Cursos del año 2025 ---");
        imprimirLista(catalogo.buscarPorAnio(2025));

        System.out.println("\n--- Cursos del año 2024 ---");
        imprimirLista(catalogo.buscarPorAnio(2024));

        System.out.println("\n--- Cursos del año 2020 (sin coincidencias) ---");
        imprimirLista(catalogo.buscarPorAnio(2020));

        System.out.println("\n--- Catálogo ordenado por código ---");
        imprimirLista(catalogo.ordenarPorCodigo());

        System.out.println("\n--- El catálogo original no cambió ---");
        catalogo.imprimirCatalogo();
    }
}
