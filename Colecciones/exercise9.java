import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Simula el historial de navegación web usando una pila (Stack),
 * que funciona bajo el principio LIFO (Last In, First Out):
 * la última página visitada es la primera en abandonarse al retroceder.
 */
class NavegadorWeb {

    private final Stack<String> historialNavegacion;
    private String paginaActual;

    public NavegadorWeb(String paginaInicial) {
        this.historialNavegacion = new Stack<>();
        this.paginaActual = paginaInicial;
        System.out.println("Navegador iniciado en: " + paginaActual);
    }

    /**
     * Visita una nueva página: la página actual se apila (para poder
     * volver a ella luego) y la nueva pasa a ser la página actual.
     */
    public void visitarPagina(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("La URL no puede estar vacía.");
        }
        historialNavegacion.push(paginaActual);
        paginaActual = url;
        System.out.println("Navegando a: " + paginaActual);
    }

    /**
     * Retrocede a la página anterior, sacando la última página
     * almacenada en la cima de la pila.
     *
     * @return la página a la que se retrocedió
     */
    public String retroceder() {
        if (historialNavegacion.isEmpty()) {
            System.out.println("No hay páginas anteriores a las cuales retroceder.");
            return paginaActual;
        }
        paginaActual = historialNavegacion.pop();
        System.out.println("Retrocediendo a: " + paginaActual);
        return paginaActual;
    }

    public String getPaginaActual() {
        return paginaActual;
    }

    public boolean puedeRetroceder() {
        return !historialNavegacion.isEmpty();
    }

    public void mostrarHistorial() {
        System.out.println("Historial (de más antigua a más reciente): " + historialNavegacion);
    }

    /**
     * Método main para probar la clase de forma independiente.
     */
    public static void main(String[] args) {
        NavegadorWeb navegador = new NavegadorWeb("google.com");

        System.out.println("\n--- El usuario navega por varias páginas ---");
        navegador.visitarPagina("wikipedia.org");
        navegador.visitarPagina("stackoverflow.com");
        navegador.visitarPagina("github.com");

        navegador.mostrarHistorial();
        System.out.println("Página actual: " + navegador.getPaginaActual());

        System.out.println("\n--- El usuario retrocede dos veces ---");
        navegador.retroceder();
        navegador.retroceder();

        System.out.println("Página actual: " + navegador.getPaginaActual());
        navegador.mostrarHistorial();

        System.out.println("\n--- El usuario visita una nueva página desde aquí ---");
        navegador.visitarPagina("openai.com");
        navegador.mostrarHistorial();

        System.out.println("\n--- Se retrocede hasta agotar el historial ---");
        while (navegador.puedeRetroceder()) {
            navegador.retroceder();
        }

        System.out.println("Página actual: " + navegador.getPaginaActual());
        System.out.println("¿Puede retroceder más? " + navegador.puedeRetroceder());

        System.out.println("\n--- Intentando retroceder sin historial ---");
        navegador.retroceder();
    }
}