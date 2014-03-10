package semaforos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Calle calle = new Calle();
        String input = "";

        while (!input.equals("0 0")) {
            try {
                log("Introduce 2 números(El número de semáforos y la velocidad máxima):");

                input = BR.readLine();

                String[] inputs = input.split(" ");
                byte numSemaforos = Byte.parseByte(inputs[0]);

            } catch (IOException ex) {
                loge("IOException: " + ex);
            } catch (NumberFormatException ex) {
                loge("NumberFormatException: " + ex);
            }
        }

        log("END");

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

    private void loge(Object o) {
        if (debug) {
            System.err.println(o);
        }
    }
}

class Calle {

    private List<Semaforo> semaforos;
    private float velMax;

    public Calle() {
    }

    public Calle(List<Semaforo> semaforos, float velMax) {
        this.semaforos = semaforos;
        this.velMax = velMax;
    }

    public List<Semaforo> getSemaforos() {
        return semaforos;
    }

    public void setSemaforos(List<Semaforo> semaforos) {
        this.semaforos = semaforos;
    }

    public float getVelMax() {
        return velMax;
    }

    public void setVelMax(float velMax) {
        this.velMax = velMax;
    }

    @Override
    public String toString() {
        return "Calle{" + "semaforos=" + semaforos + ", velMax=" + velMax + '}';
    }
}

class Semaforo {

    /**
     * Estados de un semaforo
     */
    enum Estado {

        ABIERTO, CERRADO
    }

    private float distanciaAnterior;
    private Estado estado;

    public Semaforo() {
    }

    public Semaforo(float distanciaAnterior, Estado estado) {
        this.distanciaAnterior = distanciaAnterior;
        this.estado = estado;
    }

    public float getDistanciaAnterior() {
        return distanciaAnterior;
    }

    public void setDistanciaAnterior(float distanciaAnterior) {
        this.distanciaAnterior = distanciaAnterior;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Semaforo{" + "distanciaAnterior=" + distanciaAnterior + ", estado=" + estado + '}';
    }
}
