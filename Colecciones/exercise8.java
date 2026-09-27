import java.util.Vector;

/**
 * Simula el historial de cambios de un editor de texto.
 * Se usa un Vector porque sus operaciones están sincronizadas
 * (thread-safe), lo cual es útil en un editor donde varios hilos
 * podrían registrar cambios sobre el mismo documento de forma concurrente.
 */
class EditorTextoHistorial {

    private final Vector<String> historialCambios;
    private final StringBuilder contenidoActual;

    public EditorTextoHistorial() {
        this.historialCambios = new Vector<>();
        this.contenidoActual = new StringBuilder();
    }

    /**
     * Aplica un cambio al documento (por ejemplo, agregar texto)
     * y lo registra en el historial para poder deshacerlo después.
     */
    public synchronized void realizarCambio(String textoAgregado) {
        if (textoAgregado == null) {
            throw new IllegalArgumentException("El texto no puede ser nulo.");
        }
        contenidoActual.append(textoAgregado);
        historialCambios.add(textoAgregado);
        System.out.println("Cambio registrado: \"" + textoAgregado + "\"");
        mostrarContenido();
    }

    /**
     * Deshace el último cambio realizado: lo elimina del contenido
     * actual y del historial.
     *
     * @return true si se deshizo un cambio, false si no había nada que deshacer
     */
    public synchronized boolean deshacer() {
        if (historialCambios.isEmpty()) {
            System.out.println("No hay cambios para deshacer.");
            return false;
        }

        // Se elimina el último cambio registrado (índice final del Vector)
        String ultimoCambio = historialCambios.remove(historialCambios.size() - 1);

        // Se reconstruye el contenido eliminando la última porción agregada
        int inicio = contenidoActual.length() - ultimoCambio.length();
        contenidoActual.delete(inicio, contenidoActual.length());

        System.out.println("Deshecho: \"" + ultimoCambio + "\"");
        mostrarContenido();
        return true;
    }

    public synchronized String getContenidoActual() {
        return contenidoActual.toString();
    }

    public synchronized int cantidadCambiosEnHistorial() {
        return historialCambios.size();
    }

    public synchronized void mostrarHistorial() {
        System.out.println("Historial de cambios: " + historialCambios);
    }

    private void mostrarContenido() {
        System.out.println("Contenido actual: \"" + contenidoActual + "\"");
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        EditorTextoHistorial editor = new EditorTextoHistorial();

        System.out.println("--- Realizando cambios en el documento ---");
        editor.realizarCambio("Hola");
        editor.realizarCambio(" mundo");
        editor.realizarCambio(", esto es Java");

        System.out.println("\n--- Historial actual ---");
        editor.mostrarHistorial();
        System.out.println("Cantidad de cambios: " + editor.cantidadCambiosEnHistorial());

        System.out.println("\n--- Deshaciendo el último cambio ---");
        editor.deshacer();

        System.out.println("\n--- Deshaciendo otro cambio ---");
        editor.deshacer();

        System.out.println("\n--- Historial luego de deshacer ---");
        editor.mostrarHistorial();

        System.out.println("\n--- Deshaciendo el resto ---");
        editor.deshacer();
        editor.deshacer(); // No debería quedar nada que deshacer

        System.out.println("\nContenido final: \"" + editor.getContenidoActual() + "\"");
    }
}