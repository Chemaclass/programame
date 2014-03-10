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

    public String[] leer(String input) throws IOException {
        input = BR.readLine();
        return input.split(" ");
    }

    public void doMain(String... args) {
        Calle calle;
        String input = "";

        while (!input.equals("0 0")) {
            try {
                log("Introduce 2 números(El número de semáforos y la velocidad máxima):");

                //input = BR.readLine();
                String[] inputs = leer(input);
                // Obtenemos los valores, el primero es el número de semáforos
                // y el segundo la velocidad (m/s) máxima
                byte numSemaforos = Byte.parseByte(inputs[0]);
                float velMax = Float.parseFloat(inputs[1]);
                calle = new Calle(numSemaforos, velMax);

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

    private Semaforo[] semaforos;
    private float velMax;

    public Calle() {
    }

    public Calle(byte numSemaforos, float velMax) {
        this.semaforos = new Semaforo[numSemaforos];
        this.velMax = velMax;
    }

    public Semaforo[] getSemaforos() {
        return semaforos;
    }

    public void setSemaforos(Semaforo[] semaforos) {
        this.semaforos = semaforos;
    }

    public void setSemaforo(int i, Semaforo semaforo) {
        semaforos[i] = semaforo;
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
    private float tiempoCerrado;
    private float tiempoAbierto;
    private Estado estadoActual;

    public Semaforo() {
    }

    public Semaforo(float distanciaAnterior, float tiempoCerrado, float tiempoAbierto) {
        this.distanciaAnterior = distanciaAnterior;
        this.tiempoCerrado = tiempoCerrado;
        this.tiempoAbierto = tiempoAbierto;
        this.estadoActual = Estado.CERRADO;
    }

    public float getTiempoCerrado() {
        return tiempoCerrado;
    }

    public void setTiempoCerrado(float tiempoCerrado) {
        this.tiempoCerrado = tiempoCerrado;
    }

    public float getTiempoAbierto() {
        return tiempoAbierto;
    }

    public void setTiempoAbierto(float tiempoAbierto) {
        this.tiempoAbierto = tiempoAbierto;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public float getDistanciaAnterior() {
        return distanciaAnterior;
    }

    public void setDistanciaAnterior(float distanciaAnterior) {
        this.distanciaAnterior = distanciaAnterior;
    }

    @Override
    public String toString() {
        return "Semaforo{" + "distanciaAnterior=" + distanciaAnterior + ", estado=" + estadoActual + '}';
    }
}
