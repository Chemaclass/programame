package utilidades;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author chemaclass
 */
public class Utils {

    /**
     * Buffer de entrada
     */
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Banderas de debug
     */
    private static final boolean debug = false, debugError = false, subDebug = false;

    /**
     * Mostrar un mensaje por consola
     *
     * @param o Object
     */
    public static void d(Object o) {
        if (debug) {
            System.out.println(o);
        }
    }

    /**
     * Mostrar un mensaje de error por consola
     *
     * @param o Object
     */
    public static void de(Object o) {
        if (debugError) {
            System.err.println(o);
        }
    }

    /**
     * Mostrar un mensaje secundario por consola
     *
     * @param o Object
     */
    public static void sd(Object o) {
        if (subDebug) {
            System.out.println(o);
        }
    }
}
