package semaforos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author chemaclass
 */
public class Semaforos {

    /**
     * Buffer de entrada
     */
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    //Bandera para controlar debug
    private static final boolean debug = true;

    public static void main(String[] args) {
        new Semaforos().doMain(args);
    }

    public void doMain(String... args) {

    }

    /**
     * Pintar si está activado el modo debug
     *
     * @param o Object
     */
    private void log(Object o) {
        if (debug) {
            System.out.println(o);
        }
    }
}

class Calle {

}

class Semaforo {

    /**
     * Estados de un semaforo
     */
    enum Estado {

        ABIERTO, CERRADO
    }
}
