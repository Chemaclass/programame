package marcadores;

/**
 * Excepción para controlar una entrada mal formada. Las entradas deben estar
 * dadas por número de entre 0 y 9, separadas por un espacio.
 *
 * @author chemaclass
 */
public class MalFormadoException extends Exception {

    private static final String ms = "Entrada mal formada.";

    public MalFormadoException() {
        super(ms);
    }
}
