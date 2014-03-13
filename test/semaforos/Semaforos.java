package semaforos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.aceptaelreto.com/problem/statement.php?id=113
 *
 * @author chemaclass
 */
public class Semaforos {

    /**
     * Buffer de entrada
     */
    public static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));

    private static final String[] SALIDA = {"0", "0"};
    /**
     * Calles con semáforos
     */
    private List<Calle> calles;

    public Semaforos() {
        calles = new ArrayList<>();
    }

    public static void main(String[] args) {
        new Semaforos().doMain(args);
    }

    private String[] leer(String input) throws IOException {
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
                Utils.d("Introduce 2 números(El número de semáforos y la velocidad máxima):");
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

                Utils.d("Introduce 3 números(dist,tiem_cerr,tiem_abie), por cada semáforo:");
                // Obtenemos los datos de los semáforos, 3 por semáforo
                inputs = leer(input);
                //Montamos la calle con los parámetros introducidos por consola
                calle.setSemaforos(inputs);
                //Añadimos la nueva calle a nuestras calles
                calles.add(calle);

                Utils.d(calle);

            } catch (IOException ex) {
                Utils.de("IOException: " + ex);
            } catch (NumberFormatException ex) {
                Utils.de("NumberFormatException: " + ex);
            } catch (ArrayIndexOutOfBoundsException ex) {
                Utils.de("ArrayIndexOutOfBoundsException: " + ex);
            } catch (Exception ex) {
                Utils.de("Exception: " + ex);
            }
        }

        Utils.d("Programa finalizado... obteniendo resultados por calle...");
        //Obtenemos los resultados y los pintamos
        printResultados();
        Utils.d("END");
    }

    /**
     * Pinta los resultados
     */
    protected void printResultados() {
        for (Calle calle : calles) {
            System.out.println(calle.getTiempoTarda());
        }
    }
}

class Calle {

    private static final int IMPOSIBLE = -1;
    private static final String IMPOSIBLE_STR = "IMPOSIBLE";

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
    public void setSemaforos(String[] inputs) {
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

    /**
     * Establecer un nuevo semáforo en la calle
     *
     * @param i int Posición a colocar
     * @param semaforo Semaforo
     */
    public void setSemaforo(int i, Semaforo semaforo) {
        semaforos[i] = semaforo;
    }

    public float getVelMax() {
        return velMax;
    }

    public void setVelMax(float velMax) {
        this.velMax = velMax;
    }

    /**
     * Obtenemos la velocidad media
     *
     * @return String velocidad media o IMPOSIBLE si no se puede
     */
    public float getVelMed() {
        utilidades.Utils.sd("getVelMed()");
        float d, t;
        Semaforo s;
        float velocidad[] = new float[semaforos.length];
        for (int i = 0; i < semaforos.length; i++) {
            s = semaforos[i];
            d = s.getDistanciaAnterior();
            //Según el semáforo cogeremos su tiempo de abierto o cerrado
            if (i % 2 == 0) {
                t = s.getTiempoCerrado();
            } else {
                t = s.getTiempoAbierto();
            }
            float r = d / t;
            //Si es mayor que la velocidad máxima, debemos buscar una a una
            // hasta dar con alguna
            if (r > velMax) {
                utilidades.Utils.sd("r > velMax == false");
                for (int j = (int) t + 1; j < d; j++) {
                    utilidades.Utils.sd(d + " % " + j + " ? ");
                    if (d % j == 0) {
                        utilidades.Utils.sd(d + " % " + j + " == 0");
                        //Obtenemos el nuevo tiempo
                        r = d / j;
                        utilidades.Utils.sd("r => " + r);
                        break;
                    }
                }
                r = (r > velMax) ? IMPOSIBLE : r;
            } else if (r < 0.1f) {
                r = IMPOSIBLE;
            }
            velocidad[i] = r;
            utilidades.Utils.sd("velocidad[" + i + "]=" + r + " m/s");
        }

        return getMedia(velocidad);
    }

    /**
     * Obtenemos el tiempo que tarda el coche en segundos, teniendo en cuenta la
     * velocidad media a la que tiene que ir
     *
     * @return String
     */
    public String getTiempoTarda() {

        int velMed = (int) getVelMed();

        return (velMed == IMPOSIBLE) ? IMPOSIBLE_STR : "" + getDistanciaTotal() / velMed;
    }

    /**
     * Obtenemos la distancia todal que tendrá que recorrer, a partir de las
     * distancias que existen entre los semáforos de la calle
     *
     * @return int
     */
    private int getDistanciaTotal() {
        int t = 0;
        for (Semaforo s : semaforos) {
            t += s.getDistanciaAnterior();
        }
        return t;
    }

    /**
     * Obtenemos la media de las velocidades
     *
     * @param velocidad float[] Array de velocidades
     * @return float La media de toda las velocidades pasadas en el array
     */
    private float getMedia(float[] velocidad) {
        float total = 0, cont = 0;
        for (float v : velocidad) {
            //Con que haya una sola velocidad imposible bastará por dar por imposible
            // dicha calle
            if (v == IMPOSIBLE) {
                return IMPOSIBLE;
            }
            total += v;
            cont++;
        }
        return total / cont;
    }

    @Override
    public String toString() {
        return "Calle{" + "semaforos=" + getListSemaforos() + ", velMax=" + velMax + '}';
    }
}

/**
 *
 * @author chemaclass
 */
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

class Utils {

    private static final boolean debug = false, subDebug = false;

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
        if (debug) {
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
