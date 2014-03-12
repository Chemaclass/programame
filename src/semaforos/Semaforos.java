package semaforos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.aceptaelreto.com/problem/statement.php?id=113
 * @author chemaclass
 */
public class Semaforos {

    /**
     * Buffer de entrada
     */
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    //Bandera para controlar debug
    private static final boolean debug = true;
    private static final String[] SALIDA = {"0", "0"};
    /** Calles con semáforos */
    private List<Calle> calles;
    
    public Semaforos(){
        calles = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        new Semaforos().doMain(args);
    }

    public String[] leer(String input) throws IOException {
        input = BR.readLine();
        return input.split(" ");
    }

    /**
     * Comprueba que que se haya introducido "0 0" para salir de la app
     *
     * @param inputs String[]
     * @return boolean
     */
    private boolean fin(String[] inputs) {
        return (inputs.length == 2
                && inputs[0].equals(SALIDA[0])
                && inputs[1].equals(SALIDA[1]));
    }

    public void doMain(String... args) {
        Calle calle;
        String inputs[] = {}, input = "";

        while (!fin(inputs)) {
            try {
                log("Introduce 2 números(El número de semáforos y la velocidad máxima):");
                inputs = leer(input);
                if (fin(inputs)) {
                    break;
                }
                // Obtenemos los valores, el primero es el número de semáforos
                // y el segundo la velocidad (m/s) máxima
                byte numSemaforos = Byte.parseByte(inputs[0]);
                float velMax = Float.parseFloat(inputs[1]);
                //Creamos una nueva calle
                calle = new Calle(numSemaforos, velMax);
                
                log("Introduce 3 números(dist,tiem_cerr,tiem_abie), por cada semáforo:");
                // Obtenemos los datos de los semáforos, 3 por semáforo
                inputs = leer(input);
                //Montamos la calle con los parámetros introducidos por consola
                calle.setSemaforos(inputs);
                //Añadimos la nueva calle a nuestras calles
                calles.add(calle);
                
                log(calle);

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

    public List<Semaforo> getListSemaforos() {
        List<Semaforo> l = new ArrayList();
        l.addAll(Arrays.asList(semaforos));
        return l;
    }

    public Semaforo[] getSemaforos() {
        return semaforos;
    }

    public void setSemaforos(Semaforo[] semaforos) {
        this.semaforos = semaforos;
    }
    
    /**
     * Montamos los semáforos de la calle a partir del array de String que
     * introducimos por consola
     * 
     * @param inputs String[]
     */
    public void setSemaforos(String[] inputs){
        for (int i = 0, j = 0; i < inputs.length; i += 3, j++) {
            float distanciaAnterior = Float.parseFloat(inputs[i]);
            float tiempoCerrado = Float.parseFloat(inputs[i + 1]);
            float tiempoAbierto = Float.parseFloat(inputs[i + 2]);
            //Creamos una nueva instancia Semaforo
            Semaforo semaforo = new Semaforo(distanciaAnterior, tiempoCerrado, tiempoAbierto);
            //Y la añadimos a la calle
            setSemaforo(j, semaforo);
        }
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
        return "Calle{" + "semaforos=" + getListSemaforos() + ", velMax=" + velMax + '}';
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
        return "Semaforo{" + "distanciaAnterior=" + distanciaAnterior + ", tiempoCerrado=" + tiempoCerrado + ", tiempoAbierto=" + tiempoAbierto + ", estadoActual=" + estadoActual + '}';
    }
}
