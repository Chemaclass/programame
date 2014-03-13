package utilidades;

/**
 *
 * @author chemaclass
 */
public class Utils {

    private static final boolean debug = false, debugError = false, subDebug = false;

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

    /**
     *
     * @param o Object
     */
    public static void de(Object o) {
        if (debugError) {
            System.err.println(o);
        }
    }

    /**
     *
     * @param o Object
     */
    public static void sd(Object o) {
        if (subDebug) {
            System.out.println(o);
        }
    }
}
