package utilidades;

/**
 *
 * @author chemaclass
 */
public class Utils {

    private static final boolean debug = false, subDebug = true;

    public static void sd(Object o) {
        if (subDebug) {
            System.out.println(o);
        }
    }

    /**
     * Pintar si está activado el modo debug
     *
     * @param o Object
     */
    public static void d(Object o) {
        if (debug) {
            System.out.println(o);
        }
    }

    public static void de(Object o) {
        if (debug) {
            System.err.println(o);
        }
    }
}
