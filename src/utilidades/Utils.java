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
    private static final boolean flag = true;
    //private static final boolean debug = false, debugError = false, subDebug = false;
    private static final boolean debug = flag, debugError = flag, subDebug = flag;

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

    /**
     * Pintar un str a partir de un array
     *
     * @param arr Array
     */
    public static void arrToStr(Object... arr) {
        if (debug) {
            System.out.print("Array{ ");
            for (Object o : arr) {
                System.out.print(o + ", ");
            }
            System.out.println("}");
        }
    }

    /**
     * Comprobar si un String es numérico
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
