import java.util.LinkedList;

/**
 * Sistema de gestión de turnos de un banco.
 * Usa una LinkedList<String> porque permite inserciones y eliminaciones
 * eficientes tanto al inicio como al final (O(1)), lo cual es ideal para
 * una cola de espera donde además se necesitan urgencias al inicio.
 */
class SistemaTurnosBanco {

    private final LinkedList<String> colaEspera;

    public SistemaTurnosBanco() {
        this.colaEspera = new LinkedList<>();
    }

    /**
     * Agrega un cliente al final de la cola de espera (turno normal).
     */
    public void agregarCliente(String nombreCliente) {
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        colaEspera.addLast(nombreCliente);
        System.out.println("Cliente agregado a la cola: " + nombreCliente);
    }

    /**
     * Inserta un cliente con urgencia al INICIO de la cola,
     * para que sea el próximo en ser atendido.
     * addFirst() en una LinkedList es O(1), por lo que no afecta
     * el rendimiento sin importar cuántos clientes haya en espera.
     */
    public void agregarClienteUrgente(String nombreCliente) {
        if (nombreCliente == null || nombreCliente.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        colaEspera.addFirst(nombreCliente);
        System.out.println("Cliente URGENTE insertado al inicio: " + nombreCliente);
    }

    /**
     * Atiende (retira) al primer cliente de la cola.
     *
     * @return el nombre del cliente atendido, o null si la cola está vacía
     */
    public String atenderCliente() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay clientes en espera.");
            return null;
        }
        String atendido = colaEspera.removeFirst();
        System.out.println("Atendiendo a: " + atendido);
        return atendido;
    }

    /**
     * Consulta quién es el próximo cliente a atender, sin retirarlo.
     */
    public String proximoCliente() {
        return colaEspera.peekFirst();
    }

    public boolean hayClientesEnEspera() {
        return !colaEspera.isEmpty();
    }

    public int cantidadEnEspera() {
        return colaEspera.size();
    }

    public void mostrarCola() {
        System.out.println("Cola actual: " + colaEspera);
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        SistemaTurnosBanco banco = new SistemaTurnosBanco();

        System.out.println("--- Llegan clientes normales ---");
        banco.agregarCliente("Carlos Pérez");
        banco.agregarCliente("María Gómez");
        banco.agregarCliente("Luis Rodríguez");
        banco.mostrarCola();

        System.out.println("\n--- Se atiende al primer cliente ---");
        banco.atenderCliente();
        banco.mostrarCola();

        System.out.println("\n--- Llega un cliente con urgencia (ej. adulto mayor) ---");
        banco.agregarClienteUrgente("Doña Ana (adulto mayor)");
        banco.mostrarCola();

        System.out.println("\n--- Próximo cliente a atender ---");
        System.out.println(banco.proximoCliente());

        System.out.println("\n--- Se continúan atendiendo los turnos ---");
        while (banco.hayClientesEnEspera()) {
            banco.atenderCliente();
        }

        System.out.println("\n¿Quedan clientes en espera? " + banco.hayClientesEnEspera());
        banco.atenderCliente(); // Prueba con la cola vacía
    }
}